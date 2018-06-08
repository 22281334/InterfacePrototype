import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        Button cleaningButton = new Button("Cleaning",new ImageView(CleaningPic));
        Button securityButton = new Button("Security",new ImageView(new Image("pic/SecurityIcon.png")));
        topLayout.setCenter(title);

        startNewPage(cleaningButton,CleaningStage.class);
        startNewPage(securityButton,SecurityStage.class);

        centerLayout.getChildren().add(cleaningButton);
        centerLayout.getChildren().add(securityButton);
        content.setTop(topLayout);
        content.setCenter(centerLayout);

        Scene mainScene = new Scene(content,sizeX,sizeY);
        mainStage.setScene(mainScene);
        mainStage.show();
    }


    private void startNewPage(Button buttonClick,Object object){
        EventHandler<ActionEvent> cancelButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (object==SecurityStage.class){
                    new SecurityStage(380,500,0,0);
                }
                else if (object==CleaningStage.class){
                    new CleaningStage(400,100,1000,800);
                }
            }
        };
        buttonClick.setOnAction(cancelButton);
    }
}
