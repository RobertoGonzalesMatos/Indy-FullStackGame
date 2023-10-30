package indy;

import javafx.scene.paint.Color;

public class SmallPlayer extends Player {
    /**
     * Constructor that sets up the small player
     */
    public SmallPlayer() {
        super();
        this.setX(-Constants.SMALL_OFFSET, 0, 0);
        this.setY(-Constants.SMALL_OFFSET, 0, 0);
        this.changeFill(Color.GREEN, Color.LIMEGREEN, Color.DARKGREEN);
    }
    /**
     * Method that takes the small player out of the game
     */
    public void takeOffGame(SmallPlayer smallPlayer){
        smallPlayer.setX(-Constants.SMALL_OFF_GAME,0,0);
        smallPlayer.setY(-Constants.SMALL_OFF_GAME,0,0);
    }
}
