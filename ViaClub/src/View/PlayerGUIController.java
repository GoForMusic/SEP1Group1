package View;

import Model.Player;
import Utils.VIAClubAdapter;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The Player GUI controller allow to see and modify details
 * about players who are inside the VIAClub system (file system)
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class PlayerGUIController {
    @FXML private DialogPane dialogPop;
    @FXML private ListView<HBox> playerList;
    @FXML private Label labelTitle;
    @FXML private TextField labelFirstName;
    @FXML private TextField labelSecondName;
    @FXML private TextField labelPlayerNumber;
    @FXML private TextField labelPreferredPosition;
    @FXML private ComboBox<String> comboPlayerStatus;
    @FXML private Button buttonCreate;
    @FXML private Button buttonSave;
    @FXML private ComboBox<String> playerType;


    private VIAClubAdapter adapter;
    private ArrayList<Player> players;
    private int playerIndex;


    /**
    * This method will initialize the data when the player interface is opened.
    * Firstly, declares the adaptor object of type VIAClubAdaptor and a global variable of type int called playerIndex
    * Also, this method will load and set all the players in a list view
    * */
    public void initialize()
    {
        playerIndex=0;
        adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");
        players = adapter.getAllPlayers();
        playerType.getItems().addAll("All","Available","Suspended","Injured");
        playerType.getSelectionModel().selectFirst();
        comboPlayerStatus.getItems().addAll("Available","Suspended","Injured");
        setListDetails(adapter.getAllPlayers());
    }

    /**
    * A method which loads all the players stored in the file on the list view
    * First, will clear the whole list, this function have the role of the reset the list and populate it again
    * */
    private void setListDetails(ArrayList<Player> list)
    {
        playerList.getItems().clear();
        players = list;

        for(Player player:players)
        {
            HBox hBox = new HBox();
            //add the player first name and last name on the list
            hBox.getChildren().add(new Label(player.getFirstName() + " " + player.getLastName()));
            //add edit button
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");
            HBox.setMargin(editButton,new Insets(0,5,0,500));

            //edit player button event handler using lambda method
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialogPop.setVisible(true);
                buttonSave.setVisible(false);
                labelTitle.setText("Edit a Player");
                labelFirstName.setText(player.getFirstName());
                labelSecondName.setText(player.getLastName());
                labelPlayerNumber.setText(player.getPlayerNumber()+"");
                labelPreferredPosition.setText(player.getPreferredPosition());
                comboPlayerStatus.setValue(player.getStatus());
                buttonSave.setVisible(true);
                playerIndex=players.indexOf(player);
            });

            //add Remove button
            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");
            //remove player button event handler using lambda method
            removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{
                adapter.removePlayer(player);
                setListDetails(adapter.getAllPlayers());
            });

            //add the buttons on HBox and after populate the playerList with the hBox
            hBox.getChildren().addAll(editButton,removeButton);
            playerList.getItems().add(hBox);
        }
    }

    /**
    *   This method will set visibility of a dialogBox where you can create a new player
    * */
    @FXML void clickMouseCreateEvent(MouseEvent event) {
        buttonCreate.setVisible(true);
        dialogPop.setVisible(true);
        labelTitle.setText("Create a Player");
    }

    /**
     *   This method will hid the dialogBox and will clear all the cache memory of the input fields
     * */
    @FXML void clickMouseCancelEvent(MouseEvent event) {
        buttonCreate.setVisible(false);
        buttonSave.setVisible(false);
        dialogPop.setVisible(false);
        labelFirstName.clear();
        labelSecondName.clear();
        labelPlayerNumber.clear();
        labelPreferredPosition.clear();
    }


    /**
    * This method will save all the modification of a player and will show up a message if there is any error or everything have been saved successfully.
    * After that the method will call for an update of the player list.
    * */
    @FXML void clickMouseSaveEventButton(MouseEvent event) {
        if (labelFirstName.getText().isEmpty() || labelSecondName.getText().isEmpty() || labelPlayerNumber.getText().isEmpty() || labelPreferredPosition.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        } else {
            Player localPlayer = new Player(labelFirstName.getText(), labelSecondName.getText(), Integer.parseInt(labelPlayerNumber.getText()), labelPreferredPosition.getText(), comboPlayerStatus.getValue());

            //edit the object on the specific index who will be stored in the lambda function
            adapter.editPlayer(localPlayer,playerIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The player new details have been saved!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(adapter.getAllPlayers());
            clickMouseCancelEvent(event);
        }
    }


    /**
     * This method will create a new player and will show up a message if there is any error or everything have been saved successfully.
     * After that the method will call for an update of the player list.
     * */
    @FXML void clickMouseCreateEventButton(MouseEvent event) {
        if(labelFirstName.getText().isEmpty()||labelSecondName.getText().isEmpty()||labelPlayerNumber.getText().isEmpty()||labelPreferredPosition.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {
            Player localPlayer = new Player(labelFirstName.getText(), labelSecondName.getText(), Integer.parseInt(labelPlayerNumber.getText()), labelPreferredPosition.getText(), comboPlayerStatus.getValue());
            adapter.savePlayer(localPlayer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The player have been created!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(adapter.getAllPlayers());
            clickMouseCancelEvent(event);
        }
    }

    /**
     * A method that will search and show all the players with a specific status
     */
    @FXML void clickMouseSearch(MouseEvent event) {
        if(playerType.getValue()=="All")
        {
            setListDetails(adapter.getAllPlayers());
        }else{
            setListDetails(adapter.getAllPlayersStatus(playerType.getValue()));
        }
    }
}
