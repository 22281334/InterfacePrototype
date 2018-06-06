import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CleaningStage {

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);

        BorderPane content = new BorderPane();
        BorderPane leftLayout = new BorderPane();
        BorderPane topLayout = new BorderPane();
        BorderPane bottomLayout = new BorderPane();
        BorderPane centerLayout = new BorderPane();
        BorderPane rightLayout = new BorderPane();

        Image roomPic = new Image(getClass().getResourceAsStream("room.png"));
        Image homePic = new Image(getClass().getResourceAsStream("Home.png"));
        Image powerPic = new Image(getClass().getResourceAsStream("power.png"));
        Image setPic = new Image(getClass().getResourceAsStream("setting.png"));
        Image timePic = new Image(getClass().getResourceAsStream("time.png"));
        Button room = new Button("Room",new ImageView(roomPic));
        Button home = new Button("Home Page",new ImageView(homePic));
        Button setting = new Button("Setting",new ImageView(setPic));
        Button time = new Button("Schedule",new ImageView(timePic));

        ToggleButton power = new ToggleButton("Power ON",new ImageView(powerPic));


        double btnWidth = 300;
        double btnHeight = 150;

        home.setStyle("-fx-font-size: 20;");
        room.setStyle("-fx-font-size: 20;");
        power.setStyle("-fx-font-size: 20;");
        setting.setStyle("-fx-font-size: 20;");
        time.setStyle("-fx-font-size: 20;");

        power.setPrefWidth(btnWidth);
        power.setPrefHeight(btnHeight);
        room.setPrefWidth(btnWidth);
        room.setPrefHeight(btnHeight);
        setting.setPrefWidth(btnWidth);
        setting.setPrefHeight(btnHeight);
        time.setPrefWidth(btnWidth);
        time.setPrefHeight(btnHeight);

        topLayout.setLeft(home);
        leftLayout.setTop(room);
        leftLayout.setCenter(new Text("Sub"));
        bottomLayout.setRight(power);
        bottomLayout.setLeft(setting);
        centerLayout.setCenter(new Text("Main"));
        rightLayout.setTop(time);

        content.setTop(topLayout);
        content.setBottom(bottomLayout);
        content.setLeft(leftLayout);
        content.setCenter(centerLayout);
        content.setRight(rightLayout);

        Scene scene = new Scene(content,sizeX,sizeY);
        mainStage.setScene(scene);
        mainStage.show();
    }



}
