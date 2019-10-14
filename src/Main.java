import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    final int WINDOW_WIDTH = 1200;
    final int WINDOW_HEIGHT = 680;

    private Stage window;
    private Scene menuScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        menuScene = Menu.getMenuScene(this, window);

        window.setTitle("MindTouch");
        window.setOnCloseRequest(e -> closeProgram());
        window.setScene(menuScene);
        window.show();
    }

    void goToMenu() {
        window.setScene(menuScene);
    }

    private void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
