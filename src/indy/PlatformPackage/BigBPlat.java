package indy.PlatformPackage;

import indy.Constants;
import indy.Player;
import javafx.scene.paint.Color;

public class BigBPlat extends Platforms {
    /**
     * Constructor for the BigBlockPlat class
     */
    public BigBPlat() {
        super();
        this.setFill(Color.BLUE);
    }
    /**
     * Method that restrains movement to the small player
     */
    public static boolean canMove(Player player, int x, int y) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X -((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE+x;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y  + y;
        return (col != -1 && !(row == -1 | col == Constants.BOARD_WIDTH_HEIGHT)
                && row != Constants.BOARD_WIDTH_HEIGHT) &&
                !Board.platCheck(row, col, Color.DARKBLUE,true);
    }
    /**
     * Method that changes the color of the platform when the player is able to use it
     */
    @Override
    public void lightUp(boolean smallState) {
        if(!smallState) {
            this.setFill(Color.BLUE);
        } else {
            this.setFill(Color.DARKBLUE);
        }
    }
}
