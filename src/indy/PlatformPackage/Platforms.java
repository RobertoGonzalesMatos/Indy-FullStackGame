package indy.PlatformPackage;


import indy.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Platforms {
    private final Polygon platform;
    /**
     * Constructor for the base platform
     */
    public Platforms() {
        this.platform = new Polygon(0,0, Constants.PLAT_POINT_1,0,Constants.PLAT_POINT_2,
                Constants.PLAT_POINT_4,Constants.PLAT_POINT_3,Constants.PLAT_POINT_4);
        this.setFill(Color.MEDIUMVIOLETRED);
    }
    /**
     * Method to set the X coordinates of the platform
     */
    public void setX(double x) {
        this.platform.setLayoutX(x);
    }
    /**
     * Method to set the Y coordinates of the platform
     */
    public void setY(double y) {
        this.platform.setLayoutY(y);
    }
    /**
     * Method to set the color of the platform
     */
    public void setFill(Color color) {
        this.platform.setFill(color);
    }
    /**
     * Method to add the platform to the pane
     */
    public void addToPane(Pane blockPane) {
        blockPane.getChildren().add(this.platform);
    }
    /**
     * Method to get the color of a platform
     */
    public Color getFill() {
        return (Color)this.platform.getFill();
    }
    /**
     * Method that will be overwritten to light up the small and big player platforms
     */
    public void lightUp(boolean SmallState) {
    }
}
