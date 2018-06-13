
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Author: Jiawei Chen(CHEJY176)
 *
 */
public class Setting {


    public Setting(double positionX, double positionY, double sizeX, double sizeY) {

        Stage stage = new Stage();
        stage.setX(positionX);
        stage.setY(positionY);
        BorderPane root = new BorderPane();
        BorderPane top = new BorderPane();
        GridPane gridpane = new GridPane();

        Text title = new Text("Setting");
        title.setStyle("-fx-font-size: 20");

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Text clock = new Text(dateFormat.format(date));
        clock.setStyle("-fx-font-size: 20;");
        top.setCenter(title);
        top.setRight(clock);
        top.setStyle("-fx-background-color: rgba(0,119,255,0.5)");
        root.setTop(top);

        gridpane.setPadding(new Insets(6));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2);

        Scene scene = new Scene(root, 1000, 800);

        final Label sound = new Label("Sound:");
        final Label light = new Label("Light:");
        final Label text = new Label("Text");
        final Label wifi = new Label("Wi-Fi");
        final Label soundcc = new Label("Voice Control");
        final Label language = new Label("Change Language");
        final Slider slider = new Slider(0, 100, 50);
        final Slider slider1 = new Slider(0, 100, 50);
        final Slider slidertext = new Slider(0, 6, 3);
        slidertext.setShowTickMarks(true);
        slidertext.setShowTickLabels(true);
        slidertext.setMajorTickUnit(0.25f);
        slidertext.setBlockIncrement(0.1f);

        ObservableList<String> names = FXCollections
                .observableArrayList();
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(names);
        listView.setPrefSize(200, 35);
        listView.setEditable(true);
        names.addAll("English", "Chinese", "Japanese", "French", "Korean");
        data.add("Select Language");
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        gridpane.add(listView, 2, 5);


