import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    final int WINDOW_WIDTH = 1200;
    final int WINDOW_HEIGHT = 680;

    private Stage window;
    private Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Group root = new Group();
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        scene1 = new Scene(root);

        scene1.setOnMouseClicked(e -> window.setScene(new Game(this).getGameScene()));

        window.setTitle("MindTouch");
        window.setOnCloseRequest(e -> closeProgram());
        window.setScene(scene1);
        window.show();
        drawMenu(canvas);
    }

    void goToMenu() {
        window.setScene(scene1);
    }

    private void drawMenu(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_WIDTH);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WINDOW_WIDTH, WINDOW_WIDTH);
        gc.strokeRect(300, 150, 600, 100);
        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gc.setFill(Color.BLACK);
        gc.fillText("PLAY", 500, 225);
        gc.strokeRect(300, 350, 600, 100);
        gc.setFill(Color.BLACK);
        gc.fillText("LEVELS", 460, 425);
    }

    private void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
