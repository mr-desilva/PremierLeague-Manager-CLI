public class SchoolFootballClub extends FootballClub {
    private String schoolName;

    public SchoolFootballClub(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsReceived,
                              int goalScored, int numberOfPoints, int numberOfPlayedMatches, String schoolName) {
        super(clubName, clubLocation, wins, draws, defeats, goalsReceived, goalScored, numberOfPoints, numberOfPlayedMatches);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "lk.oop.coursework.SchoolFootballClub{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }
}
