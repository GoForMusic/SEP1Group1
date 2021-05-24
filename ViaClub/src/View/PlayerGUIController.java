package View;

import Model.Player;
import Utils.VIAClubAdapter;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class PlayerGUIController {
    @FXML private DialogPane dialogPop;
    @FXML private ListView<HBox> playerList;
    @FXML private Label labelTitle;
    @FXML private TextField labelFirstName;
    @FXML private TextField labelSecondName;
    @FXML private TextField labelPlayerNumber;
    @FXML private TextField labelPreferredPosition;
    @FXML private ComboBox<String> comboPlayerStatus;
    @FXML private Button buttonCreateSave;


    private VIAClubAdapter adapter;

    public void initialize()
    {
        adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");
        comboPlayerStatus.getItems().addAll("Available","Suspended","Injured");
        setListDetails();
    }


    private void setListDetails()
    {
        playerList.getItems().clear();

        ArrayList<Player> players = adapter.getAllPlayers();

        for(Player player:players)
        {
            HBox hBox = new HBox();
            //add the player name
            hBox.getChildren().add(new Label(player.getFirstName() + " " + player.getLastName()));
            //add edit button -> TODO: will use lambda
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");
            HBox.setMargin(editButton,new Insets(0,5,0,500));

            //edit function
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                buttonCreateSave.setText("Save");
                dialogPop.setVisible(true);
                labelTitle.setText("Edit a Player");
                labelFirstName.setText(player.getFirstName());
                labelSecondName.setText(player.getLastName());
                labelPlayerNumber.setText(player.getPlayerNumber()+"");
                labelPreferredPosition.setText(player.getPreferredPosition());
                comboPlayerStatus.setValue(player.getStatus());
            });

            //add Remove button -> TODO: will use lambda
            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");

            hBox.getChildren().addAll(editButton,removeButton);
            playerList.getItems().add(hBox);
        }
    }

    //create a new Player
    @FXML void clickMouseCreateEvent(MouseEvent event) {
        buttonCreateSave.setText("Create");
        dialogPop.setVisible(true);
        labelTitle.setText("Create a Player");
    }

    //cancel the creation process
    @FXML void clickMouseCancelEvent(MouseEvent event) {
        dialogPop.setVisible(false);
        labelFirstName.clear();
        labelSecondName.clear();
        labelPlayerNumber.clear();
        labelPreferredPosition.clear();
    }

    @FXML
    void clickMouseCreateSaveEvent(MouseEvent event) {
        //TODO Warning message;
        if(labelFirstName.getText().isEmpty()||labelSecondName.getText().isEmpty()||labelPlayerNumber.getText().isEmpty()||labelPreferredPosition.getText().isEmpty())
        {
            //TODO Warning message;
            System.out.println("All fields must be filled");
        }else {
            Player localPlayer = new Player(labelFirstName.getText(), labelSecondName.getText(), Integer.parseInt(labelPlayerNumber.getText()), labelPreferredPosition.getText(), comboPlayerStatus.getValue());
            System.out.println(localPlayer);
            adapter.savePlayer(localPlayer);
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails();
        }
    }
}
