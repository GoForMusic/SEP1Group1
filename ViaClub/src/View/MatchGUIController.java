package View;

import Model.Match;
import Model.Player;
import Utils.VIAClubAdapter;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class MatchGUIController {

    @FXML private ListView<HBox> matchList;
    private VIAClubAdapter adapter;

    public void initialize()
    {
        adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");
        setListDetails();
    }

    private void setListDetails()
    {
        ArrayList<Match> matches = adapter.getAllMatches();

        for(Match match:matches) {
            HBox hBox = new HBox();

            //add the player name
            hBox.getChildren().add(new Label(match.getTeam1()+" vs "+match.getTeam2()));

            //add edit button -> TODO: will use lambda
            Button editButton = new Button("Edit");
            editButton.setStyle("-fx-background-color: #1bb148;");
            HBox.setMargin(editButton,new Insets(0,5,0,500));

            //add Remove button -> TODO: will use lambda
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #b1321b;");

            hBox.getChildren().addAll(editButton,removeButton);
            matchList.getItems().add(hBox);
        }
    }

    public void clickMouseEvent(javafx.scene.input.MouseEvent mouseEvent) {

    }
}
