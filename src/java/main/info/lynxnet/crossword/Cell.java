package info.lynxnet.crossword;

public class Cell {
    private int x;
    private int y;
    private char letter;
    private WordPlacement acrossWord;
    private WordPlacement downWord;

    public Cell(int x, int y, char letter, WordPlacement acrossWord, WordPlacement downWord) {
        this.x = x;
        this.y = y;
        this.letter = letter;
        this.acrossWord = acrossWord;
        this.downWord = downWord;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, WordPlacement acrossWord, WordPlacement downWord) {
        this.x = x;
        this.y = y;
        this.acrossWord = acrossWord;
        this.downWord = downWord;
    }

    public Cell() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public WordPlacement getAcrossWord() {
        return acrossWord;
    }

    public void setAcrossWord(WordPlacement acrossWord) {
        this.acrossWord = acrossWord;
    }

    public WordPlacement getDownWord() {
        return downWord;
    }

    public void setDownWord(WordPlacement downWord) {
        this.downWord = downWord;
    }
}
