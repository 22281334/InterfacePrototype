import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpStage {


    double sizeX;
    double sizeY;
    double positionX;
    double positionY;



    public HelpStage(double sizeX, double sizeY, double positionX, double positionY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.positionX = positionX;
        this.positionY = positionY;
        Stage mainStage = new Stage();
        BorderPane mainPane = new BorderPane();
        Scene mainScene = new Scene(mainPane,sizeX,sizeY);

        mainStage.setX(positionX);
        mainStage.setY(positionY);
        BorderPane topPane=new BorderPane();
        Text title = new Text("Help Center");
        title.setStyle("-fx-font-size: 30");
        topPane.setCenter(title);
        Image homeImg = new Image("pic/Security/backIcon.png");
        ImageView homeView=new ImageView(homeImg);
        Button homeButton=new Button("",homeView);
        homePageClick(mainStage,homeButton);
        topPane.setLeft(homeButton);
        mainPane.setTop(topPane);






        mainStage.setScene(mainScene);
        mainStage.show();
    }
    private void homePageClick(Stage stage,Button homeButton){
        EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomePageStage(400, 100, 1000, 800);
                stage.close();
            }
        };
        homeButton.setOnAction(backButtonEvent);
    }
}
