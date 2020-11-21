import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {
    private List<FootballClub> footballClubList = new ArrayList<>();

    @Override
    public void addClub(FootballClub footballClub) {
        // use this to check if the club is already in the list
        for (FootballClub footballClub1 : footballClubList) {
            if (footballClub1.equals(footballClub)) {
                System.out.println("This club is already in the List");
                return;
            }
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
        System.out.println("===============Premier League Table===============");
        for(FootballClub footballClub1 : footballClubList){
            System.out.print(footballClub1.toString());
        }
    }


    public static void main(String[] args) {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        menuLoop:
        while (true) {
            System.out.println("\n=====Welcome to the Premier League Manager=====");
            System.out.println("Select options from the menu to continue");
            System.out.println("Enter 1 to add a Football Club");
            System.out.println("Enter 2 to delete a Football Club");
            System.out.println("Enter 3 to view statistics for a selected club");
            System.out.println("Enter 4 to view the Premier League Table");
            System.out.println("Enter 5 to add test club data");
            System.out.print("Enter 6 to exit the programme : ");
            Scanner scanner = new Scanner(System.in);
            String menuSelect = scanner.nextLine();
            switch (menuSelect) {
                case ("1"):
                    System.out.println("Enter the Name of the club : ");
                    String clubName = scanner.nextLine();
                    System.out.println("Enter the location(city) of the club : ");
                    String clubLocation = scanner.nextLine();
                    FootballClub footballClub = new FootballClub(clubName, clubLocation);
                    premierLeagueManager.addClub(footballClub);
                    break;
                case ("2"):
                    System.out.println("Enter the name of the football club to remove : ");
                    String removeClub = scanner.nextLine();
                    premierLeagueManager.deleteClub(removeClub);
                    break;
                case ("3"):
                    System.out.println("Enter the football club name to view statistics : ");
                    String displayClub = scanner.nextLine();
                    premierLeagueManager.displayStat(displayClub);
                    break;
                case ("4"):
                    premierLeagueManager.displayPLMtable();
                    break;
                case ("5"):
                    FootballClub footballClubTest1 = new FootballClub("westminster", "UK",5,4,10,40,80,120,30);
                    FootballClub footballClubTest2 = new FootballClub("manchester", "UK",10,0,7,21,42,60,20);
                    FootballClub footballClubTest3 = new FootballClub("Lions", "Sri Lanka",15,6,22,36,72,100,14);
                    FootballClub footballClubTest4 = new FootballClub("nuclear", "Russia",25,1,32,42,23,60,30);
                    premierLeagueManager.addClub(footballClubTest1);
                    premierLeagueManager.addClub(footballClubTest2);
                    premierLeagueManager.addClub(footballClubTest3);
                    premierLeagueManager.addClub(footballClubTest4);
                    break;
                case ("6"):
                    break menuLoop;
                default:
                    System.out.println("Enter a valid option!!");

            }
        }
    }
}



