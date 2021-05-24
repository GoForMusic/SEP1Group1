package View;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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
    @FXML private Pane tabExport;
    public Label errorMessage;

    public void initialize() throws IOException {
        updateStudentsFromACountryTab();
    }


    private void updateStudentsFromACountryTab() throws IOException {
        tabPlayers.getChildren().add(new FXMLLoader().load(getClass().getResource("PlayerGUI.fxml")));
        tabMatches.getChildren().add(new FXMLLoader().load(getClass().getResource("MatchGUI.fxml")));
        tabExport.getChildren().add(new FXMLLoader().load(getClass().getResource("ExportGUI.fxml")));
    }

    public void setErrorMessage(String message)
    {
        errorMessage.setText(message);
    }


}
