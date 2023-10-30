package indy.PlatformPackage;

import indy.Constants;
import indy.Player;
import javafx.scene.paint.Color;

public class SmallBPlat extends Platforms {
    /**
     * Constructor for the SmallBlockPlat class
     */
    public SmallBPlat() {
        super();
        this.setFill(Color.GREEN);
    }
    /**
     * Method that restrains movement to the Big player
     */
    public static boolean canMove(Player player, int x, int y) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X -((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE + x;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y  + y;
        return col <= -1 || row <= -1 || col >= Constants.BOARD_WIDTH_HEIGHT ||
                row >= Constants.BOARD_WIDTH_HEIGHT || !Board.platCheck(row, col, Color.DARKGREEN, false);
    }
    /**
     * Method that changes the color of the platform when the player is able to use it
     */
    @Override
    public void lightUp(boolean smallState) {
        if(smallState) {
            this.setFill(Color.rgb(0,Constants.GREEN_COLOR,0));
        } else {
            this.setFill(Color.DARKGREEN);
        }
    }
}
