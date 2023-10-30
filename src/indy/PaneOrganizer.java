package indy;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import java.nio.file.Paths;

public class PaneOrganizer {
    private final BorderPane root;
    private final Pane blockPane;
    private final Game myGame;
    private AudioClip audio;
    /**
     * Constructor for the Pane organizer class that sets up all the elements in the pane
     */
    public PaneOrganizer() {
//        this.playMusic();
        this.root = new BorderPane();
        this.blockPane = new Pane();
        this.blockPane();
        this.myGame =new Game(this.blockPane,this.audio);
        this.buttonPane();
    }
    /**
     * Method that sets the button pane with the quit, reset and, safe reset button.
     */
    public void buttonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setPrefSize(Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        this.root.setBottom(buttonPane);
        buttonPane.setStyle(String.valueOf(Color.TRANSPARENT));
        /**
         * Code for the quit button
         */
        Button quitButton = new Button("Quit");
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setFocusTraversable(false);
        quitButton.setFocusTraversable(false);
        quitButton.setOnAction(mouseEvent -> System.exit(0));
        /**
         * Code for the Restart button
         */
        Button resetButton = new Button("Restart");
        resetButton.setFocusTraversable(false);
        resetButton.setOnAction(mouseEvent -> this.myGame.restartLevel(this.blockPane));
        /**
         * Code for the safe restart button
         */
        Button safeResetButton = new Button("antiLag");
        buttonPane.getChildren().addAll(quitButton, resetButton, safeResetButton);
        safeResetButton.setFocusTraversable(false);
        buttonPane.setSpacing(Constants.BUTTON_SPACING);
        safeResetButton.setOnAction(mouseEvent -> this.myGame.safeRestartLevel(this.blockPane));
    }
    /**
     * Method that sets up the music in the game
     */
    public void playMusic() {
        String path = "src/indy/ImagesAndSounds/Ephemeral.mp3";
        Media musicMedia = new Media(Paths.get(path).toUri().toString());
        this.audio = new AudioClip(musicMedia.getSource());
        this.audio.setCycleCount(AudioClip.INDEFINITE);
        audio.play();
    }
    /**
     * Method that sets the blockPane
     */
    public void blockPane() {
        Image background = new Image(getClass().getResourceAsStream(Constants.BG_IMAGE));
        ImageView imageView = new ImageView();
        imageView.setScaleX(Constants.BG_SCALE);
        imageView.setScaleY(Constants.BG_SCALE);
        imageView.setX(-Constants.BG_X);
        imageView.setY(-Constants.BG_Y);
        imageView.setRotate(Constants.BG_ROTATE);
        imageView.setImage(background);
        this.root.getChildren().add(imageView);
        this.root.setCenter(this.blockPane);
        this.blockPane.setOnKeyPressed((KeyEvent e) -> this.myGame.handleKeyPress(e, this.audio));
        this.blockPane.setFocusTraversable(true);
    }
    /**
     * Getter method for the borderpane
     */
    public BorderPane getRoot() {
        return this.root;
    }
}
