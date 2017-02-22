package info.lynxnet.etudes.trac;

public interface Constants {
    String ACTIVE_FUNCTION_MARKER = "#(";
    String NEUTRAL_FUNCTION_MARKER = "##(";
    char OPENING_BRACKET = '(';
    char CLOSING_BRACKET = ')';
    char METACHARACTER = '\'';
    String LINE_BREAK = "CR-LF";
    String INITIAL_ACTIVE_STRING = "#(ps,(CR-LF))#(ps,#(rs))";
    String DEFAULT_TRAC_DATA_DIR = ".tracdata";
    String BLOCK_SUBDIR = "blockstorage";
}
