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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class TimePane {
    public TimePane(){

    }

    public GridPane returnPane(BorderPane centerLayout){
        GridPane timeSelector = new GridPane();
        DatePicker calender = new DatePicker();
        Button save = new Button("Save changes");

        save.setPrefWidth(100);
        save.setPrefHeight(50);
        save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.4);");

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

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProgressBar progress = new ProgressBar(0);
                timeSelector.add(progress,1,2);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(progress.progressProperty(),1);
                KeyFrame kf = new KeyFrame(Duration.millis(500),kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Success! Change Saved.",new ButtonType("OK",ButtonBar.ButtonData.YES));
                alert.show();
            }
        });

        timeSelector.add(calender,1,1);
        timeSelector.add(save,1,3);
        timeSelector.setAlignment(Pos.CENTER);
        timeSelector.setHgap(10);
        timeSelector.setVgap(50);

        Text title = new Text("Please Select the Date");
        title.setStyle("-fx-font-size: 25");

        centerLayout.setBottom(timeSelector);
        centerLayout.setCenter(title);

        return timeSelector;
    }
}
