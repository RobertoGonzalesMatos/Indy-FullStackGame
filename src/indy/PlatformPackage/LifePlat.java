package indy.PlatformPackage;


import indy.App;
import indy.Constants;
import indy.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class LifePlat extends Platforms {
    private static final ArrayList<Integer> liveXArray=new ArrayList<>();
    private static final ArrayList<Integer> liveYArray=new ArrayList<>();
    private static ImageView imageView;
    /**
     * Constructor for the LifePlatform class
     */
    public LifePlat() {
        super();
        this.setFill(Color.LIGHTCORAL);
    }
    /**
     * Method that records the X and Y coordinates of the platforms that have been used
     */
    public static void storeXY(Player player) {
       liveXArray.add(player.getX());
       liveYArray.add(player.getY());
    }
    /**
     * Method that checks if the platform has already been used
     */
    public static int checkArray(Player player, int numberOfLifes, Pane pane) {
        if(liveIntersects(player)&& liveXArray.isEmpty()){
            storeXY(player);
            pane.getChildren().remove(imageView);
            numberOfLifes++;
        } else {
            for (int i = 1; i< liveXArray.size()+1; i++) {
                if(liveIntersects(player)&&!liveXArray.contains(player.getX())&&!liveYArray.contains(player.getY())) {
                    storeXY(player);
                    pane.getChildren().remove(imageView);
                    numberOfLifes++;
                }
            }
        }
        return numberOfLifes;
    }
    /**
     * Method that checks if the platform is intersecting with the player
     */
    public static boolean liveIntersects(Player player) {
        int col = (player.getX() - Constants.PLAYER_OFFSET_X-((player.getY()-Constants.PLAYER_OFFSET_Y)*
                Constants.VERTICAL_MOVE_X)/Constants.VERTICAL_MOVE_Y) / Constants.HORIZONTAL_MOVE;
        int row = (player.getY() - Constants.PLAYER_OFFSET_Y) / Constants.VERTICAL_MOVE_Y;
        return (row != -1 && col != -1 && row != Constants.BOARD_WIDTH_HEIGHT && col != Constants.BOARD_WIDTH_HEIGHT)
                && Board.platCheck(row, col, Color.LIGHTCORAL, false);
    }
    /**
     * Method that clears the Arraylists with the coordinates of the used platforms
     */
    public static void clearLiveArray() {
        liveXArray.clear();
        liveYArray.clear();
    }
    /**
     * Method that puts a heart on platforms that haven't been used by the player
     */
    public static void heartSetUp(Pane pane, int x, int y) {
        if(!liveXArray.contains(x + Constants.LIFE_OFFSET_X)&&!liveYArray.contains(y-Constants.LIFE_OFFSET_Y)) {
            Image life = new Image(App.class.getResourceAsStream(Constants.HEART_IMAGE));
            imageView=new ImageView();
            imageView.setImage(life);
            imageView.setScaleX(Constants.LIFE_SCALE_PLAT);
            imageView.setScaleY(Constants.LIFE_SCALE_PLAT);
            pane.getChildren().add(imageView);
            imageView.setX(x);
            imageView.setY(y);
            imageView.toFront();
        }
    }
}
