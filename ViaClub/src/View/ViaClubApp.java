package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViaClubApp extends Application {
    public void start(Stage window) throws IOException
    {
        window.setTitle("VIA Club");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("VIAClubGUI.fxml"));
        Scene scene = new Scene(loader.load());
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
}
