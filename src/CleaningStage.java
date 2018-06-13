import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Author: Yanjing Teng
 *
 */
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

        // Set css styles for 4 BorderPanes
        topLayout.setPrefHeight(150);
        topLayout.getStyleClass().add("TopBG");
        rightLayout.getStyleClass().add("MainBG");
        leftLayout.getStyleClass().add("MainBG");
        bottomLayout.getStyleClass().add("MainBG");

        Image roomPic = new Image("pic/Cleaning/room.png");
        Image homePic = new Image("pic/Cleaning/Home.png");
        Image powerPic = new Image("pic/Cleaning/power.png");
        Image setPic = new Image("pic/HomePage/setting.png");
        Image timePic = new Image("pic/Cleaning/time.png");
        // Create 5 buttons with pictures
        Button room = new Button("Room",new ImageView(roomPic));
        Button home = new Button("Home Page",new ImageView(homePic));
        Button setting = new Button("Setting",new ImageView(setPic));
        Button schedule = new Button("Schedule",new ImageView(timePic));
        ToggleButton power = new ToggleButton("Tap to Start",new ImageView(powerPic));

        // Create date on the top right
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Text clock = new Text(dateFormat.format(date));
        clock.setStyle("-fx-font-size: 20;");
        // Create title on the top
        Text mainTitle = new Text("Cleaning Management");
        mainTitle.setStyle("-fx-font-size: 24");

        // Set the size of buttons
        double btnWidth = 300;
        double btnHeight = 150;
        power.setPrefWidth(btnWidth);
        power.setPrefHeight(btnHeight);
        room.setPrefWidth(btnWidth);
        room.setPrefHeight(btnHeight);
        setting.setPrefWidth(btnWidth);
        setting.setPrefHeight(btnHeight);
        schedule.setPrefWidth(btnWidth);
        schedule.setPrefHeight(btnHeight);
        power.setStyle("-fx-font-size: 20; " +
                "-fx-background-color: rgba(0,255,0,0.5)");

        // Switch colors of power button between red and green
        power.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               String bgColor = power.getBackground().getFills().get(0).toString();
               bgColor = bgColor.substring(bgColor.length()-8);     // Get current color(Green:@5e759d9)
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

        // Add new pane after click "room" or "schedule" buttons on "centerLayout" pane
        room.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane roomSelector = new RoomPane().returnPane(centerLayout);
            }
        });
        schedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane timeSelector = new TimePane().returnPane(centerLayout);
            }
        });
        // Return to home page
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomePageStage homepage = new HomePageStage(positionX,positionY,sizeX,sizeY);
                mainStage.close();
            }
        });

        // Set the position of buttons and title
        topLayout.setRight(clock);
        topLayout.setLeft(home);
        topLayout.setCenter(mainTitle);
        leftLayout.setTop(room);
        bottomLayout.setRight(power);
        bottomLayout.setLeft(setting);
        rightLayout.setTop(schedule);

        // Set 5 pane on main pane "content"
        content.setTop(topLayout);
        content.setBottom(bottomLayout);
        content.setLeft(leftLayout);
        content.setCenter(centerLayout);
        content.setRight(rightLayout);

        Scene scene = new Scene(content,sizeX,sizeY);
        scene.getStylesheets().add(getClass().getResource("Style/CleaningStyle.css").toExternalForm());
        mainStage.setScene(scene);
        mainStage.show();
    }
}
