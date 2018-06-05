import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CleaningStage {

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);

        BorderPane content = new BorderPane();
        GridPane cleaningLayout = new GridPane();
        GridPane topLayout = new GridPane();

        Image roomPic = new Image(getClass().getResourceAsStream("room.png"));
        Image homePic = new Image(getClass().getResourceAsStream("Home.png"));
        Button room = new Button("Room",new ImageView(roomPic));
        Button home = new Button("",new ImageView(homePic));

        room.setStyle("-fx-font-size: 20;");


        cleaningLayout.add(room,0,0);
        topLayout.add(home,0,0);
        content.setTop(topLayout);
        content.setCenter(cleaningLayout);

        Scene scene = new Scene(content,sizeX,sizeY);
        mainStage.setScene(scene);
        mainStage.show();
    }



}
