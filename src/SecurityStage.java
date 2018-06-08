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
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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

        Image security = new Image("pic/SecurityIcon.png");
        Image back = new Image("pic/backIcon.png");
        ImageView backView = new ImageView(back);
        ImageView imageView = new ImageView(security);
        backView.setFitWidth(20);
        backView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        //top.add(backView, 0, 0);
        Button backButton = new Button("",backView);
        top.add(backButton,0,0);
        top.add(imageView, 1, 0);
        top.add(new Label("  Security "), 2, 0);
        EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new HomePageStage(400,100,1000,800);
                stage.close();;
            }
        };
        backButton.setOnAction(startButton);

        content.setTop(top);



        //center
        GridPane center = new GridPane();
        center.add(new Label("   "),0,0);
        center.add(new Label("Camera "), 1, 0);
        String media_URL = getClass().getResource("Video/Room1.mp4").toString();
        Media room1Media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(room1Media);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(200);
        mediaView.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //设置循环播放（设置播放次数）
        center.add(mediaView,1,1);


        ScrollBar sc=new ScrollBar();
        VBox vb=new VBox();

        sc.setLayoutX(scene.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(180);
        sc.setMax(360);

        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                center.setLayoutY(-new_val.doubleValue());
            }
        });
        content.setRight(sc);


        content.setCenter(center);


        //bottom
        BorderPane bottomArea = new BorderPane();
        Button lockButton=new Button("Lock");
        Button unlockButton=new Button("Unlock");
        lockButton.getStyleClass().add("button");
        unlockButton.getStyleClass().add("button");
        bottomArea.setTop(new Label(" Leaving mode "));
        bottomArea.setLeft(lockButton);
        bottomArea.setRight(unlockButton);
        content.setBottom(bottomArea);


        stage.setTitle("Security");

        //start the window.

        stage.setScene(scene);
        stage.show();
    }
}
