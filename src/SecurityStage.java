import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.util.Duration;
/**
 *
 * Author: Dayun Liu
 *
 */

public class SecurityStage {


    double sizeX;
    double sizeY;
    double positionX;
    double positionY;
    int tmp = 60;
    Label timeLeft = new Label("60");
    Stage stage = new Stage();


    public SecurityStage(double sizeX, double sizeY, double positionX, double positionY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.positionX = positionX;
        this.positionY = positionY;
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane, sizeX, sizeY);
        stage.setX(positionX + 20);
        stage.setY(positionY);
        GridPane top = new GridPane();

        //top
        Image security = new Image("pic/Security/SecurityIcon.png");
        Image back = new Image("pic/Security/backIcon.png");
        ImageView backView = new ImageView(back);
        ImageView imageView = new ImageView(security);
        backView.setFitWidth(50);
        backView.setFitHeight(38);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        Button backButton = new Button("", backView);
        backButton.setStyle("-fx-background-color: rgba(0,119,255,0.5)");
        top.add(backButton, 0, 0);
        top.add(imageView, 1, 0);
        Label securityLabel = new Label("  Security ");
        securityLabel.setStyle("-fx-font-size: 30");
        top.add(securityLabel, 2, 0);
        mainPane.setTop(top);
        mainPane.getTop().setStyle("-fx-background-color: rgba(0,119,255,0.5)");

        //center
        GridPane center = new GridPane();
        center.add(new Label("\t\t"), 0, 0);
        Label cameraLabel = new Label("Camera ");
        cameraLabel.setStyle("-fx-font-size: 30");
        center.add(cameraLabel, 1, 0);
        String media_URL = getClass().getResource("Video/RoomCamera.mp4").toString();
        Media room1Media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(room1Media);
        mediaPlay(mediaPlayer,center);

        EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.dispose();
                new HomePageStage(400, 100, 1000, 800);
                stage.close();
            }
        };
        backButton.setOnAction(backButtonEvent);
        ScrollBar sc = new ScrollBar();
        scrollBar(sc,scene,center);
        mainPane.setRight(sc);
        center.setStyle("-fx-border-width: 2px; -fx-background-color: rgba(135,206,235); -fx-border-color: rgba(0,119,255,0.5);");
        mainPane.setCenter(center);


        //bottom
        BorderPane bottomArea = new BorderPane();
        GridPane buttonArea = new GridPane();
        Button lockButton = new Button(" Lock ");
        Button unlockButton = new Button("Unlock");
        lockButton.setStyle("-fx-font-size: 35");
        unlockButton.setStyle("-fx-font-size: 35");
        Label leavingLabel = new Label("\tLeaving mode ");
        leavingLabel.setStyle("-fx-font-size: 30");
        Image lockImage = new Image("pic/Security/lock.png");
        Image unlockImage = new Image("pic/Security/unlock.png");
        ImageView lockView = new ImageView(lockImage);
        ImageView unlockView = new ImageView(unlockImage);
        lockView.setFitWidth(94.5);
        lockView.setFitHeight(146.5);
        unlockView.setFitWidth(94.5);
        unlockView.setFitHeight(146.5);
        lockClick(bottomArea, lockView, lockButton,unlockButton);
        unlockClick(bottomArea, unlockView, unlockButton,lockButton);
        buttonArea.add(new Label(""), 0, 0);
        buttonArea.add(new Label("\t\t"), 0, 1);
        buttonArea.add(lockButton, 1, 1);
        buttonArea.add(new Label("\t"), 2, 1);
        buttonArea.add(unlockButton, 3, 1);
        bottomArea.setTop(leavingLabel);
        bottomArea.setCenter(buttonArea);
        bottomArea.setRight(unlockView);
        mainPane.setBottom(bottomArea);
        mainPane.getBottom().setStyle("-fx-background-color: rgba(135,206,250);");

        stage.setTitle("Security");
        stage.setScene(scene);
        stage.show();
    }


    /**
     *
     * @param sc
     * @param scene
     * @param center
     * create a new scrollBar
     *
     */
    private void scrollBar(ScrollBar sc, Scene scene,GridPane center){
        sc.setLayoutX(scene.getWidth() - sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(5);
        sc.setMax(10);
        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                center.setLayoutY(-new_val.doubleValue());
            }
        });
    }

    /**
     *
     * @param mediaPlayer
     * @param center
     * four media video added to center
     *
     */
    private void mediaPlay(MediaPlayer mediaPlayer,GridPane center){
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView1 = new MediaView(mediaPlayer);
        mediaView1.setFitHeight(300);
        mediaView1.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        center.add(mediaView1, 1, 1);
        Text room1 = new Text(" Room 1");
        room1.setFill(Color.BLACK);
        center.add(room1, 0, 1);

        center.add(new Label("\t\t"), 2, 1);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView2 = new MediaView(mediaPlayer);
        mediaView2.setFitHeight(300);
        mediaView2.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        center.add(mediaView2, 3, 1);
        Text room2 = new Text(" Room 2");
        room2.setFill(Color.BLACK);
        center.add(room2, 2, 1);
        center.add(new Label("\t\t"), 0, 2);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView3 = new MediaView(mediaPlayer);
        mediaView3.setFitHeight(300);
        mediaView3.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        center.add(mediaView3, 1, 3);
        Text room3 = new Text(" Room 3");
        room3.setFill(Color.BLACK);
        center.add(room3, 0, 3);
        center.add(new Label("\t\t"), 2, 3);

        mediaPlayer.setAutoPlay(true); //设置自动播放
        MediaView mediaView4 = new MediaView(mediaPlayer);
        mediaView4.setFitHeight(300);
        mediaView4.setFitWidth(400);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        center.add(mediaView4, 3, 3);
        Text room4 = new Text(" Room 4");
        room4.setFill(Color.BLACK);
        center.add(room4, 2, 3);
        center.add(new Label(""), 0, 4);
        center.getChildren().addAll();
    }

    /**
     *
     * @param bottomArea
     * @param unlockButton
     * @param unlockView
     * @param lockButton
     * click event, stop lock button time loop,
     * change unlock and lock button style
     */
    private void unlockClick(BorderPane bottomArea, ImageView unlockView, Button unlockButton,Button lockButton) {
        EventHandler<ActionEvent> unlockEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tmp=-1;
                bottomArea.setRight(unlockView);
                unlockButton.setStyle("-fx-background-color: rgba(255,99,71); -fx-font-size: 35;");
                lockButton.setStyle("-fx-font-size: 35");
            }
        };
        unlockButton.setOnAction(unlockEvent);
    }

    /**
     *
     * @param bottomArea
     * @param unlockButton
     * @param lockView
     * @param lockButton
     * click event, alert a time reminder, close alert when time run out
     * change unlock and lock buttons style
     */
    private void lockClick(BorderPane bottomArea, ImageView lockView, Button lockButton,Button unlockButton) {
        EventHandler<ActionEvent> lockEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (tmp != 60) {
                    resetTmp();
                }
                Alert information = new Alert(Alert.AlertType.INFORMATION, " Lock ");
                information.setHeaderText("Lock Mode");
                Timeline time = new Timeline(new KeyFrame(Duration.millis(1000), e -> timeCount(information, bottomArea, lockView)));
                time.setCycleCount(60);
                time.play();

                information.setTitle("Lock");
                GridPane gridPane = new GridPane();
                Label infText=new Label("Locked home, Time left for leaving home: ");
                infText.setStyle("-fx-font-size: 20");
                gridPane.add(infText, 0, 0);
                timeLeft.setStyle("-fx-font-size: 20");
                gridPane.add(timeLeft, 1, 0);
                ImageView arrowView = new ImageView(new Image("pic/Security/arrow.png"));
                arrowView.setFitHeight(40);
                arrowView.setFitWidth(40);
                gridPane.add(new ImageView(new Image("pic/Security/unlock.png")), 0, 1);
                gridPane.add(arrowView, 1, 1);
                gridPane.add(new ImageView(new Image("pic/Security/lock.png")), 2, 1);
                information.getDialogPane().setContent(gridPane);
                information.show();
                lockButton.setStyle("-fx-background-color: rgba(0,255,127); -fx-font-size: 35;");
                unlockButton.setStyle("-fx-font-size: 35");


            }
        };
        lockButton.setOnAction(lockEvent);
    }

    /**
     *
     * @param information
     * @param bottomArea
     * @param lockView
     * help lock event count time,
     * time run out close the information alert
     *
     */
    private void timeCount(Alert information, BorderPane bottomArea, ImageView lockView) {
        if (tmp > 0) {
            tmp--;
            timeLeft.setText(tmp + "");
        } else if (tmp == 0) {
            information.close();
            bottomArea.setRight(lockView);


        }
    }

    /**
     * reset tmp
     */
    private void resetTmp() {
        tmp = 60;
    }
}
