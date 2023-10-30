package indy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * It's time for Indy! This is the main class to get things started.
 * AppClass, this opens up the Game.
 *
 */

public class App extends Application {
    public Stage title;
    @Override
    public void start(Stage stage) {
        this.setTitle(stage);
        indy.PaneOrganizer organizer = new indy.PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), indy.Constants.SCENE_WIDTH , indy.Constants.SCENE_HEIGHT);
        stage.setScene(scene);
        this.setTitle(stage);
        stage.show();
    }
    public void setTitle(Stage stage) {
        this.title = stage;
    }
    public static void main(String[] args) {
        launch(args); // launch is a method inherited from Application
    }
}
