
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new HomePageStage(400,100,1000,800);
        //new ApplianceStage(400,100,1000,800);
        //new Setting(400,100,1000,800);
        //new HelpStage(1000,800,400,100);
        //new SecurityStage(1000,800,400,100);
    }
}
