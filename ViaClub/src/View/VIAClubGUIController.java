package View;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * The main interface controller that allows displaying all the interfaces
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */


public class VIAClubGUIController
{
    @FXML private Pane tabPlayers;
    @FXML private Pane tabMatches;
    @FXML private Pane tabExport;

    /**
     * This method will initialize the data when the interface was open.
     * */
    public void initialize() throws IOException {
        updateStudentsFromACountryTab();
    }

    /**
     * This method will pre-setup all the tabs on the interface and set-up other controllers to work
     * @throws IOException if they cannot load a specific fxml file
     */
    private void updateStudentsFromACountryTab() throws IOException {
        tabPlayers.getChildren().add(new FXMLLoader().load(getClass().getResource("PlayerGUI.fxml")));
        tabMatches.getChildren().add(new FXMLLoader().load(getClass().getResource("MatchGUI.fxml")));
        tabExport.getChildren().add(new FXMLLoader().load(getClass().getResource("ExportGUI.fxml")));
    }
}
