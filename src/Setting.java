
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
    public class Setting extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) {
            ObservableList<String> names = FXCollections
                    .observableArrayList();
            ObservableList<String> data = FXCollections.observableArrayList();

            ListView<String> listView = new ListView<String>(data);
            listView.setPrefSize(200, 250);
            listView.setEditable(true);

            names.addAll("English", "Chinese", "Japanese", "French", "Korean");

            data.add("Double Click to Select Language");


            listView.setItems(data);
            listView.setCellFactory(ComboBoxListCell.forListView(names));

            StackPane root = new StackPane();
            root.getChildren().add(listView);
            primaryStage.setScene(new Scene(root, 200, 250));
            primaryStage.show();
            ToggleGroup group = new ToggleGroup();

            ToggleButton tb1 = new ToggleButton("High");
            tb1.setToggleGroup(group);
            tb1.setSelected(true);

            ToggleButton tb2 = new ToggleButton("Medium");
            tb2.setToggleGroup(group);

            ToggleButton tb3 = new ToggleButton("Low");
            tb3.setToggleGroup(group);


        }
    }}


