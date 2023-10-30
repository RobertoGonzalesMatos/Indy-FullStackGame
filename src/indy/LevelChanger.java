package indy;

public class LevelChanger {
    /**
     * Wrapper Class for the blueprints of the board
     */
    public LevelChanger(){
    }
    /**
     * Method that changes the layout of the game
     */
    public static int[][] setLevel(int[][] boardBluePrint, int level) {
        switch(level) {
            case 0:
                boardBluePrint = new int[][] {
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 2, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                    {0, 0, 0, 4, 1, 6, 1, 4, 0, 0},
                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 1, 1, 3}
                };
                break;
            case 1:
                boardBluePrint = new int[][] {
                    {1, 1, 7, 6, 4, 0, 0, 0, 0, 0},
                    {0, 0, 5, 0, 1, 1, 2, 0, 0, 0},
                    {0, 0, 1, 2, 0, 0, 1, 6, 4, 0},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 5, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 4, 2, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 1, 6, 3}
                };
                break;
            case 2:
                boardBluePrint = new int[][] {
                     {1, 1, 1, 1, 0, 0, 0, 0, 0, 4},
                     {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                     {0, 0, 1, 1, 5, 1, 1, 1, 1, 2},
                     {0, 0, 1, 0, 0, 1, 0, 6, 1, 4},
                     {0, 4, 1, 8, 0, 1, 1, 0, 1, 0},
                     {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                     {0, 7, 1, 0, 0, 0, 0, 1, 1, 0},
                     {0, 0, 0, 0, 0, 1, 6, 1, 0, 0},
                     {0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                     {0, 0, 0, 0, 0, 4, 0, 1, 1, 3}
                };
                break;
            case 3:
                boardBluePrint = new int[][] {
                     {1, 1, 1, 1, 8, 0, 1, 4, 0, 0},
                     {0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                     {0, 0, 1, 1, 6, 1, 1, 1, 1, 2},
                     {0, 0, 1, 0, 0, 1, 0, 0, 1, 4},
                     {0, 0, 1, 0, 1, 8, 1, 0, 1, 0},
                     {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                     {0, 9, 4, 0, 8, 4, 0, 0, 1, 0},
                     {0, 0, 0, 0, 0, 1, 8, 0, 1, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0, 1, 3}
                };
                break;
            case 4:
                boardBluePrint = new int[][] {
                     {1, 1, 0, 0, 0, 4, 0, 0, 0, 0},
                     {1, 5, 1, 0, 8, 1, 1, 1, 0, 0},
                     {0, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                     {0, 0, 1, 8, 0, 8, 0, 1, 4, 0},
                     {0, 8, 0, 0, 0, 0, 0, 0, 0, 7},
                     {4, 1, 1, 8, 0, 5, 1, 1, 1, 5},
                     {0, 1, 0, 0, 0, 1, 2, 0, 1, 0},
                     {0, 1, 1, 1, 0, 1, 0, 0, 6, 0},
                     {0, 0, 0, 4, 0, 1, 1, 6, 3, 0},
                     {0, 0, 0, 0, 7, 5, 0, 0, 0, 0}
                };
                break;
            case 5:
                boardBluePrint = new int[][] {
                     {1, 1, 1, 4, 0, 0, 0, 0, 9, 0},
                     {0, 0, 5, 0, 0, 0, 1, 1, 4, 0},
                     {0, 0, 2, 1, 6, 1, 1, 0, 0, 0},
                     {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                     {0, 0, 6, 0, 0, 0, 1, 0, 0, 0},
                     {0, 1, 1, 1, 1, 1, 2, 1, 1, 9},
                     {0, 0, 1, 0, 0, 1, 0, 0, 4, 0},
                     {0, 0, 5, 0, 0, 4, 0, 0, 1, 0},
                     {0, 0, 1, 0, 0, 1, 1, 1, 1, 0},
                     {9, 4, 1, 0, 0, 0, 0, 3, 0, 0}
                };
                break;
            case 6:
                boardBluePrint = new int[][] {
                     {1, 1, 8, 0, 1, 0, 0, 0, 9, 4},
                     {1, 1, 0, 0, 1, 8, 0, 1, 1, 1},
                     {0, 8, 0, 1, 4, 0, 0, 0, 0, 0},
                     {0, 0, 0, 8, 9, 0, 0, 8, 0, 0},
                     {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                     {0, 1, 1, 8, 0, 8, 1, 1, 9, 0},
                     {0, 8, 4, 0, 0, 0, 0, 4, 0, 0},
                     {0, 0, 0, 8, 0, 0, 0, 8, 0, 0},
                     {0, 4, 1, 0, 0, 0, 0, 0, 0, 0},
                     {0, 9, 1, 1, 0, 1, 1, 1, 1, 3}
                };
                break;
        }
        return boardBluePrint;
    }
    /**
     * Method that sets the number of movements per level
     */
    public static int setMoves(int level, int numberOfMoves) {
        switch(level) {
            case 0:
                numberOfMoves = Constants.MOVEMENTS_1;
                break;
            case 1:
               numberOfMoves=Constants.MOVEMENTS_2;
                break;
            case 2:
               numberOfMoves=Constants.MOVEMENTS_3;
                break;
            case 3:
                numberOfMoves=Constants.MOVEMENTS_4;
                break;
            case 4:
                numberOfMoves=Constants.MOVEMENTS_5;
                break;
            case 5:
                numberOfMoves=Constants.MOVEMENTS_6;
                break;
            case 6:
                numberOfMoves=Constants.MOVEMENTS_7;
                break;
        }
        return numberOfMoves;
    }
    /**
     * Method that sets the number of keys per level
     */
    public static int setLevelKeys(int level, int numberOfKeys) {
        switch(level) {
            case 0:
                numberOfKeys=Constants.KEY_NUMBER_1;
                break;
            case 1:
                numberOfKeys=Constants.KEY_NUMBER_2;
                break;
            case 2:
                numberOfKeys=Constants.KEY_NUMBER_3;
                break;
            case 3:
                numberOfKeys=Constants.KEY_NUMBER_3;
                break;
            case 4:
                numberOfKeys=Constants.KEY_NUMBER_3;
                break;
            case 5:
                numberOfKeys=Constants.KEY_NUMBER_4;
                break;
            case 6:
                numberOfKeys=Constants.KEY_NUMBER_4;
                break;
        }
        return numberOfKeys;
    }
}
