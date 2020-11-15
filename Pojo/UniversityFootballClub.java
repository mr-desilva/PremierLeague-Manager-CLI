public class UniversityFootballClub {
    private String clubName;
    private String clubLocation;
    private int wins;
    private int draws;
    private int defeats;
    private int goalsReceived;
    private int goalScored;
    private int numberOfPoints;
    private int numberOfPlayedMatches;
    private String universityName;

    public UniversityFootballClub() {
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
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

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String   toString() {
        return "UniversityFootballClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                ", wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goalsReceived=" + goalsReceived +
                ", goalScored=" + goalScored +
                ", numberOfPoints=" + numberOfPoints +
                ", numberOfPlayedMatches=" + numberOfPlayedMatches +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
