package indy.PlatformPackage;

import indy.BlockAni;
import indy.Constants;
import indy.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TPPlat extends Platforms {
    private static int preX;
    private static int preY;
    /**
     * Constructor for the Teleportation platform
     */
    public TPPlat() {
        super();
        this.setFill(Color.GOLD);
    }
    /**
     * Method that checks the direction that the player is coming from
     */
    public static boolean direction(Player player, int x, int y) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X-((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE+x;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y  + y;
        return !(col <= -1) && !(row <= -1) && !(col >= Constants.BOARD_WIDTH_HEIGHT) &&
                !(row >= Constants.BOARD_WIDTH_HEIGHT) && Board.platCheck(row, col, Color.GOLD, false);
    }
    /**
     * Method that registers the direction of the player
     */
    public static void registerDirection(Player player) {
        if(direction(player, 1,0)) {
            preX= Constants.HORIZONTAL_MOVE;
            preY=0;
        }
        if(direction(player,-1,0)) {
            preX=-Constants.HORIZONTAL_MOVE;
            preY=0;
        }
        if(direction(player,0,1)) {
            preX=Constants.VERTICAL_MOVE_X;
            preY=Constants.VERTICAL_MOVE_Y;
        }
        if(direction(player,0,-1)) {
            preX=-Constants.VERTICAL_MOVE_X;
            preY=-Constants.VERTICAL_MOVE_Y;
        }
    }
    /**
     * Method that teleports the player two spaces ahead from the platform
     * taking into consideration the direction that it came from.
     */
    public static void teleport(Player player, BlockAni image, Pane pane, boolean SmallState) {
        if(!SmallState&& direction(player,0,0)) {
            player.move(Constants.X2_MULTIPLIER*preX,Constants.X2_MULTIPLIER*preY);
            image.aniReAppear(player,pane);
        }
    }
}
