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

        double btnWidth = 300;
        double btnHeight = 150;

        home.setStyle("-fx-font-size: 20;");
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
               bgColor = bgColor.substring(bgColor.length()-8);  // Get current color "@5e759d9"(Green)
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
                GridPane roomSelector = new GridPane();
                double subBtnWidth = 100;
                double subBtnHeight = 100;

                ToggleButton living = new ToggleButton("Living Room");
                ToggleButton kitchen = new ToggleButton("kitchen");
                ToggleButton bedRoom = new ToggleButton("Bed Room");
                ToggleButton toilet = new ToggleButton("Toilet");
                Button save = new Button("Save changes");

                living.setPrefWidth(subBtnWidth);
                living.setPrefHeight(subBtnHeight);
                kitchen.setPrefWidth(subBtnWidth);
                kitchen.setPrefHeight(subBtnHeight);
                bedRoom.setPrefWidth(subBtnWidth);
                bedRoom.setPrefHeight(subBtnHeight);
                toilet.setPrefWidth(subBtnWidth);
                toilet.setPrefHeight(subBtnHeight);
                save.setPrefWidth(subBtnWidth);
                save.setPrefHeight(80);
                living.setStyle("-fx-font-size: 13;");
                kitchen.setStyle("-fx-font-size: 13;");
                bedRoom.setStyle("-fx-font-size: 13;");
                toilet.setStyle("-fx-font-size: 13;");
                save.setStyle("-fx-font-size: 12;");

                roomSelector.add(living,0,0);
                roomSelector.add(kitchen,2,0);
                roomSelector.add(bedRoom,0,1);
                roomSelector.add(toilet,2,1);
                roomSelector.add(save,1,2);

                roomSelector.setHgap(55);
                roomSelector.setVgap(100);

                centerLayout.setStyle("-fx-background-color: rgba(192,192,192,0.5)");
                roomSelector.setStyle("-fx-background-color: rgba(192,192,192,0.8)");

                Text title = new Text("Please Select Room");
                title.setStyle("-fx-font-size: 30");

                centerLayout.setBottom(roomSelector);
                centerLayout.setCenter(title);
            }
        });

        topLayout.setLeft(home);
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
