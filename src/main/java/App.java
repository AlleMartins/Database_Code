import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

import java.io.IOException;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("MyDatabase");
        Parent root = null;
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("pages/home.fxml"));

        try {
            root = loader.load();
        } catch (IOException exception){
            exception.printStackTrace();
        }

        if (primaryStage.getScene() == null) {
            assert root != null;
            primaryStage.setScene(new Scene(root));
        } else {
            primaryStage.getScene().setRoot(root);
        }

        final Home view = loader.getController();
        view.setStage(primaryStage);
        view.init();
        primaryStage.show();
    }
}
