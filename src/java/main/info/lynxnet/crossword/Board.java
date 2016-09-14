package info.lynxnet.crossword;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Board {
    private int n;
    private Map<Direction, Collection<WordPlacement>> wordPlacements;
    private Collection<String> allWords;
    private Map<Direction, Collection<String>> wordsByDirection;


    public Board(int n) {
        this.n = n;
        wordPlacements = new HashMap<>();
        wordPlacements.put(Direction.ACROSS, new HashSet<WordPlacement>());
        wordPlacements.put(Direction.DOWN, new HashSet<WordPlacement>());
        wordsByDirection = new HashMap<>();
        wordsByDirection.put(Direction.ACROSS, new HashSet<String>());
        wordsByDirection.put(Direction.DOWN, new HashSet<String>());
        allWords = new HashSet<>();
    }
    

}
