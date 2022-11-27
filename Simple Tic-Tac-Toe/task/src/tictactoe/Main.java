package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        //Build Empty grid and print it
        String userGrid = "_________";
        String[][] twoDGrid = build2DGrid(userGrid);
        printGrid(twoDGrid);

        do {
            String userCoords = scanner.nextLine();
            if (!userCoords.matches("\\d+ \\d+")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int y = Character.getNumericValue(userCoords.charAt(0));
            int x = Character.getNumericValue(userCoords.charAt(2));

            if (!isValidUserCoords(y,
                    x)) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if (!isCellEmpty(twoDGrid,
                        y,
                        x)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (GameState.getLastMove().equals(GameState.PLAYER_X.state)) {
                        twoDGrid[y-1][x-1] = GameState.PLAYER_O.state;
                        GameState.setLastMove(GameState.PLAYER_O.state);
                    } else {
                        twoDGrid[y-1][x-1] = GameState.PLAYER_X.state;
                        GameState.setLastMove(GameState.PLAYER_X.state);
                    }
                    printGrid(twoDGrid);
                    if (checkGameState(twoDGrid)) {
                        break;
                    }

                }
            }
        } while (true);
    }

    private static boolean isCellEmpty(String[][] twoDGrid, int y, int x) {
        return twoDGrid[y - 1][x - 1].equals(GameState.EMPTY.state);
    }

    private static boolean isValidUserCoords(int y, int x) {
        return (y >= 1 && y <= 3) && (x >= 1 && x <= 3);
    }

    private static String[][] build2DGrid(String userGrid) {
        String[][] twoDGrid = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                twoDGrid[i][j] = String.valueOf(userGrid.charAt(i * 3 + j));
            }
        }
        return twoDGrid;
    }

    private static void printGrid(String[][] twoDGrid) {
        String ticTacToeGrid = """
                ---------
                | %s %s %s |
                | %s %s %s |
                | %s %s %s |
                ---------
                """;
        System.out.printf(ticTacToeGrid,
                twoDGrid[0][0],
                twoDGrid[0][1],
                twoDGrid[0][2],
                twoDGrid[1][0],
                twoDGrid[1][1],
                twoDGrid[1][2],
                twoDGrid[2][0],
                twoDGrid[2][1],
                twoDGrid[2][2]);
    }

    private static boolean checkGameState(String[][] twoDGrid) {
        int xCount = 0;
        int oCount = 0;
        int emptyCount = 0;
        boolean xWins = false;
        boolean oWins = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (twoDGrid[i][j].equals(GameState.PLAYER_X.state)) {
                    xCount++;
                } else {
                    if (twoDGrid[i][j].equals(GameState.PLAYER_O.state)) {
                        oCount++;
                    } else {
                        emptyCount++;
                    }
                }
                //Identify horizontal wins
                if (j == 0 && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i][j + 1]) && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i][j + 2])) {
                    if (twoDGrid[i][j].equals(GameState.PLAYER_X.state)) {
                        xWins = true;
                    } else if (twoDGrid[i][j].equals(GameState.PLAYER_O.state)) {
                        oWins = true;
                    }

                }
                //Identify vertical wins
                if (i == 0 && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 1][j]) && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 2][j])) {
                    if (twoDGrid[i][j].equals(GameState.PLAYER_X.state)) {
                        xWins = true;
                    } else if (twoDGrid[i][j].equals(GameState.PLAYER_O.state)) {
                        oWins = true;
                    }

                }
                //Identify diagonal wins
                if (i == 0 && j == 0 && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 1][j + 1]) && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 2][j + 2])) {

                    if (twoDGrid[i][j].equals(GameState.PLAYER_X.state)) {
                        xWins = true;
                    } else if (twoDGrid[i][j].equals(GameState.PLAYER_O.state)) {
                        oWins = true;
                    }

                }
                if (i == 0 && j == 2 && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 1][j - 1]) && Objects.equals(twoDGrid[i][j],
                        twoDGrid[i + 2][j - 2])) {
                    if (twoDGrid[i][j].equals(GameState.PLAYER_X.state)) {
                        xWins = true;
                    } else if (twoDGrid[i][j].equals(GameState.PLAYER_O.state)) {
                        oWins = true;
                    }

                }
            }
        }

        return calculateIfGameIsOver(xCount,
                oCount,
                emptyCount,
                xWins,
                oWins);
    }

    private static boolean calculateIfGameIsOver(int xCount, int oCount, int emptyCount, boolean xWins, boolean oWins) {
        boolean isGameOver = false;
        if (xWins && oWins || Math.abs(xCount - oCount) > 1) {
            System.out.println(GameState.IMPOSSIBLE.state);
            isGameOver = true;
        } else if (!xWins && !oWins && emptyCount == 0) {
            System.out.println(GameState.DRAW.state);
            isGameOver = true;
        } else if (!xWins && !oWins && emptyCount > 0) {
//            System.out.println(GameState.NOT_FINISHED.state);
        } else if (xWins) {
            System.out.println(GameState.X_WINS.state);
            isGameOver = true;
        } else if (oWins) {
            System.out.println(GameState.O_WINS.state);
            isGameOver = true;
        }
        return isGameOver;
    }
}
