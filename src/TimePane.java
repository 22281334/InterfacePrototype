import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDate;

/**
 *
 * Author: Yanjing Teng
 *
 */
public class TimePane {
    public TimePane(){

    }

    public GridPane returnPane(BorderPane centerLayout){
        GridPane timeSelector = new GridPane();
        HBox start = new HBox();
        HBox end = new HBox();
        DatePicker calender = new DatePicker();
        Button save = new Button("Save changes");
        TextField startHour = new TextField();
        TextField startMin = new TextField();
        TextField endHour = new TextField();
        TextField endMin = new TextField();
        ToggleButton startAM = new ToggleButton("AM");
        ToggleButton startPM = new ToggleButton("PM");
        ToggleButton endAM = new ToggleButton("AM");
        ToggleButton endPM = new ToggleButton("PM");
        Text from = new Text("From:");
        Text to = new Text("To:");
        Text title = new Text("Please Select the Date");
        title.setStyle("-fx-font-size: 18");

        calender.setPrefSize(150,30);
        calender.setValue(LocalDate.now());
        save.setPrefWidth(100);
        save.setPrefHeight(50);
        save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.4);");

        // Change the size of text field
        startHour.setPrefColumnCount(2);
        startHour.setText("12");
        startMin.setPrefColumnCount(2);
        startMin.setText("00");
        endHour.setPrefColumnCount(2);
        endHour.setText("12");
        endMin.setPrefColumnCount(2);
        endMin.setText("00");

        // Set 2 toggle groups
        ToggleGroup startTime = new ToggleGroup();
        ToggleGroup endTime = new ToggleGroup();
        startAM.setToggleGroup(startTime);
        startPM.setToggleGroup(startTime);
        endAM.setToggleGroup(endTime);
        endPM.setToggleGroup(endTime);

        // Change the color of "save" button
        save.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.8);");
            }
        });
        save.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.4);");
            }
        });

        // Add progress bar and animation after click "save" button
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProgressBar progress = new ProgressBar(0);    // original progress : 0
                timeSelector.add(progress,1,4);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(progress.progressProperty(),1);  // Final progress value : 1
                KeyFrame kf = new KeyFrame(Duration.millis(500),kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Success! Change Saved.",new ButtonType("OK",ButtonBar.ButtonData.YES));
                alert.show();
            }
        });

        start.getChildren().addAll(new Label("Hours:"),startHour,new Label("Minutes:"),startMin,startAM,startPM);
        end.getChildren().addAll(new Label("Hours:"),endHour,new Label("Minutes:"),endMin,endAM,endPM);
        timeSelector.add(calender,1,1);
        timeSelector.add(from,0,2);
        timeSelector.add(start,1,2);
        timeSelector.add(to,0,3);
        timeSelector.add(end,1,3);
        timeSelector.add(save,1,5);
        timeSelector.setAlignment(Pos.CENTER);
        timeSelector.setVgap(50);
        timeSelector.setHgap(20);

        centerLayout.setBottom(timeSelector);
        centerLayout.setCenter(title);

        return timeSelector;
    }
}
