import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Objects;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;

        Button button1 = new Button();
        button1.setText("PLAY");
        button1.setOnAction(e -> window.setScene(new Game(this).getGameScene()));

        Button button2 = new Button();
        button2.setText("Too scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        Button button3 = new Button();
        button3.setText("Alert Test");
        button3.setOnAction(e -> AlertBox.display("awesome", "not"));

        Label label1 = new Label("hey scene 1");

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setTitle("MindTouch");
        window.setOnCloseRequest(e -> closeProgram());
        window.setScene(scene1);
        window.show();
    }

    void goToMenu(){
        window.setScene(scene1);
    }

    private void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
