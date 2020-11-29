package apiarcade;
/*
*Last updated on 10/28/20
*
*Launches the desired fxml file
*
*Contributing authors
*@author Francisco
*/
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class RunApp extends Application{

    private static Stage primaryStage;
    private static AnchorPane mainLayout;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("The Elon Musketeers");
        showLoginView();
    }

    public static void showLoginView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/login.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showMainGameView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/mainGame.fxml"));
        AnchorPane mainGame = loader.load();
        Scene scene = new Scene(mainGame);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showRegisterView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/register.fxml"));
        AnchorPane register = loader.load();
        Scene scene = new Scene(register);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showLeaderboardsView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/leaderboards.fxml"));
        AnchorPane leaderboards = loader.load();
        Scene scene = new Scene(leaderboards);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public static void showProfileView() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(RunApp.class.getResource("/views/profile.fxml"));
//        AnchorPane profile = loader.load();
//        Scene scene = new Scene(profile);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    public static void showPassRecoveryView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/forgotPassword.fxml"));
        AnchorPane passReco = loader.load();
        Scene scene = new Scene(passReco);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showGenericGame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RunApp.class.getResource("/views/genericGame.fxml"));
        AnchorPane genGame = loader.load();
        Scene scene = new Scene(genGame);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
