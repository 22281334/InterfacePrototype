import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public Main(){
        //HomeStage home = new HomeStage(300,300,500,500);
        CleaningStage cleaningStage = new CleaningStage(400,100,1000,800);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
