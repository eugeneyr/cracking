package info.lynxnet.etudes.trac;

interface State {
    boolean precondition();
    Class<? extends State> actionAndTransition();
}
