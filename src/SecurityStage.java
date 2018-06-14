import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Dayun Liu
 */

public class SecurityStage {


    double sizeX;
    double sizeY;
    double positionX;
    double positionY;
    int tmp = 60;
    Label timeLeft = new Label("60");
    Stage stage = new Stage();
    Boolean frontDoorStatus = true;
    Boolean backDoorStatus = true;
    Boolean garageDoorStatus = true;
    Boolean entryDoorStatus = true;
    Boolean kitchenDoorStatus = true;
    Boolean pantryDoorStatus = true;
    Boolean lockAllDoorStatus = true;
    Button frontDoorButton = new Button("On ");
    Button backDoorButton = new Button("On ");
    Button garageDoorButton = new Button("On ");
    Button entryDoorButton = new Button("On ");
    Button kitchenDoorButton = new Button("On ");
    Button pantryDoorButton = new Button("On ");

    public SecurityStage(double sizeX, double sizeY, double positionX, double positionY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.positionX = positionX;
        this.positionY = positionY;
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane, sizeX, sizeY);
        stage.setX(positionX);
        stage.setY(positionY);
        BorderPane title = new BorderPane();

        //top
        BorderPane topPane = new BorderPane();
        Image security = new Image("pic/Security/SecurityIcon.png");
        Image back = new Image("pic/Security/backIcon.png");
        ImageView backView = new ImageView(back);
        ImageView imageView = new ImageView(security);
        backView.setFitWidth(50);
        backView.setFitHeight(38);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Button backButton = new Button("", backView);
        Label securityLabel = new Label("  Security ");
        securityLabel.setStyle("-fx-font-size: 30");
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Text clock = new Text(dateFormat.format(date));
        clock.setStyle("-fx-font-size: 20;");
        title.setLeft(imageView);
        title.setCenter(securityLabel);

        TabPane tabPane = new TabPane();

        Tab lockTab = new Tab("Lock");
        lockTab.setGraphic(createTabImgView("pic/Security/lock.png"));

        tabPane.getTabs().add(lockTab);

        Tab cameraTab = new Tab("Camera");
        cameraTab.setGraphic(createTabImgView("pic/Security/camera.png"));
        tabPane.getTabs().add(cameraTab);



        tabPane.tabMinWidthProperty().setValue(475);
        tabPane.tabMinHeightProperty().setValue(50);


        topPane.setBottom(tabPane);
        topPane.setCenter(title);
        topPane.setLeft(backButton);
        topPane.setRight(clock);
        mainPane.setTop(topPane);
        mainPane.getTop().setStyle("-fx-background-color: rgba(0,119,255,0.1)");

        //center
        GridPane cameraArea = new GridPane();

        cameraArea.add(new Label("\t\t"), 0, 0);
        Label cameraLabel = new Label("Camera ");
        cameraLabel.setStyle("-fx-font-size: 30");
        cameraArea.add(cameraLabel, 1, 0);
        String media_URL = getClass().getResource("Video/RoomCamera.mp4").toString();
        Media room1Media = new Media(media_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(room1Media);
        mediaPlay(mediaPlayer, cameraArea);

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
        scrollBar(sc, scene, cameraArea);
        mainPane.setRight(sc);
        cameraArea.setStyle("-fx-border-width: 2px; -fx-background-color: rgba(135,206,235,0.3); -fx-border-color: rgba(0,119,255,0.5);");
        mainPane.setCenter(cameraArea);


        //bottom
        BorderPane leftLockArea = new BorderPane();
        BorderPane lockArea = new BorderPane();
        BorderPane rightLockArea = new BorderPane();
        GridPane lockLabelArea = new GridPane();
        GridPane buttonArea = new GridPane();

        Button lockAllButton = new Button("Press To Lock All", new ImageView(new Image("pic/Security/unlock.png",
                30, 40, false, true)));


        lockAllButton.setStyle("-fx-font-size: 30");
        lockAllButton.setPrefWidth(1000);
        lockAllClick(lockAllButton);


        Label frontDoorLabel = new Label("Front Door");
        frontDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(frontDoorLabel, 0, 0);
        Label backDoorLabel = new Label("Back Door");
        backDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(backDoorLabel, 0, 1);
        Label garageDoorLabel = new Label("Garage Door");
        garageDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(garageDoorLabel, 0, 2);
        Label entryDoorLabel = new Label("Entry Door");
        entryDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(entryDoorLabel, 0, 3);
        Label kitchenDoorLabel = new Label("Kitchen Door");
        kitchenDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(kitchenDoorLabel, 0, 4);
        Label pantryDoorLabel = new Label("Pantry Door");
        pantryDoorLabel.setStyle("-fx-font-size: 35");
        lockLabelArea.add(pantryDoorLabel, 0, 5);

        frontDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");
        backDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");
        garageDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");
        entryDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");
        kitchenDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");
        pantryDoorButton.setStyle("-fx-font-size: 21.5;-fx-background-color: rgba(255,99,71,0.9);");

        frontDoorButtonClick(frontDoorButton);
        backDoorButtonClick(backDoorButton);
        garageDoorButtonClick(garageDoorButton);
        entryDoorButtonClick(entryDoorButton);
        kitchenDoorButtonClick(kitchenDoorButton);
        pantryDoorButtonClick(pantryDoorButton);

        buttonArea.add(frontDoorButton, 2, 0);
        buttonArea.add(backDoorButton, 2, 1);
        buttonArea.add(garageDoorButton, 2, 2);
        buttonArea.add(entryDoorButton, 2, 3);
        buttonArea.add(kitchenDoorButton, 2, 4);
        buttonArea.add(pantryDoorButton, 2, 5);

        leftLockArea.setRight(buttonArea);
        leftLockArea.setLeft(lockLabelArea);
        lockArea.setTop(lockAllButton);
        lockArea.setCenter(leftLockArea);
        Label doorInformation = new Label("Status: Disarmed\nTroubles: OK\nConnection: OK");
        doorInformation.setStyle("-fx-font-size: 25");
        rightLockArea.setStyle("-fx-border-width: 2px; -fx-background-color: rgba(135,206,235,0.3); -fx-border-color: rgba(0,119,255,0.5);");
        rightLockArea.setRight(doorInformation);

        lockArea.setRight(rightLockArea);
        mainPane.setBottom(lockArea);
        mainPane.getBottom().setStyle("-fx-background-color: rgba(0,119,255,0.2)");

        lockTab.setContent(lockArea);
        cameraTab.setContent(cameraArea);


        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        stage.setTitle("Security");
        stage.setScene(scene);
        stage.show();
    }




