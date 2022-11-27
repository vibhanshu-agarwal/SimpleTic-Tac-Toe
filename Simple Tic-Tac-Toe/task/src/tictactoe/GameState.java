package tictactoe;

enum GameState {
    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible"),
    PLAYER_X("X"),
    PLAYER_O("O"),

    EMPTY("_");

    private static String lastMove = "_";

    final String state;

    public static String getLastMove() {
        return GameState.lastMove;
    }

    public static void setLastMove(String lastMove) {
        GameState.lastMove = lastMove;
    }

    private

    GameState(String state) {
        this.state = state;
    }
}
