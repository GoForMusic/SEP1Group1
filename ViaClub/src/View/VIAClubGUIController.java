package View;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * The user interface controller that allows displaying and modifying information
 * @author Adrian
 * @version 1.0
 */



public class VIAClubGUIController
{


    @FXML private Tab allPlayerTab;
    @FXML private Pane tabPlayers;
    @FXML private Pane tabMatches;

    public void initialize() throws IOException {
        updateStudentsFromACountryTab();
    }


    private void updateStudentsFromACountryTab() throws IOException {
        tabPlayers.getChildren().add(new FXMLLoader().load(getClass().getResource("PlayerGUI.fxml")));
        tabMatches.getChildren().add(new FXMLLoader().load(getClass().getResource("MatchGUI.fxml")));
    }


}
