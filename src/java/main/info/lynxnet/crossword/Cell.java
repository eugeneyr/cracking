package info.lynxnet.crossword;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Cell {
    private int x;
    private int y;
    private char letter = Constants.EMPTY_CELL_FILLER;
    private WordPlacement acrossWord;
    private WordPlacement downWord;

    public Cell(int x, int y, WordPlacement acrossWord, WordPlacement downWord) throws PlacementException {
        if (x < 0 || y < 0) {
            throw new PlacementException(String.format("Incorrect coordinates: %d, %d", x, y));
        }
        this.x = x;
        this.y = y;
        this.acrossWord = acrossWord;
        this.downWord = downWord;

        if (acrossWord != null) {
            if (acrossWord.getY() != y) {
                throw new PlacementException(
                        String.format(
                                "The y coordinate of the Accross word does not match: %d, %d", y, acrossWord.getY()));
            }
            if (acrossWord.getX() > x) {
                throw new PlacementException(
                        String.format(
                                "The Accross word lays after the cell: %d, %d", x, acrossWord.getX()));
            }
            if (acrossWord.getWord().length() + acrossWord.getX() <= x) {
                throw new PlacementException(
                        String.format(
                                "The Accross word does not reach the cell: %d, %d", x, acrossWord.getX()));
            }
            this.letter = acrossWord.getWord().charAt(x - acrossWord.getX());
        }

        if (downWord != null) {
            if (downWord.getX() != x) {
                throw new PlacementException(
                        String.format(
                                "The x coordinate of the Down word does not match: %d, %d", x, downWord.getY()));
            }
            if (downWord.getY() > y) {
                throw new PlacementException(
                        String.format(
                                "The Down word lays after the cell: %d, %d", y, downWord.getY()));
            }
            if (downWord.getWord().length() + downWord.getY() <= y) {
                throw new PlacementException(
                        String.format(
                                "The Down word does not reach the cell: %d, %d", y, downWord.getY()));
            }
            char letter = downWord.getWord().charAt(y - downWord.getY());
            if (this.letter != Constants.EMPTY_CELL_FILLER && letter != this.letter) {
                throw new PlacementException(
                        String.format(
                                "The letter in the Down word does not match the letter in the Across word:: %c, %c",
                                this.letter, letter));
            }
            if (this.letter == Constants.EMPTY_CELL_FILLER) {
                this.letter = letter;
            }
        }
    }

    public Cell(int x, int y) throws PlacementException {
        if (x < 0 || y < 0) {
            throw new PlacementException(String.format("Incorrect coordinates: %d, %d", x, y));
        }
        this.x = x;
        this.y = y;
    }

    public Cell() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public char getLetter() {
        return letter;
    }

    public WordPlacement getAcrossWord() {
        return acrossWord;
    }

    public void setAcrossWord(WordPlacement acrossWord) throws PlacementException {
        if (acrossWord != null) {
            if (acrossWord.getY() != y) {
                throw new PlacementException(
                        String.format(
                                "The y coordinate of the Accross word does not match: %d, %d", y, acrossWord.getY()));
            }
            if (acrossWord.getX() > x) {
                throw new PlacementException(
                        String.format(
                                "The Accross word lays after the cell: %d, %d", x, acrossWord.getX()));
            }
            if (acrossWord.getWord().length() + acrossWord.getX() <= x) {
                throw new PlacementException(
                        String.format(
                                "The Accross word does not reach the cell: %d, %d", x, acrossWord.getX()));
            }
            char letter = acrossWord.getWord().charAt(x - downWord.getX());
            if (this.letter != Constants.EMPTY_CELL_FILLER && letter != this.letter) {
                throw new PlacementException(
                        String.format(
                                "The letter in the new Across word does not match the letter in the Down word:: %c, %c",
                                this.letter, letter));
            }
            if (this.letter == Constants.EMPTY_CELL_FILLER) {
                this.letter = letter;
            }

            this.letter = acrossWord.getWord().charAt(x - acrossWord.getX());
        }

        this.acrossWord = acrossWord;

        if (this.downWord == null && this.acrossWord == null) {
            this.letter = Constants.EMPTY_CELL_FILLER;
        }
    }

    public WordPlacement getDownWord() {
        return downWord;
    }

    public void setDownWord(WordPlacement downWord) throws PlacementException {
        if (downWord != null) {
            if (downWord.getX() != x) {
                throw new PlacementException(
                        String.format(
                                "The x coordinate of the Down word does not match: %d, %d", x, downWord.getY()));
            }
            if (downWord.getY() > y) {
                throw new PlacementException(
                        String.format(
                                "The Down word lays after the cell: %d, %d", y, downWord.getY()));
            }
            if (downWord.getWord().length() + downWord.getY() <= y) {
                throw new PlacementException(
                        String.format(
                                "The Down word does not reach the cell: %d, %d", y, downWord.getY()));
            }
            char letter = downWord.getWord().charAt(y - downWord.getY());
            if (this.letter != Constants.EMPTY_CELL_FILLER && letter != this.letter) {
                throw new PlacementException(
                        String.format(
                                "The letter in the new Down word does not match the letter in the Across word:: %c, %c",
                                this.letter, letter));
            }
            if (this.letter == Constants.EMPTY_CELL_FILLER) {
                this.letter = letter;
            }
        }

        this.downWord = downWord;

        if (this.downWord == null && this.acrossWord == null) {
            this.letter = Constants.EMPTY_CELL_FILLER;
        }
    }

    public WordPlacement getWord(Direction direction) {
        switch (direction) {
            case ACROSS:
                return this.acrossWord;
            case DOWN:
                return this.downWord;
        }
        throw new IllegalArgumentException(
                String.format(
                        "Direction not supported: %s", direction == null ? "" : direction.toString()));
    }

    public void setWord(WordPlacement wordPlacement) throws PlacementException {
        Direction direction = wordPlacement.getDirection();
        switch (direction) {
            case ACROSS:
                setAcrossWord(acrossWord);
                break;
            case DOWN:
                setDownWord(downWord);
                break;
        }
        throw new IllegalArgumentException(
                String.format(
                        "Direction not supported: %s", direction == null ? "" : direction.toString()));
    }
}
