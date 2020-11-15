public class SportsClub {
    private String clubName;
    private String clubLocation;

    public SportsClub() {
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

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                '}';
    }
}
