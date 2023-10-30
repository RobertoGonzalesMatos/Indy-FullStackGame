package indy.PlatformPackage;

import indy.Constants;
import indy.Player;
import indy.SmallPlayer;
import javafx.scene.paint.Color;

public class SeparationPlat extends Platforms {
    private static int sBX=0;
    private static int sBY=0;
    private static int BBX=0;
    private static int BBY=0;
    /**
     * Constructor for the separation platform
     */
    public SeparationPlat() {
        super();
        this.setFill(Color.ORANGE);
    }
    /**
     * Method that changes the state of the player
     */
    public static boolean stateChange(Player player, Boolean bigState) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X -((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y;
        if (Board.platCheck(row, col,Color.ORANGE, false)) {
            return true;
        }
        return bigState;
    }
    /**
     * Method to check if the player intersects with the platform
     */
    public static boolean intersects(Player player, int x, int y, Color color) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X-((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE + x;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y  + y;
        return !(col <= -1 || row <= -1 | col >= Constants.BOARD_WIDTH_HEIGHT || row >= Constants.BOARD_WIDTH_HEIGHT)
                && Board.platCheck(row, col, color, false);
    }
    /**
     * Method that checks available spaces and places the small blocks on the available ones.
     */
    public static void smartSeparationMethod(Player player, SmallPlayer smallPlayer) {
        if(SeparationPlat.intersects(player,0,0,Color.ORANGE)) {
            resetValue();
            for (int i = 1; i<Constants.AVAILABLE_PLAT;i++) {
                switch(i){
                case 1:
                    clockWiseChecker(player,-Constants.VERTICAL_MOVE_X,-Constants.VERTICAL_MOVE_Y,
                            0,-1);
                case 2:
                    clockWiseChecker(player,Constants.PLAT_OFFSET_X,-Constants.VERTICAL_MOVE_Y,
                            1,-1);
                case 3:
                    clockWiseChecker(player,Constants.HORIZONTAL_MOVE,0, 1,0);
                case 4:
                    clockWiseChecker(player,Constants.PLAT_OFFSET_X1,Constants.VERTICAL_MOVE_Y,
                            1,1);
                case 5:
                    clockWiseChecker(player,Constants.VERTICAL_MOVE_X,Constants.VERTICAL_MOVE_Y,
                            0,1);
                case 6:
                    clockWiseChecker(player,-Constants.PLAT_OFFSET_X,Constants.VERTICAL_MOVE_Y,
                            -1,1);
                case 7:
                    clockWiseChecker(player,-Constants.HORIZONTAL_MOVE,0,-1,0);
                case 8:
                    clockWiseChecker(player,-Constants.PLAT_OFFSET_X1,-Constants.VERTICAL_MOVE_Y,
                            -1,-1);
                }
            }
            player.setX(BBX,0,0);
            player.setY(BBY,0,0);
            smallPlayer.setX(sBX,0,0);
            smallPlayer.setY(sBY,0,0);
        }
    }
    /**
     * Method that determines if a platform is available and give the coordinates for the small platform
     * to be placed there. If one other platform is available then one of the small players will be placed
     * in the separation platform itself. The coordinates are checked in a clockwise manner for both small
     * blocks but the original player will be placed in the first available platform in a clockwise manner,
     * while the other one will be placed down in a counterclockwise manner.
     */
    public static void clockWiseChecker(Player player, int offsetX, int offsetY,int offsetRow, int offsetCol) {
        if(sBX==0&&sBY==0&&SeparationPlat.intersects(player,offsetRow,offsetCol,Color.MEDIUMVIOLETRED)) {
            sBX=player.getX()+ offsetX;
            sBY=player.getY()+ offsetY;
        } else if (!(sBX==0&&sBY==0)&&SeparationPlat.intersects(player,offsetRow,offsetCol,Color.MEDIUMVIOLETRED)) {
            if(sBX==player.getX()+ offsetX&&sBY==player.getY()+ offsetY) {
                BBX=player.getX();
                BBY=player.getY();
            } else {
                BBX=player.getX()+ offsetX;
                BBY=player.getY()+ offsetY;
            }
        }
    }
    /**
     * Method that resets the values of the coordinates for the separation method.
     */
    public static void resetValue() {
        sBX=0;
        sBY=0;
    }
}
