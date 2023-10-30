package indy;

import indy.PlatformPackage.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;


public class Game {
    private final Board myBoard;
    private final Player blockPlayer;
    private final SmallPlayer smallPlayer;
    private Timeline timeline;
    private boolean smallState =false;
    private boolean fall=false;
    private int[][] bluePrintBoard = new int[Constants.BOARD_WIDTH_HEIGHT][Constants.BOARD_WIDTH_HEIGHT];
    private int level;
    private Label myLabel;
    private int numberOfMoves;
    private int numberOfLives;
    private boolean gameOver = false;
    private final Keys myKeys;
    private int numberOfKeys = Constants. KEY_NUMBER_1;
    private final ArrayList<Keys> keyArray=new ArrayList<>();
    private final BlockAni playerAni;
    private boolean music;
    private int counter;
    private boolean cooldown=false;
    private boolean safeReset=false;
    /**
     * Constructor that sets up the game
     */
    public Game(Pane pane, AudioClip audio) {
        this.numberOfLives = Constants.INITIAL_LIVES;
        this.myKeys = new Keys(pane, this.numberOfKeys);
        this.myKeys.remove(pane);
        this.setUpLabel(pane);
        this.bluePrintBoard = LevelChanger.setLevel(this.bluePrintBoard,this.level);
        this.myBoard = new Board();
        this.myBoard.setUpBoard(pane, this.bluePrintBoard,this.numberOfKeys, this.keyArray, this.safeReset);
        this.smallPlayer = new SmallPlayer();
        this.blockPlayer = new Player();
        this.playerAni =new BlockAni(pane, this.blockPlayer);
        this.numberOfMoves = LevelChanger.setMoves(this.level,this.numberOfMoves);
        new Lives(pane, this.numberOfLives);
        this.timeline=new Timeline();
        this.setUpTimeline(audio, pane);
        this.playerAni.aniTimeline(this.blockPlayer,this.smallPlayer, pane);
    }
    /**
     * Method to set up the label for number of movements left and game over
     */
    public void setUpLabel(Pane Pane) {
        this.myLabel =new Label("You have "+this.numberOfMoves + " moves left!");
        this.myLabel.setTranslateX(Constants.LABEL_X);
        this.myLabel.setFont(new Font(Constants.MOVE_FONT,Constants.LABEL_SIZE));
        this.myLabel.setTextFill(Color.WHITE);
        Pane.getChildren().add(this.myLabel);
    }
    /**
     * Method for key inputs for the big and small player
     */
    public void handleKeyPress(KeyEvent e, AudioClip audio) {
        KeyCode keyPressed = e.getCode();
        if(!this.gameOver &&counter==0) {
            switch (keyPressed) {
                case LEFT:
                    if(this.smallState && BigBPlat.canMove(this.blockPlayer,-1,0)) {
                        this.playerAni.setMoveLeft(this.blockPlayer,null);
                        this.blockPlayer.smallMove(-Constants.HORIZONTAL_MOVE,0);
                    }
                    if(this.smallState &&BigBPlat.canMove(this.smallPlayer,-1,0)) {
                        this.playerAni.setMoveLeft(null,this.smallPlayer);
                        this.smallPlayer.smallMove(-Constants.HORIZONTAL_MOVE,0);
                    }
                    if(!this.smallState &&SmallBPlat.canMove(this.blockPlayer,-1,0)) {
                        this.playerAni.setMoveLeft(this.blockPlayer,null);
                        this.blockPlayer.move(-Constants.HORIZONTAL_MOVE,0);
                    }
                    this.cooldown=true;
                    this.numberOfMoves--;
                    break;
                case RIGHT:
                    if(this.smallState &&BigBPlat.canMove(this.blockPlayer,1,0)) {
                        this.playerAni.setMoveRight(this.blockPlayer,null);
                        this.blockPlayer.smallMove(Constants.HORIZONTAL_MOVE,0);
                    }
                    if(this.smallState &&BigBPlat.canMove(this.smallPlayer,1,0)) {
                        this.playerAni.setMoveRight(null,this.smallPlayer);
                        this.smallPlayer.smallMove(Constants.HORIZONTAL_MOVE,0);
                    }
                    if(!this.smallState &&SmallBPlat.canMove(this.blockPlayer,1,0)) {
                        this.playerAni.setMoveRight(this.blockPlayer, null);
                        this.blockPlayer.move(Constants.HORIZONTAL_MOVE,0);
                    }
                    this.cooldown=true;
                    this.numberOfMoves--;
                    break;
                case UP:
                    if(this.smallState &&BigBPlat.canMove(this.blockPlayer,0,-1)) {
                        this.playerAni.setMoveUp(this.blockPlayer, null);
                        this.blockPlayer.smallMove(-Constants.VERTICAL_MOVE_X,-Constants.VERTICAL_MOVE_Y);
                    }
                    if(this.smallState &&BigBPlat.canMove(this.smallPlayer,0,-1)) {
                        this.playerAni.setMoveUp(null,this.smallPlayer);
                        this.smallPlayer.smallMove(-Constants.VERTICAL_MOVE_X,-Constants.VERTICAL_MOVE_Y);
                    }
                    if(!this.smallState &&SmallBPlat.canMove(this.blockPlayer,0,-1)) {
                        this.playerAni.setMoveUp(this.blockPlayer, null);
                        this.blockPlayer.move(-Constants.VERTICAL_MOVE_X,-Constants.VERTICAL_MOVE_Y);
                    }
                    this.cooldown=true;
                    this.numberOfMoves--;
                    break;
                case DOWN:
                    if(this.smallState &&BigBPlat.canMove(this.blockPlayer,0,1)) {
                        this.playerAni.setMoveDown(this.blockPlayer,null);
                        this.blockPlayer.smallMove(Constants.VERTICAL_MOVE_X,Constants.VERTICAL_MOVE_Y);
                    }
                    if(this.smallState &&BigBPlat.canMove(this.smallPlayer,0,1)) {
                        this.playerAni.setMoveDown(null,this.smallPlayer);
                        this.smallPlayer.smallMove(Constants.VERTICAL_MOVE_X,Constants.VERTICAL_MOVE_Y);
                    }
                    if(!this.smallState &&SmallBPlat.canMove(this.blockPlayer,0,1)) {
                        this.playerAni.setMoveDown(this.blockPlayer,null);
                        this.blockPlayer.move(Constants.VERTICAL_MOVE_X,Constants.VERTICAL_MOVE_Y);
                    }
                    this.cooldown=true;
                    this.numberOfMoves--;
                    break;
                case M:
                    this.music=!this.music;
                    if(!this.music) {
                        audio.play();
                    }
                    if(this.music) {
                        audio.stop();
                    }
                    break;
            }
        }
        e.consume();
    }
    /**
     * Timeline for the game which contains methods that need to be updated
     */
    public void setUpTimeline(AudioClip audio, Pane blockPane) {
        KeyFrame blockTimeline = new KeyFrame(Duration.seconds(Constants.TIMELINE_DURATION), (ActionEvent) -> {
            /**
             * Update fall boolean and reset game when the player falls
             */
            if(!this.fall) {
                this.fall=this.myBoard.fall(this.blockPlayer, false);
            }
            if(this.fall) {
                this.blockPlayer.move(0,Constants.PLAYER_FALL);
                if(this.counter>=Constants.COUNTER_MAX||!this.cooldown) {
                    this.playerAni.fallState();
                }
            }
            if(this.blockPlayer.getY()>Constants.FALL_LIMIT) {
                restartLevel(blockPane);
            }
            /**
             * Transforms the player into the small state and sets the smallState boolean on
             */
            if(!this.fall&&!this.smallState && SeparationPlat.stateChange(this.blockPlayer, false)) {
                this.smallState = true;
                this.blockPlayer.changeFill(Color.GREEN,Color.LIMEGREEN,Color.DARKGREEN);
                SeparationPlat.smartSeparationMethod(this.blockPlayer,this.smallPlayer);
                this.blockPlayer.size(Constants.SIZE_SMALL,Constants.OFFSET_XL,
                        Constants.OFFSET_YL,-Constants.OFFSET_XT,Constants.OFFSET_YT);
                this.smallPlayer.size(Constants.SIZE_SMALL,Constants.OFFSET_XL,
                        Constants.OFFSET_YL,-Constants.OFFSET_XT,Constants.OFFSET_YT);
            }
            /**
             * Combines both small players into a big player if they are in the same platform
             * it also sets the smallState method to false.
             */
            if(this.smallState&&(this.blockPlayer.getX()==this.smallPlayer.getX())&&
                    (this.blockPlayer.getY()==this.smallPlayer.getY())) {
                this.smallPlayer.remove(blockPane);
                this.smallState = false;
                this.blockPlayer.changeFill(Color.DARKBLUE,Color.DEEPSKYBLUE,Color.BLUE);
                this.blockPlayer.size(1,Constants.BIG_OFFSET_XL,Constants.BIG_OFFSET_YL,0,0);
                this.playerAni.aniReAppear(this.blockPlayer,blockPane);
                this.counter=0;
                this.cooldown=false;
            }
            /**
             * Checks the key queue when reached the end platform
             */
            EndPlat.endQueue(this.myKeys.checkKey(this.blockPlayer,this.smallPlayer,
                    this.numberOfKeys, blockPane, this.smallState));
            /**
             * Changes the level if all the requirements are met.
             */
            if(!this.smallState &&EndPlat.endCheck(this.blockPlayer)&&EndPlat.checkQueue(this.numberOfKeys)) {
                this.level++;
                this.numberOfLives++;
                LifePlat.clearLiveArray();
                restartLevel(blockPane);
                this.playerAni.aniReAppear(this.blockPlayer,blockPane);
            }
            /**
             * Resets the game if the player rans out of movements
             */
            if(this.numberOfMoves ==-1) {
                restartLevel(blockPane);
            }
            /**
             * Updates the label
             */
            this.myLabel.setText("You have "+ numberOfMoves + " moves left!");
            if(this.numberOfLives ==0) {
                this.timeline.stop();
                this.gameOver =true;
//                audio.stop();
                this.myLabel.setText(Constants.GAME_OVER);
            }
            /**
             * Updates kay animations
             */
            this.myKeys.oscillate(this.keyArray);
            /**
             * Checks if the LifePlatform has been used
             */
            if(!this.fall) {
                this.numberOfLives = LifePlat.checkArray(this.blockPlayer,this.numberOfLives, blockPane);
                new Lives(blockPane, this.numberOfLives);
            }
            /**
             * Updates the direction for the TPPlatform
             */
            TPPlat.registerDirection(this.blockPlayer);
            /**
             * Teleports the player in the direction they were facing
             */
            TPPlat.teleport(this.blockPlayer, this.playerAni, blockPane, this.smallState);
            this.playerAni.getSmallState(this.smallState);
            /**
             * Cooldown for the key inputs
             */
            if(this.cooldown&&this.counter<Constants.COUNTER_MAX) {
                this.counter++;
            } else if (counter<=Constants.COUNTER_MAX) {
                this.counter=0;
                this.cooldown=false;
            }
            /**
             * Checks if the player won
             */
            if(this.level == Constants.LAST_LEVEL) {
                blockPane.getChildren().clear();
                blockPane.getChildren().add(this.myLabel);
                this.myLabel.setText(Constants.WIN_MESSAGE);
                this.myLabel.setFont(new Font(Constants.WIN_FONT,Constants.WIN_FONT_SIZE));
                this.myLabel.setTextFill(Color.MEDIUMVIOLETRED);
                this.myLabel.setTranslateX(Constants.WIN_LABEL_X);
                this.myLabel.setTranslateY(Constants.WIN_LABEL_Y);
            }
            /**
             * Updates the color for the small and big player platforms
             */
            this.myBoard.checkLight(this.smallState);
            /**
             * Takes player to the origin
             */
            this.smallState = OriginPlatform.returnOrigin(this.blockPlayer, this.smallPlayer, this.playerAni
                    ,blockPane,this.smallState);
        });
        this.timeline = new Timeline(blockTimeline);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }
    /**
     * Method that restarts the level
     */
    public void restartLevel(Pane blockPane) {
        blockPane.getChildren().clear();
        Keys.clearBackUpArray();
        this.safeReset=false;
        this.keyArray.clear();
        this.bluePrintBoard = LevelChanger.setLevel(this.bluePrintBoard,this.level);
        this.numberOfMoves = LevelChanger.setMoves(this.level,this.numberOfMoves);
        this.numberOfKeys = LevelChanger.setLevelKeys(this.level,this.numberOfKeys);
        this.myBoard.setUpBoard(blockPane, this.bluePrintBoard, this.numberOfKeys, this.keyArray, false);
        this.blockPlayer.reEnablePlayer();
        this.playerAni.aniReAppear(this.blockPlayer,blockPane);
        blockPane.getChildren().add(this.myLabel);
        this.numberOfLives--;
        new Lives(blockPane, this.numberOfLives);
        this.smallState =false;
        this.fall=false;
    }
    /**
     * Method that restarts the level while maintaining game properties like movements left,
     * lives, keys collected, etc.
     */
    public void safeRestartLevel(Pane blockPane) {
        blockPane.getChildren().clear();
        this.safeReset=true;
        this.bluePrintBoard = LevelChanger.setLevel(this.bluePrintBoard,this.level);
        this.numberOfKeys = LevelChanger.setLevelKeys(this.level,this.numberOfKeys);
        this.myBoard.setUpBoard(blockPane, this.bluePrintBoard, this.numberOfKeys, this.keyArray, true);
        this.playerAni.removeAni(blockPane);
        if(smallState) {
            this.blockPlayer.size(Constants.SIZE_SMALL,Constants.OFFSET_XL,
                    Constants.OFFSET_YL,-Constants.OFFSET_XT,Constants.OFFSET_YT);
            this.smallPlayer.size(Constants.SIZE_SMALL,Constants.OFFSET_XL,
                    Constants.OFFSET_YL,-Constants.OFFSET_XT,Constants.OFFSET_YT);
            this.playerAni.setUpDivision(blockPane,blockPlayer,smallPlayer);
        } else {
            this.blockPlayer.size(1,Constants.BIG_OFFSET_XL,Constants.BIG_OFFSET_YL,0,0);
            this.playerAni.aniReAppear(this.blockPlayer,blockPane);
        }
        blockPane.getChildren().add(this.myLabel);
        this.fall=false;
    }
}
