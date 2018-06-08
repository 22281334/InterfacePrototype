import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //CleaningStage cleaningStage = new CleaningStage(400,100,1000,800);
        //HomePageStage home = new HomePageStage(400,100,1000,800);

        SecurityStage securityStage=new SecurityStage(500,500,0,0);
    }
}
