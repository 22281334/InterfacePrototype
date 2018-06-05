import javafx.application.Application;
import javafx.stage.Stage;

public class CleaningStage {

    double positionX;
    double positionY;
    double sizeX;
    double sizeY;

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        this.positionX=positionX;
        this.positionY=positionY;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        mainStage.show();
    }



}
