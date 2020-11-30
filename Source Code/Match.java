import java.io.Serializable;

public class Match implements Serializable {
    private String team1;
    private String team2;
    private int team1GoalScore;
    private int team2GoalScore;
    private Date date;

    public Match() {}

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getTeam1GoalScore() {
        return team1GoalScore;
    }

    public void setTeam1GoalScore(int team1GoalScore) {
        this.team1GoalScore = team1GoalScore;
    }

    public int getTeam2GoalScore() {
        return team2GoalScore;
    }

    public void setTeam2GoalScore(int team2GoalScore) {
        this.team2GoalScore = team2GoalScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
