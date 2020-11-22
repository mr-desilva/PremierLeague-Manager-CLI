import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private int wins;
    private int draws;
    private int defeats;
    private int goalsReceived;
    private int goalScored;
    private int numberOfPoints;
    private int numberOfPlayedMatches;

    public FootballClub(String clubName, String clubLocation) {
        super(clubName, clubLocation);
    }

    public FootballClub(String clubName, String clubLocation, int wins, int draws,
                        int defeats, int goalsReceived, int goalScored, int numberOfPoints, int numberOfPlayedMatches) {
        super(clubName, clubLocation);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsReceived = goalsReceived;
        this.goalScored = goalScored;
        this.numberOfPoints = numberOfPoints;
        this.numberOfPlayedMatches = numberOfPlayedMatches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfPlayedMatches() {
        return numberOfPlayedMatches;
    }

    public void setNumberOfPlayedMatches(int numberOfPlayedMatches) {
        this.numberOfPlayedMatches = numberOfPlayedMatches;
    }

    @Override
    public String toString() {
        return "Club Name=" + this.getClubName()+" "+
                "wins=" + wins +
                " draws=" + draws +
                " defeats=" + defeats +
                " goalsReceived=" + goalsReceived +
                " goalScored=" + goalScored +
                " numberOfPoints=" + numberOfPoints +
                " numberOfPlayedMatches=" + numberOfPlayedMatches+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub footballClub = (FootballClub) o;
        return Objects.equals(getClubName(),footballClub.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, draws, defeats, goalsReceived, goalScored, numberOfPoints, numberOfPlayedMatches);
    }

    @Override
    public int compareTo(FootballClub o){
        if (this.getNumberOfPoints() == o.getNumberOfPoints()){
            return o.getGoalScored() - this.getGoalsReceived();
        }else {
            return o.getNumberOfPoints() - this.getNumberOfPoints();
        }
    }
}
