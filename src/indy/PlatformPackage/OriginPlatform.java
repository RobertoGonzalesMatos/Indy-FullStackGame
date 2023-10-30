package indy.PlatformPackage;


import indy.BlockAni;
import indy.Constants;
import indy.Player;
import indy.SmallPlayer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OriginPlatform extends Platforms{
    /**
     * Constructor for originPlatoform class
     */
    public OriginPlatform (){
        super();
        this.setFill(Color.LIGHTSTEELBLUE);
    }
    /**
     * Method that checks if the player is in the origin platform
     */
    public static Boolean originCheck(Player player, boolean smallState) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X -
                (player.getY()-Constants.PLAYER_OFFSET_Y)) / Constants.HORIZONTAL_MOVE;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y;
        return (!(col <= -1) && !(row <= -1) && !(col >= Constants.BOARD_WIDTH_HEIGHT) &&
                !(row >= Constants.BOARD_WIDTH_HEIGHT)) &&
                Board.platCheck(row, col, Color.LIGHTSTEELBLUE, smallState);
    }
    /**
     * Method that returns the player to the origin
     */
    public static boolean returnOrigin(Player player, SmallPlayer smallPlayer, BlockAni playerImage
            , Pane pane, boolean smallState) {
        if(originCheck(player, smallState)||originCheck(smallPlayer,smallState)) {
            player.reEnablePlayer();
            playerImage.aniReAppear(player,pane);
            smallPlayer.takeOffGame(smallPlayer);
            return false;
        }
            return smallState;
    }
}
