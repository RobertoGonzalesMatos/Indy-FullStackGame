package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Player {
    private final Polygon playerTopSide;
    private final Polygon playerRightSide;
    private final Polygon playerLeftSide;
    /**
     * Constructor for the logical part of the player
     */
    public Player() {
        this.playerRightSide = new Polygon(0,0,Constants.POLYGON_POINT_1,Constants.POLYGON_POINT_2
                ,Constants.POLYGON_POINT_1,Constants.POLYGON_POINT_3,0,Constants.POLYGON_POINT_4);
        this.playerRightSide.setFill(Color.DARKBLUE);

        this.playerLeftSide = new Polygon(Constants.POLYGON_POINT_1,Constants.POLYGON_POINT_2,
                Constants.POLYGON_POINT_5,Constants.POLYGON_POINT_2,Constants.POLYGON_POINT_5,
                Constants.POLYGON_POINT_3,Constants.POLYGON_POINT_1,Constants.POLYGON_POINT_3);
        this.playerLeftSide.setFill(Color.DEEPSKYBLUE);

        this.playerTopSide = new Polygon(0,0,Constants.POLYGON_POINT_6,0,Constants.POLYGON_POINT_5,
                Constants.POLYGON_POINT_2,Constants.POLYGON_POINT_1,Constants.POLYGON_POINT_2);
        this.playerTopSide.setFill(Color.BLUE);

        this.setX(Constants.PLAYER_OFFSET_X,0,0);
        this.setY(Constants.PLAYER_OFFSET_Y,0,0);
    }
    /**
     * Method that sets the logical X location of the player
     */
    public void setX(int x, int offsetL, int offsetT) {
        this.playerRightSide.setLayoutX(x);
        this.playerLeftSide.setLayoutX(x+offsetL);
        this.playerTopSide.setLayoutX(x+offsetT);
    }
    /**
     * Method that sets the logical Y location of the player
     */
    public void setY(int y, int yOffsetLeft, int yOffsetTop) {
        this.playerRightSide.setLayoutY(y);
        this.playerLeftSide.setLayoutY(y+yOffsetLeft);
        this.playerTopSide.setLayoutY(y+yOffsetTop);
    }
    /**
     * Method that gets the logical X location of the player
     */
    public int getX() {
        return (int)this.playerRightSide.getLayoutX();
    }
    /**
     * Method that gets the logical Y location of the player
     */
    public int getY() {
        return (int)this.playerRightSide.getLayoutY();
    }
    /**
     * Method that moves the player
     */
    public void move(int x, int y) {
        this.setX((int) this.playerRightSide.getLayoutX()+x,0,0);
        this.setY((int) this.playerRightSide.getLayoutY()+y,0,0);
    }
    /**
     * Method that moves the small player
     */
    public void smallMove(int x, int y) {
        this.setX((int) this.playerRightSide.getLayoutX()+x,-Constants.OFFSET_L,-Constants.OFFSET_T);
        this.setY((int) this.playerRightSide.getLayoutY()+y,-Constants.OFFSET_L_Y,Constants.OFFSET_T_Y);
    }
    /**
     * Method that removes the logical player from the game
     */
    public void remove(Pane blockPane) {
        blockPane.getChildren().removeAll(this.playerRightSide,this.playerLeftSide,this.playerTopSide);
    }
    /**
     * Method to Change the color of the player
     */
    public void changeFill(Color rightColor, Color leftColor, Color topColor) {
        this.playerRightSide.setFill(rightColor);
        this.playerLeftSide.setFill(leftColor);
        this.playerTopSide.setFill(topColor);
    }
    /**
     * Method that changes the size of the player
     */
    public void size(double  size, double OffsetXL, double OffsetYL, double OffsetXT, double OffsetYT) {
        this.playerRightSide.setScaleX(size);
        this.playerLeftSide.setScaleX(size);
        this.playerTopSide.setScaleX(size);
        this.playerRightSide.setScaleY(size);
        this.playerLeftSide.setScaleY(size);
        this.playerTopSide.setScaleY(size);
        this.playerRightSide.relocate(getX(),getY());
        this.playerLeftSide.relocate(getX()+OffsetXL,getY()+OffsetYL);
        this.playerTopSide.relocate(getX()+OffsetXT,getY()+OffsetYT);
    }
    /**
     * Method that re-enables the player when reset.
     */
    public void reEnablePlayer() {
        this.size(1,Constants.BIG_OFFSET_XL,Constants.BIG_OFFSET_YL,0,0);
        this.changeFill(Color.DARKBLUE,Color.DEEPSKYBLUE, Color.BLUE);
        this.setX(Constants.PLAYER_OFFSET_X,0,0);
        this.setY(Constants.PLAYER_OFFSET_Y,0,0);
    }
}
