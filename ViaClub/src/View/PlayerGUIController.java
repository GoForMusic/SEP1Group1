package View;

import Model.Player;
import Utils.VIAClubModelManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Optional;

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


    private VIAClubModelManager manager;
    private ArrayList<Player> players;
    private int playerIndex;


    /**
    * This method will initialize the data when the player interface is opened.
    * Firstly, declares the adaptor object of type VIAClubAdaptor and a global variables
    * Also, this method will load and set all the players in a list view
    * */
    public void initialize()
    {
        playerIndex=0;
        manager = new VIAClubModelManager("players.bin","matches.bin","matches.xml");
        playerType.getItems().addAll("All","Available","Suspended","Injured");
        playerType.getSelectionModel().selectFirst();
        comboPlayerStatus.getItems().addAll("Available","Suspended","Injured");
        comboPlayerStatus.getSelectionModel().selectFirst();
        setListDetails(manager.getAllPlayers());
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
            Label label = new Label(player.getFirstName() + " " + player.getLastName()+" | "+ player.getStatus());
            label.setPrefWidth(500);
            hBox.getChildren().add(label);
            //add edit button
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");

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

                //Ask for a confirmation to delete the player, if the OK button was pressed the deletion process can start and save into file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete player\n("+player.getFirstName()+" "+player.getLastName()+") from the system?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK)
                {
                    manager.removePlayer(player);
                    setListDetails(manager.getAllPlayers());
                }

            });

            //Add a little space between edit and remove button
            HBox.setMargin(editButton,new Insets(0,5,0,0));
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
     *   This method will hide the dialogBox and will clear all the cache memory of the input fields
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
            manager.editPlayer(localPlayer,playerIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The player new details have been saved!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(manager.getAllPlayers());
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
            manager.savePlayer(localPlayer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The player have been created!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(manager.getAllPlayers());
            clickMouseCancelEvent(event);
        }
    }

    /**
     * A method that will search and show all the players with a specific status
     */
    @FXML void clickMouseSearch(MouseEvent event) {
        if(playerType.getValue()=="All")
        {
            setListDetails(manager.getAllPlayers());
        }else{
            setListDetails(manager.getAllPlayersStatus(playerType.getValue()));
        }
    }
}
