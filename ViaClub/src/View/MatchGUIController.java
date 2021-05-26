package View;

import Model.Location;
import Model.Match;
import Model.MyDate;
import Model.Player;
import Utils.VIAClubAdapter;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


/**
 * The Match GUI controller allow to see and modify details
 * about matches who are inside the VIAClub system (file system) and what players can play in the specific match
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */

public class MatchGUIController {

    @FXML private ListView<HBox> matchList;
    @FXML private ComboBox<String> matchType;
    @FXML private DialogPane dialogPop;
    @FXML private Label labelTitle;
    @FXML private TextField labelTeam1Name;
    @FXML private TextField labelTeam2Name;
    @FXML private TextField labelScore;
    @FXML private ComboBox<String> comboMatchType;
    @FXML private Button buttonCreate;
    @FXML private Button buttonSave;
    @FXML private DatePicker matchDate;
    @FXML private TextField labelCountryName;
    @FXML private TextField labelCityName;
    @FXML private TextField labelStadiumName;
    @FXML private ListView<Player> playerList;
    @FXML private ListView<Player> playersWhoWillPlay;
    @FXML private Label labelAvailablePlayers;
    @FXML private Label labelPlayersWhoArePlaying;


    private VIAClubAdapter adapter;
    private ArrayList<Player> players;
    private ArrayList<Match> matches;
    private int matchIndex;

    /**
     * This method will initialize the data when the match interface is opened.
     * Firstly, declares the adaptor object of type VIAClubAdaptor and a global variables
     * Also, this method will load and set all the matches in a list view
     * */
    public void initialize()
    {
        adapter = new VIAClubAdapter("players.bin","matches.bin","matches.xml");
        players = adapter.getAllPlayers();
        matchType.getItems().addAll("All","Friendly","League","Cup");
        matchType.getSelectionModel().selectFirst();
        comboMatchType.getItems().addAll("Friendly","League","Cup");
        comboMatchType.getSelectionModel().selectFirst();
        setListDetails(adapter.getAllMatches());
    }

    /**
     * A method which loads all the matches stored in the file on the list view
     * First, will clear the whole list, this function have the role of the reset the list and populate it again
     * */
    private void setListDetails(ArrayList<Match> list)
    {
        matchList.getItems().clear();
        matches = list;

        for(Match match:matches)
        {
            HBox hBox = new HBox();
            //add the player first name and last name on the list
            Label label = new Label(match.getTeam1() + " vs " + match.getTeam2()+" | "+match.getType());
            label.setPrefWidth(500);
            hBox.getChildren().add(label);
            //add edit button
            Button editButton = new Button("Edit");
            editButton.getStyleClass().add("buttonGreen");

            //edit player button event handler using lambda method
            editButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                playersWhoWillPlay.getItems().clear();
                playerList.getItems().clear();
                dialogPop.setVisible(true);
                labelAvailablePlayers.setVisible(true);
                labelPlayersWhoArePlaying.setVisible(true);
                buttonSave.setVisible(false);
                playersWhoWillPlay.setVisible(true);
                playerList.setVisible(true);
                labelTitle.setText("Edit a Match");
                labelTeam1Name.setText(match.getTeam1());
                labelTeam2Name.setText(match.getTeam2());
                labelScore.setText(match.getScore());
                labelCityName.setText(match.getLocation().getCity());
                labelCountryName.setText(match.getLocation().getCountry());
                labelStadiumName.setText(match.getLocation().getStadium());
                comboMatchType.setValue(match.getType());
                LocalDate localDate = LocalDate.of(match.getDate().getYear(),match.getDate().getMonth(),match.getDate().getDay());
                matchDate.setValue(localDate);
                buttonSave.setVisible(true);
                ArrayList<Player> localListWhoIsPlaying = match.getListOfPlayers();
                if(!localListWhoIsPlaying.isEmpty()){
                    for(Player player:localListWhoIsPlaying)
                    {
                        playersWhoWillPlay.getItems().add(player);
                    }
                }


                for(Player player:players)
                {
                    if(match.getType().equals("Friendly") && !player.getStatus().equals("Injured")){
                        playerList.getItems().add(player);
                    }else if(match.getType().equals("League") && !player.getStatus().equals("Injured") && !player.getStatus().equals("Suspended"))
                    {
                        playerList.getItems().add(player);
                    }else if(match.getType().equals("Cup") && !player.getStatus().equals("Injured") && !player.getStatus().equals("Suspended")){
                        playerList.getItems().add(player);
                    }
                }


                matchIndex=matches.indexOf(match);
            });