        final Label soundValue = new Label(
                Integer.toString((int) slider.getValue()));
        final Label lightValue = new Label(
                Integer.toString((int) slider1.getValue()));
        final Label textValue = new Label(
                Integer.toString((int) slidertext.getValue()));

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                soundValue.setText(String.format("%.1f", new_val));
            }
        });

        GridPane.setConstraints(soundValue, 2, 0);
        gridpane.getChildren().add(soundValue);

        slider1.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                lightValue.setText(String.format("%.1f", new_val));
            }
        });

        GridPane.setConstraints(lightValue, 2, 1);
        gridpane.getChildren().add(lightValue);

        slidertext.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                textValue.setText(String.format("%.1f", new_val));
            }
        });

        GridPane.setConstraints(textValue, 2, 2);
        gridpane.getChildren().add(textValue);
        Button saveButton = new Button("Save");


        // sound label
        GridPane.setHalignment(sound, HPos.RIGHT);
        gridpane.add(sound, 0, 0);

        // Light label
        GridPane.setHalignment(light, HPos.RIGHT);
        gridpane.add(light, 0, 1);

        // sound slider
        GridPane.setHalignment(slider, HPos.LEFT);
        gridpane.add(slider, 1, 0);

        // light slider
        GridPane.setHalignment(slider1, HPos.LEFT);
        gridpane.add(slider1, 1, 1);

        // text label
        GridPane.setHalignment(text, HPos.RIGHT);
        gridpane.add(text, 0, 2);
        // text slider
        GridPane.setHalignment(slidertext, HPos.LEFT);
        gridpane.add(slidertext, 1, 2);
        // Save button
        GridPane.setHalignment(saveButton, HPos.RIGHT);
        saveButton.setPrefSize(100, 100);
        gridpane.add(saveButton, 2, 7);
        saveButton.setStyle("-fx-background-color: rgba(58, 168, 36, 1)");
        // wifi 
        gridpane.add(wifi, 1, 3);
        // Wi-fi open/close
        Button wifiButton = new Button("Off");
        wifiButton.setStyle("-fx-font-size: 10; " +
                "-fx-background-color: rgba(255,0,0,0.5)");
        wifiButton.setText("Off");

        wifiButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String bgColor = wifiButton.getBackground().getFills().get(0).toString();
                bgColor = bgColor.substring(bgColor.length() - 8);
                if (bgColor.equals("@5e759d9")) {
                    wifiButton.setStyle("-fx-font-size: 10; " +
                            "-fx-background-color: rgba(255,0,0,0.5)");
                    wifiButton.setText("Off");
                } else {
                    wifiButton.setStyle("-fx-font-size: 10; " +
                            "-fx-background-color: rgba(0,255,0,0.5)");
                    wifiButton.setText("On");
                }
            }
        });

        gridpane.add(wifiButton, 2, 3);
        gridpane.add(soundcc, 1, 4);
        gridpane.add(language, 1, 5);
        // soundcc open/close
        Button soundccButton = new Button("Off");
        soundccButton.setStyle("-fx-font-size: 10; " +
                "-fx-background-color: rgba(255,0,0,0.5)");
        soundccButton.setText("Off");

        soundccButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String bgColor = soundccButton.getBackground().getFills().get(0).toString();
                bgColor = bgColor.substring(bgColor.length() - 8);
                if (bgColor.equals("@5e759d9")) {
                    soundccButton.setStyle("-fx-font-size: 10; " +
                            "-fx-background-color: rgba(255,0,0,0.5)");
                    soundccButton.setText("Off");
                } else {
                    soundccButton.setStyle("-fx-font-size: 10; " +
                            "-fx-background-color: rgba(0,255,0,0.5)");
                    soundccButton.setText("On");
                }
            }
        });
        gridpane.add(soundccButton, 2, 4);

        Image soundIcon = new Image("pic/settingImg/sound.jpg", 25, 25, false, false);
        ImageView soundView = new ImageView(soundIcon);
        Button soundButton = new Button();
        soundButton.setGraphic(soundView);
        gridpane.add(soundButton, 0, 0);

        Image lightIcon = new Image("pic/settingImg/light.png", 25, 25, false, false);
        ImageView lightView = new ImageView(lightIcon);
        Button lightButton = new Button();
        lightButton.setGraphic(lightView);
        gridpane.add(lightButton, 0, 1);

        Image textIcon = new Image("pic/settingImg/text.png", 25, 25, false, false);
        ImageView textView = new ImageView(textIcon);
        Button textButton = new Button();
        textButton.setGraphic(textView);
        gridpane.add(textButton, 0, 2);

        Image wifiIcon = new Image("pic/settingImg/wifi.png", 25, 25, false, false);
        ImageView wifiView = new ImageView(wifiIcon);
        Button wifiButt = new Button();
        wifiButt.setGraphic(wifiView);
        gridpane.add(wifiButt, 0, 3);

        Image soundccIcon = new Image("pic/settingImg/soundcc.png", 25, 25, false, false);
        ImageView soundccView = new ImageView(soundccIcon);
        Button soundccButt = new Button();
        soundccButt.setGraphic(soundccView);
        gridpane.add(soundccButt, 0, 4);

        EventHandler<ActionEvent> success = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Save setting");
                alert.setHeaderText("Do you want to save the changes?");
                GridPane gridPane = new GridPane();
                ProgressIndicator progressIndicator = new ProgressIndicator();
                gridPane.add(progressIndicator, 2, 6);
                Image image = new Image("pic/settingImg/important.png", 50, 50, false, false);
                ImageView imageView = new ImageView(image);
                gridPane.add(imageView, 0, 0);
                alert.getDialogPane().setContent(gridPane);
                gridPane.add(new Label("If not stored, your changed content will be lost"), 1, 0);
                alert.getDialogPane().setContent(gridPane);
                alert.showAndWait();


            }
        };


        saveButton.setOnAction(success);
        Button backhomeButt = new Button("Home");

        EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomePageStage(400, 100, 1000, 800);
                stage.close();
            }
        };
        backhomeButt.setOnAction(backButtonEvent);

        backhomeButt.setPrefSize(100, 100);
        backhomeButt.setStyle("-fx-background-color: rgba(36, 164, 168, 1)");
        gridpane.add(backhomeButt, 0, 7);
        root.setCenter(gridpane);
        stage.setScene(scene);
        stage.show();

    }

}