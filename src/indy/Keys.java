package indy;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Keys {
    private final ImageView myImageView;
    private final Circle keyHitBox;
    private final Label keyNumber;
    private double yVelocity;
    private boolean oscillate=true;
    private static final HashMap<Integer,Keys> keyHash = new HashMap<>();
    private static final ArrayList<Integer> backUpKeyArrayX=new ArrayList<>();
    private static final ArrayList<Integer> backUpKeyArrayY=new ArrayList<>();
    /**
     * Constructor for the keys
     */
    public Keys(Pane blockPane, int keyNumber) {
        this.keyHitBox =new Circle(Constants.KEY_RADIUS, Color.TRANSPARENT);
        this.keyNumber = new Label();
        this.keyNumber.setFont(new Font(Constants.KEY_FONT, Constants.KEY_FONT_SIZE));
        this.keyNumber.setTextFill(Color.WHITE);
        Image keyImage = new Image(getClass().getResourceAsStream(Constants.KEY_IMAGE));
        this.myImageView =new ImageView();
        this.myImageView.setScaleX(Constants.KEY_SIZE);
        this.myImageView.setScaleY(Constants.KEY_SIZE);
        this.myImageView.setImage(keyImage);
        this.keyNumber.setText(String.valueOf(keyNumber));
        blockPane.getChildren().addAll(this.keyHitBox,this.keyNumber, this.myImageView);
    }
    /**
     * Method that generates the required amount of keys
     */
    public void keyGeneration(int numberOfKeys, Pane blockPane, ArrayList<Keys> keyArray) {
        for(int keyNumber=1;keyNumber <= numberOfKeys;keyNumber++) {
            Keys keys = new Keys(blockPane, keyNumber);
            keyHash.put(keyNumber,keys);
            keyArray.add(keys);
        }
    }
    /**
     * Method that sets the position of the keys
     */
    public void setCenter(int x, int y) {
        this.keyHitBox.setCenterX(x);
        this.keyHitBox.setCenterY(y);
        this.myImageView.setX(x-Constants.KEY_OFFSET);
        this.myImageView.setY(y-Constants.KEY_OFFSET);
        this.keyNumber.setTranslateX(x);
        this.keyNumber.setTranslateY(y-Constants.KEY_LABEL_OFFSET);
    }
    /**
     * Method that allows keys to be put into the pane if they were not grabbed
     */
    public void setKey(int x, int y,int keyNumber) {
        if(backUpKeyArrayX.contains(x-Constants.KEY_OFFSET_X)&&backUpKeyArrayY.contains(y-Constants.KEY_OFFSET_Y)) {
            keyHash.get(keyNumber).setCenter(-Constants.KEY_CENTER_OFFSET, -Constants.KEY_CENTER_OFFSET);
        }else{
            keyHash.get(keyNumber).setCenter(x, y);
            keyHash.get(keyNumber).toFront();
        }
    }
    /**
     * Method that sends the keys to the front
     */
    public void toFront() {
        this.keyHitBox.toFront();
        this.keyNumber.toFront();
        this.myImageView.toFront();
    }
    /**
     * Method that removes the keys from the pane
     */
    public void remove(Pane pane) {
        pane.getChildren().remove(this.keyHitBox);
        pane.getChildren().remove(this.keyNumber);
        pane.getChildren().remove(this.myImageView);
    }
    /**
     * Method that checks if the player intersects with the key's hit-box
     */
    public boolean intersects(Player player, SmallPlayer smallPlayer, boolean smallState) {
        if(this.keyHitBox.intersects(player.getX()+Constants.VERTICAL_MOVE_X,
                player.getY()+Constants.VERTICAL_MOVE_Y,1,1)) {
            this.storeXY(player);
            return true;
        } else {
            this.storeXY(smallPlayer);
            return smallState && this.keyHitBox.intersects(smallPlayer.getX() + Constants.VERTICAL_MOVE_X,
                    smallPlayer.getY() + Constants.VERTICAL_MOVE_Y, 1, 1);
        }
    }
    /**
     * Method that checks which key was intersected and removes is from the hashmap
     */
    public int checkKey(Player player,SmallPlayer smallPlayer,int numberOfKeys,Pane blockPane,boolean smallState) {
        for(int i=1; i<numberOfKeys+1;i++) {
            if(keyHash.get(i)!=null&& keyHash.get(i).intersects(player, smallPlayer,smallState)) {
//                keySound();
                keyHash.get(i).remove(blockPane);
                keyHash.remove(i);
                return i;
            }
        }
        return 0;
    }
    /**
     * Key animations
     */
    public void oscillate(ArrayList<Keys> keyArray) {
        if (this.yVelocity >=Constants.KEY_VEL) {
            this.oscillate=false;
        } else if (this.yVelocity <=-Constants.KEY_VEL) {
            this.oscillate=true;
        }
        if(this.oscillate) {
            this.yVelocity++;
        } else {
            this.yVelocity--;
            }
        for (Keys keys : keyArray) {
            keys.setImageY(keys.getImageY() + this.yVelocity * Constants.KEY_VEL_MULTIPLIER);
        }
    }
    /**
     * Method to change the image and label for the key animation
     */
    public void setImageY(double y) {
        this.keyNumber.setTranslateY(y+Constants.KEY_ANI_OFFSET);
        this.myImageView.setY(y);
    }
    /**
     * Getter method for the Y coordinates of the image
     */
    public double getImageY() {
       return this.myImageView.getY();
    }
    /**
     * Method that plays the sound effect
     */
    public void keySound() {
        String path = "src/indy/ImagesAndSounds/KeySound.mp3";
        Media keySoundEffect = new Media(Paths.get(path).toUri().toString());
        AudioClip keySound = new AudioClip(keySoundEffect.getSource());
        keySound.setCycleCount(1);
        keySound.play();
    }
    /**
     * Method to store the location of the keys that were grabbed by the player before the safe-reset
     */
    public static void storeXY(Player player) {
        backUpKeyArrayX.add(player.getX());
        backUpKeyArrayY.add(player.getY());
    }
    /**
     * Method that clears the arraylists
     */
    public static void clearBackUpArray() {
        backUpKeyArrayX.clear();
        backUpKeyArrayY.clear();
    }
}
