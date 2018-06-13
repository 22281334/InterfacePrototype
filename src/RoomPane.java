import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * Author:
 *
 */
public class RoomPane {

    public RoomPane(){

    }

    public GridPane returnPane(BorderPane centerLayout){
        GridPane roomSelector = new GridPane();
        double subBtnWidth = 100;
        double subBtnHeight = 100;

        ToggleButton living = new ToggleButton("Living Room");
        ToggleButton kitchen = new ToggleButton("kitchen");
        ToggleButton bedRoom = new ToggleButton("Bed Room");
        ToggleButton toilet = new ToggleButton("Toilet");
        Button save = new Button("Save changes");
        Text title = new Text("Please Select Room");
        title.setStyle("-fx-font-size: 18");

        // Set the size and font of buttons
        living.setPrefHeight(subBtnHeight);
        kitchen.setPrefWidth(subBtnWidth);
        kitchen.setPrefHeight(subBtnHeight);
        bedRoom.setPrefWidth(subBtnWidth);
        bedRoom.setPrefHeight(subBtnHeight);
        toilet.setPrefWidth(subBtnWidth);
        toilet.setPrefHeight(subBtnHeight);
        save.setPrefWidth(subBtnWidth);
        save.setPrefHeight(50);
        living.setStyle("-fx-font-size: 13;");
        kitchen.setStyle("-fx-font-size: 13;");
        bedRoom.setStyle("-fx-font-size: 13;");
        toilet.setStyle("-fx-font-size: 13;");
        save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.4); ");

        // Change "save" button color when click
        save.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.8);" +
                        " -fx-pref-width: 100; -fx-pref-height: 50;");
            }
        });
        save.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save.setStyle("-fx-font-size: 12; -fx-background-color: rgba(0,255,0,0.4);" );
            }
        });

        // Add progress bar and animation after click "save" button
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProgressBar progress = new ProgressBar(0);  // original progress : 0
                roomSelector.add(progress,1,2);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(progress.progressProperty(),1);  // Final progress value : 1
                KeyFrame kf = new KeyFrame(Duration.millis(500),kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Success! Change Saved.",new ButtonType("OK",ButtonBar.ButtonData.YES));
                alert.show();
            }
        });

        roomSelector.add(living,0,0);
        roomSelector.add(kitchen,2,0);
        roomSelector.add(bedRoom,0,1);
        roomSelector.add(toilet,2,1);
        roomSelector.add(save,1,3);

        roomSelector.setPadding(new Insets(30));
        roomSelector.setAlignment(Pos.CENTER);
        roomSelector.setHgap(10);
        roomSelector.setVgap(30);
        centerLayout.setStyle("-fx-background-color: rgba(192,192,192,0.5)");
        roomSelector.setStyle("-fx-background-color: rgba(192,192,192,0.8)");

        centerLayout.setBottom(roomSelector);
        centerLayout.setCenter(title);

        return roomSelector;
    }
}