            //add Remove button
            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().add("buttonRed");
            //remove player button event handler using lambda method
            removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent)->{

                //Ask for a confirmation to delete the match, if the OK button was pressed the deletion process can start and save into file
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the match\n("+match.getTeam1()+" vs "+match.getTeam2()+") from the system?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get()== ButtonType.OK)
                {
                    adapter.removeMatch(match);
                    setListDetails(adapter.getAllMatches());
                }
            });

            //Add a little space between edit and remove button
            HBox.setMargin(editButton,new Insets(0,5,0,0));
            //add the buttons on HBox and after populate the playerList with the hBox
            hBox.getChildren().addAll(editButton,removeButton);

            matchList.getItems().add(hBox);
        }
    }


    /**
     *   This method will set visibility of a dialogBox where you can create a new match
     * */
    @FXML void clickMouseCreateEvent(MouseEvent event) {
        buttonCreate.setVisible(true);
        dialogPop.setVisible(true);
        matchDate.setValue(LocalDate.now());
        labelTitle.setText("Create a match");
    }

    /**
     * This method will save all the modification of match and will show up a message if there is any error or everything have been saved successfully.
     * After that the method will call for an update of the match list.
     * */
    @FXML void clickMouseSaveEventButton(MouseEvent event) {
        if(labelTeam1Name.getText().isEmpty()||labelTeam2Name.getText().isEmpty()||labelCityName.getText().isEmpty()||labelStadiumName.getText().isEmpty()||labelCountryName.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {
            String localScore = "0-0";
            if(!labelScore.getText().isEmpty()){
                localScore = labelScore.getText();
            }

            MyDate localDate = new MyDate(matchDate.getValue().getDayOfMonth(), matchDate.getValue().getMonthValue(),matchDate.getValue().getYear());
            Location localLocation = new Location(labelCountryName.getText(),labelCityName.getText(),labelStadiumName.getText());
            Match localMatch = new Match(localDate,labelTeam1Name.getText(),labelTeam2Name.getText(),localLocation, comboMatchType.getValue());

            if(!playersWhoWillPlay.getItems().isEmpty()){
                for(Player player:playersWhoWillPlay.getItems()){
                    localMatch.addPlayer(player);
                }
            }

            adapter.editMatch(localMatch,matchIndex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The match have been created!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(adapter.getAllMatches());
            clickMouseCancelEvent(event);
        }
    }

    /**
     * This method will create a new match and will show up a message if there is any error or everything have been saved successfully.
     * After that the method will call for an update of the match list.
     * */
    @FXML void clickMouseCreateEventButton(MouseEvent event) {
        if(labelTeam1Name.getText().isEmpty()||labelTeam2Name.getText().isEmpty()||labelCityName.getText().isEmpty()||labelStadiumName.getText().isEmpty()||labelCountryName.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled!");
            alert.showAndWait();
        }else {
            String localScore = "0-0";
            if(!labelScore.getText().isEmpty()){
                localScore = labelScore.getText();
            }

            MyDate localDate = new MyDate(matchDate.getValue().getDayOfMonth(), matchDate.getValue().getMonthValue(),matchDate.getValue().getYear());
            Location localLocation = new Location(labelCountryName.getText(),labelCityName.getText(),labelStadiumName.getText());
            Match localMatch = new Match(localDate,labelTeam1Name.getText(),labelTeam2Name.getText(),localLocation, comboMatchType.getValue());
            adapter.saveMatch(localMatch);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The match have been created!");
            alert.showAndWait();
            dialogPop.setVisible(false);
            //update the player list with the new player
            setListDetails(adapter.getAllMatches());
            clickMouseCancelEvent(event);
        }
    }

    /**
     *   This method will hide the dialogBox and will clear all the cache memory of the input fields
     * */
    @FXML void clickMouseCancelEvent(MouseEvent event) {
        buttonCreate.setVisible(false);
        buttonSave.setVisible(false);
        labelAvailablePlayers.setVisible(false);
        labelPlayersWhoArePlaying.setVisible(false);
        dialogPop.setVisible(false);
        playersWhoWillPlay.setVisible(false);
        playerList.setVisible(false);
        labelTeam1Name.clear();
        labelTeam2Name.clear();
        labelScore.clear();
        labelCountryName.clear();
        labelCityName.clear();
        labelStadiumName.clear();
        matchDate.setValue(LocalDate.now());
    }


    /**
     * A method that will search and show all the matches with a specific type
     */
    @FXML void clickMouseSearch(MouseEvent event) {
        if(matchType.getValue()=="All")
        {
            setListDetails(adapter.getAllMatches());
        }else{
            setListDetails(adapter.getAllMatchesType(matchType.getValue()));
        }
    }

    /**
     * A method that will be use to select a player for the specific match
     */
    @FXML void clickSelectPlayerForMatch(MouseEvent event) {
        int index = playerList.getSelectionModel().getSelectedIndex();
        playersWhoWillPlay.getItems().add(playerList.getSelectionModel().getSelectedItem());
        playerList.getItems().remove(index);
    }

    /**
     * A method that will be use to remove a player from the specific match
     */
    @FXML void clickRemoveAPlayerForMatch(MouseEvent event){
        int index = playersWhoWillPlay.getSelectionModel().getSelectedIndex();
        playersWhoWillPlay.getItems().remove(index);
    }

}
