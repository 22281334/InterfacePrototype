import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CleaningStage {

    public CleaningStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        mainStage.setResizable(false);

        BorderPane content = new BorderPane();
        BorderPane leftLayout = new BorderPane();
        BorderPane topLayout = new BorderPane();
        BorderPane bottomLayout = new BorderPane();
        BorderPane centerLayout = new BorderPane();
        BorderPane rightLayout = new BorderPane();

        Image roomPic = new Image("room.png");
        Image homePic = new Image("Home.png");
        Image powerPic = new Image("power.png");
        Image setPic = new Image("setting.png");
        Image timePic = new Image("time.png");

        Button room = new Button("Room",new ImageView(roomPic));
        Button home = new Button("Home Page",new ImageView(homePic));
        Button setting = new Button("Setting",new ImageView(setPic));
        Button time = new Button("Schedule",new ImageView(timePic));
        ToggleButton power = new ToggleButton("Tap to Start",new ImageView(powerPic));

        Text mainTitle = new Text("Cleaning Management");
        mainTitle.setStyle("-fx-font-size: 30");

        double btnWidth = 300;
        double btnHeight = 150;

        home.setStyle("-fx-font-size: 15;");
        room.setStyle("-fx-font-size: 20;");
        power.setStyle("-fx-font-size: 20; " +
                "-fx-background-color: rgba(0,255,0,0.5)");
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

        power.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               String bgColor = power.getBackground().getFills().get(0).toString();
               bgColor = bgColor.substring(bgColor.length()-8);
                if(bgColor.equals("@5e759d9")){
                    power.setStyle("-fx-font-size: 20; " +
                            "-fx-background-color: rgba(255,0,0,0.5)");
                    power.setText("Tap to Stop");
                }
                else{
                    power.setStyle("-fx-font-size: 20; " +
                            "-fx-background-color: rgba(0,255,0,0.5)");
                    power.setText("Tap to Start");
                }
           }
        });

        room.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane roomSelector = new RoomPane().returnPane(centerLayout);
            }
        });

        topLayout.setLeft(home);
        topLayout.setCenter(mainTitle);
        leftLayout.setTop(room);
        leftLayout.setCenter(new Text("Sub"));
        bottomLayout.setRight(power);
        bottomLayout.setLeft(setting);
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
