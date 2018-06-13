import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CleaningStage {

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);


        Image roomPic = new Image(getClass().getResourceAsStream("room.png"));

        Button room = new Button("Room",new ImageView(roomPic));
        GridPane cleaningLayout = new GridPane();
        cleaningLayout.add(room,0,0);
        Scene scene = new Scene(cleaningLayout,sizeX,sizeY);

        mainStage.setScene(scene);
        mainStage.show();
    }



}
