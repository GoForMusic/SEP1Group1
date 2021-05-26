package View;

import Utils.VIAClubModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


/**
 * The Export GUI controller allow to export all the matches from system into a XML file using the xstream lib
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class ExportGUIController {

    private VIAClubModelManager manager;

    /**
     * This method will initialize the VIAClub adapter.
     * */
    public void initialize()
    {
        manager = new VIAClubModelManager("players.bin","matches.bin","matches.xml");
    }

    /**
     * This method will export into XML file a list with all the matches.
     */
    @FXML void exportAction(MouseEvent event) {
        manager.exportMatches(manager.getAllMatches());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The match list have been exported!");
        alert.showAndWait();
    }
}
