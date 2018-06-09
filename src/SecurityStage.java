import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SecurityStage {


    double sizeX;
    double sizeY;
    double positionX;
    double positionY;
    Stage stage = new Stage();


    public SecurityStage(double sizeX, double sizeY, double positionX, double positionY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.positionX = positionX;
        this.positionY = positionY;
        BorderPane content = new BorderPane();
        Scene scene = new Scene(content, sizeX, sizeY);

        stage.setX(positionX + 20);
        stage.setY(positionY);


        GridPane top = new GridPane();

        Image security = new Image("pic/Security/SecurityIcon.png");
        Image back = new Image("pic/Security/backIcon.png");
        ImageView backView = new ImageView(back);
        ImageView imageView = new ImageView(security);
        backView.setFitWidth(50);
        backView.setFitHeight(38);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        //top.add(backView, 0, 0);
        Button backButton = new Button("", backView);
        top.add(backButton, 0, 0);
        top.add(imageView, 1, 0);
        Label securityLabel = new Label("  Security ");
        securityLabel.setStyle("-fx-font-size: 30");
        top.add(securityLabel, 2, 0);
        EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new HomePageStage(400, 100, 1000, 800);
                stage.close();
            }
        };
        backButton.setOnAction(startButton);

        content.setTop(top);


        //center
        GridPane center = new GridPane();
        center.add(new Label("\t\t"), 0, 0);
        Label cameraLabel = new Label("Camera ");
        cameraLabel.setStyle("-fx-font-size: 30");
        center.add(cameraLabel, 1, 0);
        String media_URL = getClass().getResource("Video/RoomCamera.mp4").toString();
        Media room1Media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(room1Media);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView1 = new MediaView(mediaPlayer);
        mediaView1.setFitHeight(300);
        mediaView1.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
        center.add(mediaView1, 1, 1);
        Text room1=new Text("Room 1");
        room1.setFill(Color.BLACK);
        center.add(room1, 0, 1);

        center.add(new Label("\t\t"), 2, 1);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView2 = new MediaView(mediaPlayer);
        mediaView2.setFitHeight(300);
        mediaView2.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
        center.add(mediaView2, 3, 1);
        Text room2=new Text("Room 2");
        room2.setFill(Color.BLACK);
        center.add(room2, 2, 1);
        center.add(new Label("\t\t"), 0, 2);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView3 = new MediaView(mediaPlayer);
        mediaView3.setFitHeight(300);
        mediaView3.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
        center.add(mediaView3, 1, 3);
        Text room3=new Text("Room 3");
        room3.setFill(Color.BLACK);
        center.add(room3, 0, 3);
        center.add(new Label("\t\t"), 2, 3);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView4 = new MediaView(mediaPlayer);
        mediaView4.setFitHeight(300);
        mediaView4.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
        center.add(mediaView4, 3, 3);
        Text room4=new Text("Room 4");
        room4.setFill(Color.BLACK);
        center.add(room4, 2, 3);
        center.add(new Label(""), 0, 4);
        center.getChildren().addAll();


        ScrollBar sc = new ScrollBar();

        sc.setLayoutX(scene.getWidth() - sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(10);
        sc.setMax(20);



        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                center.setLayoutY(-new_val.doubleValue());
                System.out.println(-new_val.doubleValue());
            }
        });



        content.setRight(sc);
        center.setStyle("-fx-border: 2px solid; -fx-border-color: black;");
        content.setCenter(center);


        //bottom
        BorderPane bottomArea = new BorderPane();
        Button lockButton = new Button("Lock");
        Button unlockButton = new Button("Unlock");
        Button close = new Button("Close");

        lockButton.setStyle("-fx-font-size: 20; -fx-background-color: green;");
        unlockButton.setStyle("-fx-font-size: 20; -fx-background-color: red");
        Label leavingLabel = new Label(" Leaving mode ");
        leavingLabel.setStyle("-fx-font-size: 30");
        bottomArea.setTop(leavingLabel);
        bottomArea.setLeft(lockButton);
        bottomArea.setRight(close);
        bottomArea.setCenter(unlockButton);
        bottomArea.setBottom(new ImageView(new Image("pic/Security/backIcon.png")));
        content.setBottom(bottomArea);


        stage.setTitle("Security");

        //start the window.
        stage.setScene(scene);
        stage.show();
    }
}
