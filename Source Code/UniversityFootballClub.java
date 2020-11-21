public class UniversityFootballClub extends FootballClub {
    private String universityName;

    public UniversityFootballClub(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsReceived, int goalScored,
                                  int numberOfPoints, int numberOfPlayedMatches, String universityName) {
        super(clubName, clubLocation, wins, draws, defeats, goalsReceived, goalScored, numberOfPoints, numberOfPlayedMatches);
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "lk.oop.coursework.UniversityFootballClub{" +
                "universityName='" + universityName + '\'' +
                '}';
    }
}
