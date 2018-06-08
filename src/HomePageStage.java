import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HomePageStage {

    public HomePageStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);

        FlowPane centerLayout = new FlowPane();
        BorderPane topLayout = new BorderPane();
        BorderPane content = new BorderPane();
        Text title = new Text("Smart Home");
        title.setStyle("-fx-font-size: 50");

        centerLayout.setHgap(100);

        Image CleaningPic = new Image("pic/Cleaning.png");

        Button cleaning = new Button("Cleaning",new ImageView(CleaningPic));
        Button security = new Button("Security");

        topLayout.setCenter(title);
        centerLayout.getChildren().add(cleaning);
        centerLayout.getChildren().add(security);
        content.setTop(topLayout);
        content.setCenter(centerLayout);

        Scene mainScene = new Scene(content,sizeX,sizeY);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
