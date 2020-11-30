import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    public static void addClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Name of the club : ");
        String clubName = scanner.nextLine();
        System.out.println("Enter the location(city) of the club : ");
        String clubLocation = scanner.nextLine();
        FootballClub footballClub = new FootballClub(clubName, clubLocation);
        premierLeagueManager.addClub(footballClub);
    }
    public static void deleteClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the football club to remove : ");
        String removeClub = scanner.nextLine();
        premierLeagueManager.deleteClub(removeClub);
    }
    public static void displayStat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the football club name to view statistics : ");
        String displayClub = scanner.nextLine();
        premierLeagueManager.displayStat(displayClub);
    }
    public static void displayPLMtable(){
        premierLeagueManager.displayPLMtable();
    }

    public static void addMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the football club name : "); // Getting the club 1 name
        String footballClubTeam1 = scanner.nextLine();
        System.out.println("Enter the second football club name : "); // Getting the club 1 name
        String footballClubTeam2 = scanner.nextLine();
        premierLeagueManager.addMatch(footballClubTeam1,footballClubTeam2);
    }

    public static void addTestData(){
        FootballClub footballClubTest1 = new FootballClub("westminster", "UK",5,4,10,40,80,120,30);
        FootballClub footballClubTest2 = new FootballClub("manchester", "UK",10,0,7,21,42,60,20);
        FootballClub footballClubTest3 = new FootballClub("Lions", "Sri Lanka",15,6,22,36,72,100,14);
        FootballClub footballClubTest4 = new FootballClub("nuclear", "Russia",25,1,32,12,23,60,30);
        premierLeagueManager.addClub(footballClubTest1);
        premierLeagueManager.addClub(footballClubTest2);
        premierLeagueManager.addClub(footballClubTest3);
        premierLeagueManager.addClub(footballClubTest4);
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        premierLeagueManager.loadData("saveFile.txt");
        menuLoop:
        while (true) {
            System.out.println("\n=====Welcome to the Premier League Manager=====");
            System.out.println("Select options from the menu to continue");
            System.out.println("Enter 1 to add a Football Club");
            System.out.println("Enter 2 to delete a Football Club");
            System.out.println("Enter 3 to view statistics for a selected club");
            System.out.println("Enter 4 to view the Premier League Table");
            System.out.println("Enter 5 to add test club data");
            System.out.println("Enter 6 to add a played match");
            System.out.print("Enter 7 to exit the programme : ");
            Scanner scanner = new Scanner(System.in);
            String menuSelect = scanner.nextLine();
            switch (menuSelect) {
                case ("1"):
                    addClub();
                    break;
                case ("2"):
                    deleteClub();
                    break;
                case ("3"):
                    displayStat();
                    break;
                case ("4"):
                    displayPLMtable();
                    break;
                case ("5"):
                    addTestData();
                    break;
                case ("6"):
                    addMatch();
                    break;
                case ("7"):
                    premierLeagueManager.saveData("saveFile.txt");
                    break menuLoop;
                default:
                    System.out.println("Enter a valid option!!");

            }
        }
    }
}
