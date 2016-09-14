package info.lynxnet.crossword;

public class WordPlacement {
    private String word;
    private int x;
    private int y;
    private Direction direction;

    public WordPlacement(String word, int x, int y, Direction direction) {
        this.word = word;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WordPlacement that = (WordPlacement) o;

        if (x != that.x) {
            return false;
        }
        if (y != that.y) {
            return false;
        }
        if (word != null ? !word.equals(that.word) : that.word != null) {
            return false;
        }
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
