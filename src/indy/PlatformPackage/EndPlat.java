package indy.PlatformPackage;

import indy.Constants;
import indy.Player;
import indy.Queue;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class EndPlat extends Platforms {
    private static Queue myQueue = new Queue();
    /**
     * Constructor for endPlatform class
     */
    public EndPlat() {
        super();
        this.setFill(Color.RED);
    }
    /**
     * Method that checks if the player is in the end platform
     */
    public static Boolean endCheck(Player player) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X - (player.getY() -
                Constants.PLAYER_OFFSET_Y)) / Constants.HORIZONTAL_MOVE;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y;
        return (!(col <= -1) && !(row <= -1) && !(col >= Constants.BOARD_WIDTH_HEIGHT)
                && !(row >= Constants.BOARD_WIDTH_HEIGHT)) &&
                Board.platCheck(row, col, Color.RED, false);
    }
    /**
     * Method that takes out keys from the end queue
     */
    public static void endQueue(int keyNumber) {
        if(!myQueue.contains(keyNumber)) {
            myQueue.enqueue(keyNumber);
        }
    }
    /**
     * Method that checks the queue to allow the level to end
     */
    public static Boolean checkQueue(int numberOfkeys) {
        boolean check = false;
        ArrayList<Integer> keyArray = new ArrayList();

        for(int i=1; i<numberOfkeys+1;i++) {
            keyArray.add(i);
        }
        for(int i=0; i<numberOfkeys;i++) {
            if(myQueue.dequeue()==keyArray.get(i)) {
                check=true;
            } else {
                return false;
            }
        }
        return check;
    }
}
