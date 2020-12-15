import java.io.Serializable;

public abstract class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;


    public SportsClub(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }

    public String getClubName() {
        return clubName;
    }


    public String getClubLocation() {
        return clubLocation;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }


    @Override
    public String toString() {
        return "SportsClub{" + "clubName='" + clubName + '\'' + ", clubLocation='" + clubLocation + '\'' + '}';
    }
}
