package View;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class MatchGUIController {

    @FXML private ListView<HBox> matchList;

    public void initialize()
    {
        setListDetails();
    }

    private void setListDetails()
    {
        HBox hBox = new HBox();

        //add the player name
        hBox.getChildren().add(new Label("VIAClub vs FCSB"));

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

    public void clickMouseEvent(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println(matchList.getItems().get(1));
    }
}