    private void lockAllClick(Button lockAllButton) {
        EventHandler<ActionEvent> lockEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ImageView lockView = new ImageView(new Image("pic/Security/lock.png",
                        30, 40, false, true));
                ImageView unlockView = new ImageView(new Image("pic/Security/unlock.png",
                        30, 40, false, true));

                if (lockAllDoorStatus == true) {
                    if (tmp != 60) {
                        resetTmp();
                    }
                    Alert information = new Alert(Alert.AlertType.INFORMATION, " Lock ");
                    information.setHeaderText("Lock Mode");

                    Timeline time = new Timeline(new KeyFrame(Duration.millis(1000), e -> timeCount(information, lockAllButton, lockView)));
                    time.setCycleCount(61);
                    time.play();

                    information.setTitle("Lock");
                    GridPane gridPane = new GridPane();
                    Label infText = new Label("Locked home, Time left for leaving home: ");
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

                } else {
                    lockAllDoorStatus = true;
                    lockAllButton.setGraphic(unlockView);
                    lockAllButton.setText("Press To Lock All");
                    lockAllButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 35;");
                }
            }

        };
        lockAllButton.setOnAction(lockEvent);


    }


    private void frontDoorButtonClick(Button frontDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (frontDoorStatus == true) {
                    frontDoorStatus = false;
                    frontDoorButton.setText("Off");
                    frontDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    frontDoorStatus = true;

                    frontDoorButton.setText("On ");
                    frontDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        frontDoorButton.setOnAction(changeEvent);
    }

    private void backDoorButtonClick(Button backDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (backDoorStatus == true) {
                    backDoorStatus = false;
                    backDoorButton.setText("Off");
                    backDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    backDoorStatus = true;

                    backDoorButton.setText("On ");
                    backDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        backDoorButton.setOnAction(changeEvent);
    }

    private void garageDoorButtonClick(Button garageDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (garageDoorStatus == true) {
                    garageDoorStatus = false;
                    garageDoorButton.setText("Off");
                    garageDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    garageDoorStatus = true;

                    garageDoorButton.setText("On ");
                    garageDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        garageDoorButton.setOnAction(changeEvent);
    }

    private void entryDoorButtonClick(Button entryDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (entryDoorStatus == true) {
                    entryDoorStatus = false;
                    entryDoorButton.setText("Off");
                    entryDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    entryDoorStatus = true;

                    entryDoorButton.setText("On ");
                    entryDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        entryDoorButton.setOnAction(changeEvent);
    }

    private void kitchenDoorButtonClick(Button kitchenDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (kitchenDoorStatus == true) {
                    kitchenDoorStatus = false;
                    kitchenDoorButton.setText("Off");
                    kitchenDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    kitchenDoorStatus = true;

                    kitchenDoorButton.setText("On ");
                    kitchenDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        kitchenDoorButton.setOnAction(changeEvent);
    }

    private void pantryDoorButtonClick(Button pantryDoorButton) {
        EventHandler<ActionEvent> changeEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pantryDoorStatus == true) {
                    pantryDoorStatus = false;
                    pantryDoorButton.setText("Off");
                    pantryDoorButton.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 21.5;");


                } else {
                    pantryDoorStatus = true;
                    pantryDoorButton.setText("On ");
                    pantryDoorButton.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 21.5;");

                }

            }
        };
        pantryDoorButton.setOnAction(changeEvent);
    }


    private ImageView createTabImgView(String iconName) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(iconName).toExternalForm(),
                20, 20, false, true));

        //button.getStyleClass().add("tab-button");
        return imageView;
    }

    /**
     * @param sc
     * @param scene
     * @param center create a new scrollBar
     */
    private void scrollBar(ScrollBar sc, Scene scene, GridPane center) {
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
     * @param mediaPlayer
     * @param center      four media video added to center
     */
    private void mediaPlay(MediaPlayer mediaPlayer, GridPane center) {
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
     * @param information
     * @param lockView    help lock event count time,
     *                    time run out close the information alert
     */
    private void timeCount(Alert information, Button button, ImageView lockView) {
        if (tmp > 0) {
            tmp--;
            timeLeft.setText(tmp + "");
        } else if (tmp == 0) {
            information.close();
            button.setGraphic(lockView);
            if (lockAllDoorStatus == true) {
                lockAllDoorStatus = false;
                button.setText("Press To Unlock All");
                button.setStyle("-fx-background-color: rgba(0,255,127,0.9); -fx-font-size: 35;");


            } else {
                lockAllDoorStatus = true;
                button.setText("Press To Lock All");
                button.setStyle("-fx-background-color: rgba(255,99,71,0.9); -fx-font-size: 35;");

            }
            tmp = -2;
        }

    }

    /**
     * reset tmp
     */
    private void resetTmp() {
        tmp = 60;
    }
}
