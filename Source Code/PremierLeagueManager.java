import java.io.*;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {
    private List<FootballClub> footballClubList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    private int indexOfTeam1 = 0;
    private int indexOfTeam2 = 0;
    private int team1GoalScore = 0;
    private int team2GoalScore = 0;

    @Override
    public void addClub(FootballClub footballClub) {
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
        Collections.sort(footballClubList);
        System.out.println("\n==============================Premier League Table==============================");
        System.out.println("Team\t\t " + "Played\t\t" + "Wins\t\t" + "Draw\t\t" + "Defeats\t\t" + "Goals Received\t\t" + "Goal Scored\t\t" +
                "Goal difference\t\t" + "Points");
        for (FootballClub footballClub1 : footballClubList) {
            System.out.printf("%-15s %-8s %-12s %-12s %-12s %-18s %-16s %-16s %-16s%n", footballClub1.getClubName(), footballClub1.getNumberOfPlayedMatches(), footballClub1.getWins(),
                    footballClub1.getDraws(), footballClub1.getDefeats(), footballClub1.getGoalsReceived(), footballClub1.getGoalScored(),
                    footballClub1.getGoalScored() - footballClub1.getGoalsReceived(),
                    footballClub1.getNumberOfPoints());
        }
        /*System.out.println(matchList.get(0).getTeam1());
        System.out.println(matchList.get(0).getTeam1GoalScore());
        System.out.println(matchList.get(0).getTeam2());
        System.out.println(matchList.get(0).getTeam2GoalScore());
        System.out.println(matchList.get(0).getDate());*/
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


        // System.out.println(indexOfTeam1 + " and " + indexOfTeam2);
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
        } else if (team1GoalScore < team2GoalScore) {
            footballClubList.get(indexOfTeam2).setWins(footballClubList.get(indexOfTeam2).getWins() + 1); // adding the wining details
            footballClubList.get(indexOfTeam2).setNumberOfPoints(footballClubList.get(indexOfTeam2).getNumberOfPoints() + 3); // adding the points for a win
            footballClubList.get(indexOfTeam1).setDefeats(footballClubList.get(indexOfTeam1).getDefeats() + 1); // adding the defeat details to the opponent team
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
        System.out.println("Match saved successfully!");
    }

    @Override
    public void loadData(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        for(;;) {
            try {
                Object object = ( objectInputStream.readObject()); // Reading the object from the text file
                Class objClass = object.getClass(); // Getting the type of the read object
                //System.out.println(objClass); // sending type to the console
                if ((FootballClub.class == objClass)) { // If the object type is equal to FootballClub type
                    footballClubList.add((FootballClub) object); // adding to the footballClub list
                }
                if ((Match.class == objClass)) { // If the object type is equal to Match type
                    matchList.add((Match) object); // adding to the match list
                }
            }catch (EOFException e) {
                break;
            }
        }
    }
}








