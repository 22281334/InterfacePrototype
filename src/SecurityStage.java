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
        Image security = new Image("pic/SecurityIcon.png");
        Image back=new Image("pic/backIcon.png");
        ImageView backView=new ImageView(back);
        ImageView imageView = new ImageView(security);
        backView.setFitWidth(20);
        backView.setFitHeight(20);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        gridPane.add(backView,0,0);

        gridPane.add(imageView,20,0);
        content.setCenter(gridPane);



        stage.setTitle("Security");

        //start the window.
        Scene scene = new Scene(content, sizeX, sizeY);

        stage.setScene(scene);
        stage.show();
    }
}
