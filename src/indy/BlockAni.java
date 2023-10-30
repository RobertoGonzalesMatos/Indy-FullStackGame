package indy;

import indy.PlatformPackage.SeparationPlat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BlockAni {
    private Image imagePlayer1;
    private final ImageView myImageView;
    private ImageView smallImageView;
    private boolean moveRight = false;
    private int rightCounter;
    private boolean moveLeft = false;
    private int leftCounter;
    private boolean moveDown = false;
    private double downCounter;
    private boolean moveUp = false;
    private double upCounter;
    private int baseY;
    private boolean smallMoveRight = false;
    private int smallRightCounter;
    private boolean smallMoveLeft = false;
    private int smallLeftCounter;
    private boolean smallMoveDown = false;
    private double smallDownCounter;
    private boolean smallMoveUp = false;
    private double smallUpCounter;
    private int smallBaseY;
    private int counter;
    private boolean separation = false;
    private boolean smallState = false;
    /**
     * This is the constructor of the AnimationClass.
     */
    public BlockAni(Pane pane, Player player) {
        this.imagePlayer1 =new Image(getClass().getResourceAsStream(Constants.BIG_IMAGE));
        this.myImageView =new ImageView();
        this.myImageView.setImage(this.imagePlayer1);
        this.setUpImage(player);
        pane.getChildren().add(this.myImageView);
    }
    /**
     * This sets up the image for the Player in the Big State.
     */
    public void setUpImage(Player player) {
        this.myImageView.setScaleX(Constants.SIZE_BIG_STATE);
        this.myImageView.setScaleY(Constants.SIZE_BIG_STATE);
        this.myImageView.setX(player.getX()-Constants.OFFSET_IMAGE_X);
        this.myImageView.setY(player.getY()-Constants.OFFSET_IMAGE_Y);
    }
    /**
     * This sets the base point for the Y axis for the parabola.
     */
    public void setBaseY(Player player, boolean move) {
        if(!move) {
                this.baseY=player.getY();
        }
    }
    /**
     * This sets the base point for the Y axis for the parabola for the small block.
     */
    public void setSmallBaseY(SmallPlayer smallPlayer, boolean move) {
        if(!move) {
            this.smallBaseY=smallPlayer.getY();
        }
    }
    /**
     * Method that enables the move right Animation.
     */
    public void setMoveRight(Player player, SmallPlayer smallPlayer) {
        if(player!=null) {
            setBaseY(player, this.moveRight);
            this.moveRight=true;
        }
        if(smallPlayer!=null) {
            setSmallBaseY(smallPlayer,this.smallMoveRight);
            this.smallMoveRight =true;
        }
    }
    /**
     * Method that enables the move left Animation.
     */
    public void setMoveLeft(Player player, SmallPlayer smallPlayer) {
        if(player!=null) {
            setBaseY(player, this.moveLeft);
            this.moveLeft=true;
        }
        if(smallPlayer!=null) {
            setSmallBaseY(smallPlayer,this.smallMoveLeft);
            this.smallMoveLeft =true;
        }
    }
    /**
     * Method that enables the move down Animation.
     */
    public void setMoveDown(Player player, SmallPlayer smallPlayer) {
        if(player!=null) {
            setBaseY(player, this.moveDown);
            this.moveDown=true;
        }
        if(smallPlayer!=null) {
            setSmallBaseY(smallPlayer,this.smallMoveDown);
            this.smallMoveDown =true;
        }
    }
    /**
     * Method that enables the move up Animation.
     */
    public void setMoveUp(Player player, SmallPlayer smallPlayer) {
        if(player!=null) {
            setBaseY(player, this.moveUp);
            this.moveUp=true;
        }
        if(smallPlayer!=null) {
            setSmallBaseY(smallPlayer,this.smallMoveUp);
            this.smallMoveUp =true;
        }
    }
    /**
     * Move right animation for the big player
     */
    public void rightAni() {
        if(this.moveRight&&!(this.rightCounter >=Constants.X_AXIS_COUNTER)) {
            aniMove(1, ((Constants.X_AXIS_PARABOLA*(this.rightCounter)*(this.rightCounter)-
                    (this.rightCounter))-Constants.X_PARABOLA_OFFSET+(this.baseY-Constants.PLAYER_OFFSET_Y)));
            this.rightCounter +=1;
        } else if (this.rightCounter <=Constants.X_AXIS_COUNTER) {
            this.rightCounter =0;
            this.moveRight=false;
        }
    }
    /**
     * Move right animation for the big small
     */
    public void smallRightAni() {
        if(this.smallMoveRight &&!(this.smallRightCounter >=Constants.X_AXIS_COUNTER)) {
            smallAniMove(1, ((Constants.X_AXIS_PARABOLA*(this.smallRightCounter)*(this.smallRightCounter)-
                    (this.smallRightCounter))-Constants.X_PARABOLA_OFFSET
                    +(this.smallBaseY -Constants.PLAYER_OFFSET_Y)));
            this.smallRightCounter +=1;
        } else if (this.smallRightCounter <=Constants.X_AXIS_COUNTER) {
            this.smallRightCounter =0;
            this.smallMoveRight =false;
        }
    }
    /**
     * Move left animation for the big player
     */
    public void leftAni(){
        if(this.moveLeft&&!(this.leftCounter >=Constants.X_AXIS_COUNTER)) {
            aniMove(-1, ((Constants.X_AXIS_PARABOLA*(this.leftCounter)*(this.leftCounter))-(this.leftCounter))-
                    Constants.X_PARABOLA_OFFSET+(this.baseY-Constants.PLAYER_OFFSET_Y));
            this.leftCounter +=1;
        } else if (leftCounter <=Constants.X_AXIS_COUNTER){
            this.leftCounter =0;
            this.moveLeft=false;
        }
    }
    /**
     * Move left animation for the small player
     */
    public void smallLeftAni() {
        if(this.smallMoveLeft &&!(this.smallLeftCounter >=Constants.X_AXIS_COUNTER)) {
            smallAniMove(-1, ((Constants.X_AXIS_PARABOLA*(this.smallLeftCounter)*(this.smallLeftCounter))-
                    (this.smallLeftCounter))-Constants.X_PARABOLA_OFFSET+(this.smallBaseY -Constants.PLAYER_OFFSET_Y));
            this.smallLeftCounter +=1;
        } else if (smallLeftCounter <=Constants.X_AXIS_COUNTER) {
            this.smallLeftCounter =0;
            this.smallMoveLeft =false;
        }
    }
    /**
     * Move up animation for the big player
     */
    public void upAni() {
        if(this.moveUp&&!(this.upCounter >=Constants.Y_AXIS_COUNTER)) {
            aniMove(-0.5, (int) ((Constants.Y_AXIS_PARABOLA*
                    (this.upCounter-Constants.UP_PARABOLA_OFFSET)*(this.upCounter -Constants.UP_PARABOLA_OFFSET))-
                    Constants.Y_AXIS_PARABOLA2*(this.upCounter-Constants.UP_PARABOLA_OFFSET))-
                    Constants.Y_PARABOLA_OFFSET+(this.baseY-Constants.PLAYER_OFFSET_Y));
            this.upCounter +=0.5;
        } else if (upCounter <=Constants.Y_AXIS_COUNTER) {
            this.upCounter =0;
            this.moveUp=false;
        }
    }
    /**
     * Move up animation for the small player
     */
    public void smallUpAni(){
        if(this.smallMoveUp &&!(this.smallUpCounter >=Constants.Y_AXIS_COUNTER)) {
            smallAniMove(-0.5, (int) ((Constants.Y_AXIS_PARABOLA*
                    (this.smallUpCounter-Constants.UP_PARABOLA_OFFSET)*
                    (this.smallUpCounter-Constants.UP_PARABOLA_OFFSET))-
                    Constants.Y_AXIS_PARABOLA2*(this.smallUpCounter-
                    Constants.UP_PARABOLA_OFFSET))-Constants.Y_PARABOLA_OFFSET
                    +(this.smallBaseY -Constants.PLAYER_OFFSET_Y));
            this.smallUpCounter +=0.5;
        } else if (smallUpCounter <=Constants.Y_AXIS_COUNTER) {
            this.smallUpCounter =0;
            this.smallMoveUp =false;
        }
    }
    /**
     * Move down animation for the big player
     */
    public void downAni(){
        if(this.moveDown&&!(this.downCounter >=Constants.Y_AXIS_COUNTER)) {
            aniMove(0.5, (int) ((Constants.Y_AXIS_PARABOLA*(this.downCounter)*(this.downCounter))-
                    Constants.Y_AXIS_PARABOLA2*(this.downCounter))-
                    Constants.DOWN_PARABOLA_OFFSET+(this.baseY-Constants.PLAYER_OFFSET_Y));
            this.downCounter +=0.5;
        } else if (downCounter <=Constants.Y_AXIS_COUNTER) {
            this.downCounter =0;
            this.moveDown=false;
        }
    }
    /**
     * Move down animation for the small player
     */
    public void smallDownAni(){
        if(this.smallMoveDown &&!(this.smallDownCounter >=Constants.Y_AXIS_COUNTER)) {
            smallAniMove(0.5, (int)((Constants.Y_AXIS_PARABOLA*(this.smallDownCounter)
                    *(this.smallDownCounter))-Constants.Y_AXIS_PARABOLA2*(this.smallDownCounter))-
                    Constants.DOWN_PARABOLA_OFFSET+(this.smallBaseY-Constants.PLAYER_OFFSET_Y));
            this.smallDownCounter +=0.5;
        } else if (smallDownCounter <=Constants.Y_AXIS_COUNTER) {
            this.smallDownCounter =0;
            this.smallMoveDown =false;
        }
    }
    /**
     * Method to move the player image
     */
    public void aniMove(double x, double y) {
        this.myImageView.setX(this.myImageView.getX()+x);
        this.myImageView.setY(y);
    }
    /**
     * Method to move the player image
     */
    public void smallAniMove(double x, double y) {
        this.smallImageView.setX(this.smallImageView.getX()+x);
        this.smallImageView.setY(y);
    }
    /**
     * Timeline for the animations
     */
    public void aniTimeline(Player player, SmallPlayer smallPlayer, Pane pane) {
        KeyFrame blockTimeline = new KeyFrame(Duration.seconds(Constants.ANI_TIMELINE_DURATION), (ActionEvent) -> {
            /**
             * Trigger the separation image method after a certain amount of time
             */
            if(!this.smallState && SeparationPlat.intersects(player,0,0, Color.ORANGE)){
                this.separation =true;
            }
            if(separation) {
                this.counter++;
                if(counter>=Constants.X_AXIS_COUNTER) {
                    this.separation =false;
                    this.counter=0;
                    pane.getChildren().remove(myImageView);
                    this.setUpDivision(pane,player,smallPlayer);
                }
            }
            this.rightAni();
            this.leftAni();
            this.downAni();
            this.upAni();
            this.smallRightAni();
            this.smallLeftAni();
            this.smallDownAni();
            this.smallUpAni();
        });
        Timeline timeline = new Timeline(blockTimeline);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    /**
     * Method set the player image back to the big State
     */
    public void aniReAppear(Player player, Pane pane) {
        pane.getChildren().removeAll(myImageView, smallImageView);
        this.imagePlayer1 =new Image(getClass().getResourceAsStream(Constants.BIG_IMAGE));
        this.myImageView.setImage(this.imagePlayer1);
        this.setUpImage(player);
        pane.getChildren().add(this.myImageView);
        this.rightCounter =Constants.X_AXIS_COUNTER;
        this.leftCounter =Constants.X_AXIS_COUNTER;
        this.downCounter =Constants.Y_AXIS_COUNTER;
        this.upCounter =Constants.Y_AXIS_COUNTER;
    }
    /**
     * Method to set up the image for the small state
     */
    public void smallSetUpImage(Player player,SmallPlayer smallPlayer) {
        this.smallImageView.setScaleX(Constants.SMALL_SIZE);
        this.smallImageView.setScaleY(Constants.SMALL_SIZE);
        this.smallImageView.setX(smallPlayer.getX()-Constants.OFFSET_IMAGE_X);
        this.smallImageView.setY(smallPlayer.getY()-Constants.OFFSET_IMAGE_Y);
        this.myImageView.setScaleX(Constants.SMALL_SIZE);
        this.myImageView.setScaleY(Constants.SMALL_SIZE);
        this.myImageView.setX(player.getX()-Constants.OFFSET_IMAGE_X);
        this.myImageView.setY(player.getY()-Constants.OFFSET_IMAGE_Y);
    }
    /**
     * Getter method for the smallState boolean
     */
    public void getSmallState(boolean smallBlock) {
        this.smallState = smallBlock;
    }
    /**
     * Falling animation
     */
    public void fallState() {
        this.myImageView.setY(this.myImageView.getY()+Constants.FALL_ANI);
        this.myImageView.toBack();
    }
    /**
     * Method that sets up the change between the big and small player
     */
    public void setUpDivision(Pane blockPane, Player player, SmallPlayer smallPlayer){
        this.imagePlayer1 = new Image(getClass().getResourceAsStream(Constants.SMALL_IMAGE));
        this.myImageView.setImage(this.imagePlayer1);
        blockPane.getChildren().add(this.myImageView);

        Image imagePlayer2 = new Image(getClass().getResourceAsStream(Constants.SMALL_IMAGE));
        this.smallImageView =new ImageView();
        this.smallImageView.setImage(imagePlayer2);
        blockPane.getChildren().add(this.smallImageView);
        this.smallSetUpImage(player,smallPlayer);
    }
    /**
     * Method to remove the player image.
     */
    public void removeAni(Pane blockPane) {
        if(smallState) {
            blockPane.getChildren().remove(smallImageView);
        }
        blockPane.getChildren().remove(myImageView);
    }
}
