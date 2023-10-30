package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
public class Lives {
    /**
     * Constructor for the lives that the player has
     */
    public Lives (Pane blockPane, int numberOfLives) {
        for (int i=0; i<numberOfLives;i++) {
            Image heartImage = new Image(getClass().getResourceAsStream(Constants.HEART_IMAGE));
            ImageView myImageView = new ImageView();
            myImageView.setImage(heartImage);
            myImageView.setScaleX(Constants.LIFE_SCALE);
            myImageView.setScaleY(Constants.LIFE_SCALE);
            blockPane.getChildren().add(myImageView);
            myImageView.setX(i*Constants.LIFE_MULTIPLIER-Constants.LIFE_OFFSET);
            myImageView.setY(Constants.LIFE_Y);
        }
    }
}
