import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager  {
    final static int maximumClubs = 20; //Maximum number of clubs can be added
    private List<FootballClub> footballClubList = new ArrayList<>(maximumClubs); //Arraylist to save clubs
    private List<Match> matchList = new ArrayList<>(); //Arraylist to save played matches
    private int indexOfTeam1 = 0; // Save the index of the match team 1
    private int indexOfTeam2 = 0; // Save the index of the match team 2
    private int team1GoalScore = 0; // Save the goal score of the match team 1
    private int team2GoalScore = 0; // Save the goal score of the match team 2

    @Override
    public void addClub(FootballClub footballClub) {
        if(footballClubList.size()==20){
            System.out.println("Clubs are full!!");
            return;
        }
        // use this to check if the club is already in the list
        if (footballClubList.contains(footballClub)) {
            System.out.println("Club already in the list");
            return;
        }
        //if not it will add to the football club list
        footballClubList.add(footballClub);
        System.out.println("---Football club added to the List---");
    }

    @Override
    public void deleteClub(String clubName) {
        boolean foundClub = false;
        if(footballClubList.isEmpty()){
            System.out.println("Club list is empty!");
            return;
        }
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().equals(clubName)) {
                footballClubList.remove(i);
                foundClub = true;
                System.out.println("Football club removed successfully");
                break;
            }
        }
        if (!foundClub) {
            System.out.println("Football Club is not available in the list");
        }
    }

    @Override
    public void displayStat(String clubName) {
        boolean foundClub = false;
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().equals(clubName)) {
                System.out.println(footballClubList.get(i).toString());
                foundClub = true;
                break;
            }
        }
        if (!foundClub) {
            System.out.println("Football Club is not available in the list");
        }

    }

    @Override
    public void displayPLMtable() {
        //"%-15s %-8s %-12s %-12s %-12s %-18s %-16s %-16s %-16s%n"
        Collections.sort(footballClubList);
        System.out.println("Team\t\t " + "Played\t\t" + "Wins\t\t" + "Draw\t\t" + "Defeats\t\t" + "Goals Received\t\t" + "Goal Scored\t\t" + "Goal difference\t\t" + "Points");
        for (FootballClub footballClub1 : footballClubList) {
            System.out.printf("%-18s %-13s %-16s %-18s %-20s %-22s %-23s %-17s %-13s%n", footballClub1.getClubName(), footballClub1.getNumberOfPlayedMatches(), footballClub1.getWins(),
                    footballClub1.getDraws(), footballClub1.getDefeats(), footballClub1.getGoalsReceived(), footballClub1.getGoalScored(),
                    footballClub1.getGoalScored() - footballClub1.getGoalsReceived(),
                    footballClub1.getNumberOfPoints());
        }
    }

    @Override
    public void addMatch(String footballClub1, String footballClub2) {
        boolean foundClub1 = false; // to check whether the team 1 is existing in the PML table
        boolean foundClub2 = false; // to check whether the team 2 is existing in the PML table
        Match match = new Match(); // Creating the match object to store the match details

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < footballClubList.size(); i++) {
            if (footballClubList.get(i).getClubName().equals(footballClub1)) {
                indexOfTeam1 = i;
                foundClub1 = true;
                match.setTeam1(footballClub1); // adding team 1 to the match array list
            } else if (footballClubList.get(i).getClubName().equals(footballClub2)) {
                indexOfTeam2 = i;
                foundClub2 = true; // adding the team 2 to the match array list
                match.setTeam2(footballClub2);
            }
        }
        if (!foundClub1 && !foundClub2) {
            System.out.println(footballClub1 + " and " + footballClub2 + " not in the PML Table!!, Please check again");
            return;
        } else if (!foundClub1) {
            System.out.println(footballClub1 + " is not in the PML Table!!, Please check again");
            return;
        } else if (!foundClub2) {
            System.out.println(footballClub2 + " is not in the PML Table!!, Please check again");
            return;
        }


        // Getting the played match date
        try {
            System.out.println("Please enter the date of the played match");
            System.out.println("Format : DD/MM/YYYY");
            System.out.println("Enter the Day : ");
            int day = scanner.nextInt();
            System.out.println("Enter the Month : ");
            int month = scanner.nextInt();
            System.out.println("Enter the Year : ");
            int year = scanner.nextInt();
            Date date = new Date(day, month, year);
            match.setDate(date); //adding the match date
        } catch (InputMismatchException e) {
            System.out.println("Invalid format!!, please try again!");
            return;
        }
        while (true) {
            try {
                System.out.println("How many goal scored " + footballClub1 + " in the match : ");
                team1GoalScore = scanner.nextInt(); // getting the club score
                match.setTeam1GoalScore(team1GoalScore); // setting the match score
                footballClubList.get(indexOfTeam1).setGoalScored(footballClubList.get(indexOfTeam1).getGoalScored() + team1GoalScore); // updating the new club score
                footballClubList.get(indexOfTeam1).setNumberOfPlayedMatches(footballClubList.get(indexOfTeam1).getNumberOfPlayedMatches() + 1); // adding played matches count
                System.out.println(footballClub1 + " match details updated!"); // output message for completion
                break;
            } catch (InputMismatchException e) { // checking the type error
                System.out.println("Please enter a valid number!!");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.println("How many goal scored " + footballClub2 + " in the match : ");
                team2GoalScore = scanner.nextInt(); // getting the club score
                match.setTeam2GoalScore(team2GoalScore); //setting the match score
                footballClubList.get(indexOfTeam2).setGoalScored(footballClubList.get(indexOfTeam2).getGoalScored() + team2GoalScore); // updating the new club score
                footballClubList.get(indexOfTeam2).setNumberOfPlayedMatches(footballClubList.get(indexOfTeam2).getNumberOfPlayedMatches() + 1); // adding played matches count
                System.out.println(footballClub2 + " match details updated!"); // output message for completion
                break;
            } catch (InputMismatchException e) { // checking the type error
                System.out.println("Please enter a valid number!!");
                scanner.nextLine();
            }
        }
        matchList.add(match); // adding entered match details to the array list

        // adding the win/defeat/draw statistics
        if (team1GoalScore > team2GoalScore) {
            footballClubList.get(indexOfTeam1).setWins(footballClubList.get(indexOfTeam1).getWins() + 1); // adding the wining details
            footballClubList.get(indexOfTeam1).setNumberOfPoints(footballClubList.get(indexOfTeam1).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(indexOfTeam2).setDefeats(footballClubList.get(indexOfTeam2).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(indexOfTeam1).setGoalsReceived(footballClubList.get(indexOfTeam1).getGoalsReceived() +  // adding goals received for Team 1
                    (team1GoalScore - team2GoalScore));
        } else if (team1GoalScore < team2GoalScore) {
            footballClubList.get(indexOfTeam2).setWins(footballClubList.get(indexOfTeam2).getWins() + 1); // adding the wining details
            footballClubList.get(indexOfTeam2).setNumberOfPoints(footballClubList.get(indexOfTeam2).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(indexOfTeam1).setDefeats(footballClubList.get(indexOfTeam1).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(indexOfTeam2).setGoalsReceived(footballClubList.get(indexOfTeam2).getGoalsReceived() +  // adding goals received for Team 2
                    (team2GoalScore - team1GoalScore));
        } else {
            footballClubList.get(indexOfTeam1).setDraws(footballClubList.get(indexOfTeam1).getDraws() + 1); //adding draw details
            footballClubList.get(indexOfTeam1).setNumberOfPoints(footballClubList.get(indexOfTeam1).getNumberOfPoints() + 1); // adding 1 point for draw match
            footballClubList.get(indexOfTeam2).setDraws(footballClubList.get(indexOfTeam2).getDraws() + 1); // adding draw details
            footballClubList.get(indexOfTeam2).setNumberOfPoints(footballClubList.get(indexOfTeam2).getNumberOfPoints() + 3); // adding 1 point for draw match
        }
    }

    @Override
    public void saveData(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream((fileName));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for(SportsClub footballClub : footballClubList) {
            objectOutputStream.writeObject(footballClub);
        }
        System.out.println("Football clubs saved successfully!");

        for(Match match : matchList) {
            objectOutputStream.writeObject(match);
        }
        System.out.println("Match Data saved successfully!");
    }


    @Override
    public void loadData(String fileName) {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            for (; ; )
            {
                try
                {
                    Object object = ( objectInputStream.readObject()); // Reading the object from the text file
                    Class objClass = object.getClass(); // Getting the type of the read object

                    if ((FootballClub.class == objClass)) { // If the object type is equal to FootballClub type
                        footballClubList.add((FootballClub) object); // adding to the footballClub list
                    }
                    if ((Match.class == objClass)) { // If the object type is equal to Match type
                        matchList.add((Match) object); // adding to the match list
                    }
                } catch (EOFException eofException)
                {
                    break;
                }
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("File Not Found !");
        }
        catch (IOException ioException)
        {
            System.out.println("ERROR" + ioException.getMessage());
        }
        catch (Exception exception)
        {
            System.out.println("ERROR" + exception.getMessage());
        }
    }

    @Override
    public void playRandomMatch() {
        int randomTeam1 = 0; // Save the randomly generated index position
        int randomTeam2 = 0; // Save the randomly generated index position
        int min = 0; // Minimum range of football club index position in football club list
        int max = footballClubList.size() - 1; // Maximum range of football club index position in football club list
        int minGoalScore = 0; // Minimum score value range
        int maxGoalScore = 20; // Maximum score value range
        int team1GoalScore = (int)(Math.random() * (maxGoalScore - minGoalScore + 1) + minGoalScore );
        int team2GoalScore = (int)(Math.random() * (maxGoalScore - minGoalScore + 1) + minGoalScore );
        Match match = new Match(); // Creating a match object to save the records
        // Generating a random number between 0 and the number of football clubs in the list
        // It will generate until the random numbers are not same
        while(true){
            randomTeam1 = (int)(Math.random() * (max - min + 1) + min );
            randomTeam2 = (int)(Math.random() * (max - min + 1) + min );
            if(randomTeam1==randomTeam2){
                randomTeam2 = (int)(Math.random() * (max - min + 1) + min );
            } else {
                break;
            }
        }
        // Generating a random date
        int dateDay = (int) (Math.random() * (31 - 1 + 1) + 1); // Generating a random day
        int dateMonth = (int) (Math.random()* (12 - 1 + 1) + 1); // Generating a random month
        int dateYear = (int) (Math.random()* (2021 - 2020 + 1) + 2020); // Generating a random year
        Date date = new Date(dateDay,dateMonth,dateYear); // Creating the date object and passing the values
        match.setDate(date); // Adding date to the match object
        match.setTeam1(footballClubList.get(randomTeam1).getClubName()); // Setting the Team 1 name to the match object
        match.setTeam1GoalScore(team1GoalScore); // Setting the Team 1 score to the match object
        match.setTeam2(footballClubList.get(randomTeam2).getClubName()); // Setting the Team 2 name to the match object
        match.setTeam2GoalScore(team2GoalScore); // Setting the Team 2 score to the match object
        matchList.add(match); // adding the match records to the match list
        footballClubList.get(randomTeam1).setGoalScored(footballClubList.get(randomTeam1).getGoalScored() + team1GoalScore); // updating the new club score
        footballClubList.get(randomTeam1).setNumberOfPlayedMatches(footballClubList.get(randomTeam1).getNumberOfPlayedMatches() + 1); // adding played matches count
        footballClubList.get(randomTeam2).setGoalScored(footballClubList.get(randomTeam2).getGoalScored() + team2GoalScore); // updating the new club score
        footballClubList.get(randomTeam2).setNumberOfPlayedMatches(footballClubList.get(randomTeam2).getNumberOfPlayedMatches() + 1); // adding played matches count

        // randomTeam 1
        if (team1GoalScore > team2GoalScore) {
            footballClubList.get(randomTeam1).setWins(footballClubList.get(randomTeam1).getWins() + 1); // adding the wining details to Team 1
            footballClubList.get(randomTeam1).setNumberOfPoints(footballClubList.get(randomTeam1).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(randomTeam2).setDefeats(footballClubList.get(randomTeam2).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(randomTeam1).setGoalsReceived(footballClubList.get(randomTeam1).getGoalsReceived() +  // adding goals received for Team 1
                    (team1GoalScore - team2GoalScore));

        } else if (team1GoalScore < team2GoalScore) {
            footballClubList.get(randomTeam2).setWins(footballClubList.get(randomTeam2).getWins() + 1); // adding the wining details to Team 2
            footballClubList.get(randomTeam2).setNumberOfPoints(footballClubList.get(randomTeam2).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(randomTeam1).setDefeats(footballClubList.get(randomTeam1).getDefeats() + 1); // adding the defeat details to the opponent team
            footballClubList.get(randomTeam2).setGoalsReceived(footballClubList.get(randomTeam2).getGoalsReceived() +  // adding goals received for Team 1
                    (team2GoalScore - team1GoalScore));
        } else {
            footballClubList.get(randomTeam1).setDraws(footballClubList.get(indexOfTeam1).getDraws() + 1); //adding draw details to Both Teams
            footballClubList.get(randomTeam1).setNumberOfPoints(footballClubList.get(indexOfTeam1).getNumberOfPoints() + 1); // adding 1 point for draw match
            footballClubList.get(randomTeam2).setDraws(footballClubList.get(randomTeam2).getDraws() + 1); // adding draw details
            footballClubList.get(randomTeam2).setNumberOfPoints(footballClubList.get(randomTeam2).getNumberOfPoints() + 3); // adding 1 point for draw match
        }

    }

    @Override
    public void guiPlayMatch(Stage stage, Scene scene) {
        BorderPane borderPanePlayRandomMatch = new BorderPane();
        Scene scenePlayRandomMatch = new Scene(borderPanePlayRandomMatch,1600,600);
        GridPane gridPaneButtons = new GridPane(); //Pane for Button section
        GridPane gridPaneHeading = new GridPane(); //Pane for the headings
        HBox hBoxTables = new HBox();
        Button home = new Button("Home");
        home.setPrefSize(150,10);
        Button play = new Button("Play random Match");
        play.setPrefSize(150,10);
        Text labelHeadingPML = new Text("Premier League Table");
        Text labelHeadingMatches = new Text("Generated Matches");
        labelHeadingPML.setStyle("-fx-font-weight: bold");
        labelHeadingMatches.setStyle("-fx-font-weight: bold");

        // Table view for the PML
        // Creating the table view to display the stats
        TableView tableViewPML = new TableView();
        TableColumn column1T = new TableColumn<>("Club Name");
        column1T.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        TableColumn column2T = new TableColumn<>("Club Location");
        column2T.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        TableColumn column3T = new TableColumn<>("Wins");
        column3T.setCellValueFactory(new PropertyValueFactory<>("wins"));
        TableColumn column4T = new TableColumn<>("Draws");
        column4T.setCellValueFactory(new PropertyValueFactory<>("draws"));
        TableColumn column5T = new TableColumn<>("Goals Received");
        column5T.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        TableColumn column6T = new TableColumn<>("Goals Scored");
        column6T.setCellValueFactory(new PropertyValueFactory<>("goalScored"));
        TableColumn column7T = new TableColumn<>("Points");
        column7T.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));
        TableColumn column8T = new TableColumn<>("Number of Matches");
        column8T.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayedMatches"));

        //adding columns to the table.
        tableViewPML.getColumns().add(column1T);
        tableViewPML.getColumns().add(column2T);
        tableViewPML.getColumns().add(column3T);
        tableViewPML.getColumns().add(column4T);
        tableViewPML.getColumns().add(column5T);
        tableViewPML.getColumns().add(column6T);
        tableViewPML.getColumns().add(column7T);
        tableViewPML.getColumns().add(column8T);
        //Removing the additional column by default.
        tableViewPML.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Table view for the played match
        TableView tableViewPlayedMatch = new TableView();
        TableColumn column1P = new TableColumn<>("Team 1 Name");
        column1P.setCellValueFactory(new PropertyValueFactory<>("team1"));
        TableColumn column2P = new TableColumn<>("Team 2 Name");
        column2P.setCellValueFactory(new PropertyValueFactory<>("team2"));
        TableColumn column3P = new TableColumn<>("Team 1 Score");
        column3P.setCellValueFactory(new PropertyValueFactory<>("team1GoalScore"));
        TableColumn column4P = new TableColumn<>("Team 2 Score");
        column4P.setCellValueFactory(new PropertyValueFactory<>("team2GoalScore"));
        TableColumn column5P = new TableColumn<>("Date");
        column5P.setCellValueFactory(new PropertyValueFactory<>("date"));
        //adding columns to the table.
        tableViewPlayedMatch.getColumns().add(column1P);
        tableViewPlayedMatch.getColumns().add(column2P);
        tableViewPlayedMatch.getColumns().add(column3P);
        tableViewPlayedMatch.getColumns().add(column4P);
        tableViewPlayedMatch.getColumns().add(column5P);
        //Removing the additional column by default.
        tableViewPlayedMatch.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //Adding data to the Table View
        for(int i = 0; i < footballClubList.size() ; i++){
            tableViewPML.getItems().add(footballClubList.get(i));
        }

        gridPaneButtons.add(home,10,0); //Adding home button
        gridPaneButtons.add(play,14,0); //Adding play button
        gridPaneButtons.setHgap(55); //Setting horizontal gap - Premier League Table
        borderPanePlayRandomMatch.setBottom(gridPaneButtons);
        gridPaneHeading.add(labelHeadingPML,4,0); //Adding pain heading - Premier League Table
        gridPaneHeading.add(labelHeadingMatches,12,0); //Adding pain heading - Matches Table
        gridPaneHeading.setHgap(95); //Setting horizontal gap - Matches Table
        borderPanePlayRandomMatch.setTop(gridPaneHeading); //Adding heading pane for the border pane

        tableViewPML.setPrefSize(1000,500); //Setting a preferred size for the table view - premier league table
        tableViewPlayedMatch.setPrefSize(600,500); //Setting a preferred size for the table view - match table


        hBoxTables.getChildren().add(tableViewPML); //Adding table view to the Hbox - premier league table
        hBoxTables.getChildren().add(tableViewPlayedMatch); //Adding table view to the Hbox - match table

        borderPanePlayRandomMatch.setCenter(hBoxTables); //Adding Hbox for the border pane
        borderPanePlayRandomMatch.setTop(gridPaneHeading); //Adding heading pane for the border pane
        play.setOnAction(new EventHandler<ActionEvent>() { //Set on action for the play random match button
            @Override
            public void handle(ActionEvent event) {
                playRandomMatch(); //Calling the random play match method
                tableViewPlayedMatch.getItems().add(matchList.get(matchList.size()-1)); //Adding played match to the match table
                tableViewPML.getItems().clear(); // Clearing the PML table before adding data
                for(int i = 0; i < footballClubList.size() ; i++){
                    tableViewPML.getItems().add(footballClubList.get(i)); //Adding data to the PML table
                }
            }
        });
        home.setOnAction(new EventHandler<ActionEvent>() { //Set on action for the home button
            @Override
            public void handle(ActionEvent event) {
                tableViewPlayedMatch.getItems().clear(); //Clearing the table view before switching the scene
                stage.setScene(scene); //Changing the scene to the home
            }
        });
        stage.setScene(scenePlayRandomMatch);  //Setting the play random match stage to the scene
    }

    public void searchMatch(Stage stage, Scene scene){
        BorderPane borderPaneSearchMatch = new BorderPane();
        GridPane gridPaneSearchMatch = new GridPane();
        Scene sceneSearchMatch = new Scene(borderPaneSearchMatch,1200,600);

        //Buttons for the search matches window
        Button buttonHomeSearchMatch = new Button("Home");
        buttonHomeSearchMatch.setPrefSize(150,10);
        Button buttonSearchMatch = new Button("Search Match");
        buttonHomeSearchMatch.setPrefSize(150,10);
        //Label to Display the
        //Text box for take the date input
        TextField textFieldDay = new TextField("Day");
        TextField textFieldMonth = new TextField("Month");
        TextField textFieldYear = new TextField("Year");

        //------------Search matches UI-------------
        TableView tableSearchPlayedMatch = new TableView();
        TableColumn column1S = new TableColumn<>("Team 1 Name");
        column1S.setCellValueFactory(new PropertyValueFactory<>("team1"));
        TableColumn column2S = new TableColumn<>("Team 2 Name");
        column2S.setCellValueFactory(new PropertyValueFactory<>("team2"));
        TableColumn column3S = new TableColumn<>("Team 1 Score");
        column3S.setCellValueFactory(new PropertyValueFactory<>("team1GoalScore"));
        TableColumn column4S = new TableColumn<>("Team 2 Score");
        column4S.setCellValueFactory(new PropertyValueFactory<>("team2GoalScore"));
        TableColumn column5S = new TableColumn<>("Date");
        column5S.setCellValueFactory(new PropertyValueFactory<>("date"));


        //adding columns to the table.
        tableSearchPlayedMatch.getColumns().add(column1S);
        tableSearchPlayedMatch.getColumns().add(column2S);
        tableSearchPlayedMatch.getColumns().add(column3S);
        tableSearchPlayedMatch.getColumns().add(column4S);
        tableSearchPlayedMatch.getColumns().add(column5S);
        //Removing the additional column by default.
        tableSearchPlayedMatch.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gridPaneSearchMatch.add(textFieldDay,9,12);
        gridPaneSearchMatch.add(textFieldMonth,10,12);
        gridPaneSearchMatch.add(textFieldYear,11,12);
        gridPaneSearchMatch.add(buttonSearchMatch,10,13);
        tableSearchPlayedMatch.setPrefSize(700,570);

        gridPaneSearchMatch.resize(600,570);
        textFieldDay.setPrefSize(60,30);
        textFieldMonth.setPrefSize(60,30);
        textFieldYear.setPrefSize(60,30);
        gridPaneSearchMatch.setVgap(15);
        gridPaneSearchMatch.setHgap(15);

        borderPaneSearchMatch.setLeft(tableSearchPlayedMatch);
        borderPaneSearchMatch.setCenter(gridPaneSearchMatch);
        borderPaneSearchMatch.setBottom(buttonHomeSearchMatch);

        textFieldDay.setOnMouseClicked(new EventHandler<MouseEvent>() {//Setting clear for the mouse on click for the text Field to be clear
            @Override
            public void handle(MouseEvent event) {
                textFieldDay.clear();
            }
        });
        textFieldMonth.setOnMouseClicked(new EventHandler<MouseEvent>() {//Setting clear for the mouse on click for the text Field to be clear
            @Override
            public void handle(MouseEvent event) {
                textFieldMonth.clear();
            }
        });
        textFieldYear.setOnMouseClicked(new EventHandler<MouseEvent>() {//Setting clear for the mouse on click for the text Field to be clear
            @Override
            public void handle(MouseEvent event) {
                textFieldYear.clear();
            }
        });


        buttonHomeSearchMatch.setOnAction(new EventHandler<ActionEvent>() {//Set on action for the home button
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        buttonSearchMatch.setOnAction(new EventHandler<ActionEvent>() {//Set on action for the match search button
            @Override
            public void handle(ActionEvent event) {
                tableSearchPlayedMatch.getItems().clear(); //Clear the table before displaying data
                try {
                    int getDay = Integer.parseInt(textFieldDay.getText()); // Get the day from the text Field
                    int getMonth = Integer.parseInt(textFieldMonth.getText()); // Get the month from the text Field
                    int getYear = Integer.parseInt(textFieldYear.getText()); // Get the year from the text Field
                    Date dateFind = new Date(getDay, getMonth, getYear); // Creating a date object
                    for (Match match : matchList) { // Iterating through the matchList
                        if (match.getDate().equals(dateFind)) { //Checking the match date from the array list with the user entered dates
                            tableSearchPlayedMatch.getItems().add(match); //if true will display the match data to the table view
                            return;
                        }
                    }
                    Alert alertFull = new Alert(Alert.AlertType.INFORMATION); //alert box to show no match found.
                    alertFull.setContentText("No Matches Found!"); //error message for the alert box.
                    alertFull.showAndWait();
                } catch (NumberFormatException e) {
                    Alert alertError = new Alert(Alert.AlertType.INFORMATION); //alert box to show invalid type of data entered.
                    alertError.setContentText("Please enter date format in dd/mm/yyyy"); //error message for the alert box.
                    alertError.showAndWait();
                }
            }
        });

        stage.setScene(sceneSearchMatch); //Setting the stage to the search stage
    }


    @Override
    public void guiMain() {
        //Creating the pane and the scene
        BorderPane borderPaneHome = new BorderPane();
        BorderPane borderPaneSortTeams = new BorderPane();
        BorderPane borderPaneViewPlayedMatch = new BorderPane();

        Scene homeScene = new Scene(borderPaneHome, 1600, 600);
        Scene sceneSortTeams = new Scene(borderPaneSortTeams,1200,600);
        Scene sceneViewPlayedMatch = new Scene(borderPaneViewPlayedMatch,1200,600);
        Stage homeStage = new Stage();

        GridPane gridPaneButtons = new GridPane(); //Pane for Button section
        GridPane gridPaneCenter = new GridPane(); // Pane for home window
        GridPane gridPaneSortTeams = new GridPane();

        //------------Buttons section------------
        // Buttons for the Home window
        Button buttonSortTeams = new Button("Sort Teams");
        buttonSortTeams.setPrefSize(150,10);
        Button buttonPlayRandomMatch = new Button("Play Random Match");
        Button buttonSearchMatches = new Button("Search Matches");
        buttonSearchMatches.setPrefSize(150,10);
        Button buttonViewPlayedMatches = new Button("View Played Matches");
        buttonViewPlayedMatches.setPrefSize(180,10);
        Button buttonHomeWindow = new Button("Home");
        buttonHomeWindow.setPrefSize(150,10);

        // Buttons for the Sort Teams window
        Button buttonSortPoints = new Button("Sort by Points");
        buttonSortPoints.setPrefSize(150,10);
        Button buttonSortGoalScore = new Button ("Sort by Goal Score");
        buttonSortGoalScore.setPrefSize(150,10);
        Button buttonSortWins = new Button("Sort by Wins");
        buttonSortWins.setPrefSize(150,10);
        Button buttonHomeWindowMatch = new Button("Home");
        buttonHomeWindowMatch.setPrefSize(150,10);

        //Buttons for the view played matches window
        Button buttonHomeViewMatch = new Button("Home");
        buttonHomeViewMatch.setPrefSize(150,10);

        // Creating the table view to display the stats
        TableView tableView = new TableView();
        TableColumn column1T = new TableColumn<>("Club Name");
        column1T.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        TableColumn column2T = new TableColumn<>("Club Location");
        column2T.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        TableColumn column3T = new TableColumn<>("Wins");
        column3T.setCellValueFactory(new PropertyValueFactory<>("wins"));
        TableColumn column4T = new TableColumn<>("Draws");
        column4T.setCellValueFactory(new PropertyValueFactory<>("draws"));
        TableColumn column5T = new TableColumn<>("Goals Received");
        column5T.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        TableColumn column6T = new TableColumn<>("Goals Scored");
        column6T.setCellValueFactory(new PropertyValueFactory<>("goalScored"));
        TableColumn column7T = new TableColumn<>("Points");
        column7T.setCellValueFactory(new PropertyValueFactory<>("numberOfPoints"));
        TableColumn column8T = new TableColumn<>("Number of Matches");
        column8T.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayedMatches"));

        //adding columns to the table.
        tableView.getColumns().add(column1T);
        tableView.getColumns().add(column2T);
        tableView.getColumns().add(column3T);
        tableView.getColumns().add(column4T);
        tableView.getColumns().add(column5T);
        tableView.getColumns().add(column6T);
        tableView.getColumns().add(column7T);
        tableView.getColumns().add(column8T);
        //Removing the additional column by default.
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //------------Home Window UI------------
        gridPaneButtons.add(buttonSortTeams,2,0); // Adding buttons
        gridPaneButtons.add(buttonPlayRandomMatch,4,0);
        gridPaneButtons.add(buttonSearchMatches,6,0);
        gridPaneButtons.add(buttonViewPlayedMatches,8,0);
        Image imageHomeBg = new Image("file:jg.jpg");
        ImageView imageView = new ImageView(imageHomeBg);
        Image imageHomeLeft = new Image("file:logo.jpg");
        ImageView imageView2 = new ImageView(imageHomeLeft);
        gridPaneCenter.add(imageView,0,0);
        gridPaneCenter.add(imageView2,20,0);
        imageView.setFitHeight(570);
        imageView.setFitWidth(1200);
        imageView2.setFitHeight(570);
        imageView2.setFitWidth(402);
        gridPaneButtons.setHgap(55);
        borderPaneHome.setCenter(gridPaneCenter);
        borderPaneHome.setBottom(gridPaneButtons);

        //------------Sort Teams UI-------------
        gridPaneSortTeams.add(buttonHomeWindow,2,0);
        gridPaneSortTeams.add(buttonSortPoints,4,0);
        gridPaneSortTeams.add(buttonSortGoalScore,6,0);
        gridPaneSortTeams.add(buttonSortWins,8,0);
        gridPaneSortTeams.setHgap(55);
        borderPaneSortTeams.setCenter(tableView);
        borderPaneSortTeams.setBottom(gridPaneSortTeams);

        //------------View played matches UI-------------
        // Table view for the played match
        TableView tableViewPlayedMatch = new TableView();
        TableColumn column1P = new TableColumn<>("Team 1 Name");
        column1P.setCellValueFactory(new PropertyValueFactory<>("team1"));
        TableColumn column2P = new TableColumn<>("Team 2 Name");
        column2P.setCellValueFactory(new PropertyValueFactory<>("team2"));
        TableColumn column3P = new TableColumn<>("Team 1 Score");
        column3P.setCellValueFactory(new PropertyValueFactory<>("team1GoalScore"));
        TableColumn column4P = new TableColumn<>("Team 2 Score");
        column4P.setCellValueFactory(new PropertyValueFactory<>("team2GoalScore"));
        TableColumn column5P = new TableColumn<>("Date");
        column5P.setCellValueFactory(new PropertyValueFactory<>("date"));
        //adding columns to the table.
        tableViewPlayedMatch.getColumns().add(column1P);
        tableViewPlayedMatch.getColumns().add(column2P);
        tableViewPlayedMatch.getColumns().add(column3P);
        tableViewPlayedMatch.getColumns().add(column4P);
        tableViewPlayedMatch.getColumns().add(column5P);
        tableViewPlayedMatch.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        borderPaneViewPlayedMatch.setCenter(tableViewPlayedMatch);
        borderPaneViewPlayedMatch.setBottom(buttonHomeViewMatch);
        //-------------Home Window Buttons-------------
        // Goto Sort Teams window
        buttonSortTeams.setOnAction(event -> {
            for(int i = 0; i < footballClubList.size() ; i++){
                tableView.getItems().add(footballClubList.get(i));
            }
            homeStage.setScene(sceneSortTeams);
            buttonHomeWindow.setOnAction(event1 -> {
                tableView.getItems().clear();
                homeStage.setScene(homeScene);
            });
        });
        //Set on action for the sort by points button
        buttonSortPoints.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableView.getSortOrder().clear(); //Clearing the table before displaying the data
                tableView.getSortOrder().add(column7T); // Sort by the points column (column7T)
                column7T.setSortType(TableColumn.SortType.DESCENDING); // Setting the sort order
                tableView.sort(); // Sorting the table
            }
        });
        buttonSortGoalScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableView.getSortOrder().clear(); //Clearing the table before displaying the data
                tableView.getSortOrder().add(column6T); // Sort by the Goal Score column (column6T)
                column6T.setSortType(TableColumn.SortType.DESCENDING); // Setting the sort order
                tableView.sort(); // Sorting the table
            }
        });
        buttonSortWins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableView.getSortOrder().clear(); //Clearing the table before displaying the data
                tableView.getSortOrder().add(column3T); // Sort by the wins column (column3T)
                column3T.setSortType(TableColumn.SortType.DESCENDING); // Setting the sort order
                tableView.sort(); // Sorting the table
            }
        });

        // Goto play random match window
        buttonPlayRandomMatch.setOnAction(event -> {
            guiPlayMatch(homeStage,homeScene);
        });
        // Goto Search matches window
        buttonSearchMatches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchMatch(homeStage,homeScene);
            }
        });
        // Goto view played matches window
        buttonViewPlayedMatches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Collections.sort(matchList,Collections.reverseOrder()); // Sorting the match list by the date
                homeStage.setScene(sceneViewPlayedMatch); // Setting the stage to the view played match stage
                for(int i = 0; i < matchList.size() ; i++){
                    tableViewPlayedMatch.getItems().add(matchList.get(i)); // Setting the data to the table view
                }
                buttonHomeViewMatch.setOnAction(event1 -> { // Setting the home button action
                    tableViewPlayedMatch.getItems().clear(); // Clearing the table view
                    homeStage.setScene(homeScene); // Setting the stage to the home stage
                });
            }
        });

        // Creating a stage and setting the scene for home window
        homeStage.setTitle("Premier League Manger");
        homeStage.setScene(homeScene);
        homeStage.showAndWait();
    }
}








