import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SecurityStage {


    double sizeX;
    double sizeY;
    double positionX;
    double positionY;
    Stage stage = new Stage();

    public SecurityStage(double sizeX,double sizeY,double positionX,double positionY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.positionX=positionX;
        this.positionY=positionY;
        BorderPane content = new BorderPane();

        stage.setX(positionX + 20);
        stage.setY(positionY);

        GridPane gridPane = new GridPane();
        Image image = new Image("pic/SecurityIcon.png");

        ImageView imageView = new ImageView(image);
        gridPane.add(imageView,0,0);
        content.setCenter(gridPane);



        stage.setTitle("Security");

        //start the window.
        Scene scene = new Scene(content, sizeX, sizeY);

        stage.setScene(scene);
        stage.show();
    }
}
