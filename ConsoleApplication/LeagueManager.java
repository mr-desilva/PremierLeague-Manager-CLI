import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public interface LeagueManager {
    void addClub (FootballClub footballClub);
    void deleteClub (String clubName);
    void displayStat(String clubName);
    void displayPLMtable();
    void addMatch(String footballClub1, String footballClub2);
    void saveData(String fileName) throws IOException;
    void loadData(String fileName);
    void playRandomMatch();
    void guiPlayMatch(Stage stage, Scene scene);
    void guiMain();
}
