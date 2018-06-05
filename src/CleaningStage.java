import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CleaningStage {

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        GridPane mainPane = new GridPane();
        Scene scene = new Scene(mainPane,sizeX,sizeY);

        mainStage.setScene(scene);
        mainStage.show();
    }



}
