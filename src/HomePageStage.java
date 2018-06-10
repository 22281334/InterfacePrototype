import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class HomePageStage {

    public HomePageStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        mainStage.setResizable(false);

        FlowPane centerLayout = new FlowPane();
        BorderPane topLayout = new BorderPane();
        BorderPane content = new BorderPane();
        Text title = new Text("Smart Home");
        title.setStyle("-fx-font-size: 24");

        centerLayout.setHgap(250);
        centerLayout.setVgap(50);

        Button cleaningButton = new Button("Cleaning",new ImageView(new Image("pic/HomePage/Cleaning.png")));
        Button securityButton = new Button("Security",new ImageView(new Image("pic/Security/SecurityIcon.png")));
        Button phoneButton = new Button("Phone",new ImageView(new Image("pic/HomePage/Phone.png")));
        Button applianceButton = new Button("Appliance Controller",new ImageView(new Image("pic/HomePage/Appliance.png")));
        Button healthButton = new Button("Health Monitor",new ImageView(new Image("pic/HomePage/Health.png")));
        Button helpButton = new Button("Help",new ImageView(new Image("pic/HomePage/Help.png")));

        applianceButton.setStyle("-fx-pref-width: 350");

        topLayout.setCenter(title);
        centerLayout.getChildren().addAll(cleaningButton,securityButton,
                phoneButton,applianceButton,healthButton,helpButton );
        content.setTop(topLayout);
        content.setCenter(centerLayout);
        centerLayout.setPadding(new Insets(50));

        startNewPage(cleaningButton,CleaningStage.class,mainStage);
        startNewPage(securityButton,SecurityStage.class,mainStage);

        Scene mainScene = new Scene(content,sizeX,sizeY);
        mainScene.getStylesheets().add(getClass().getResource("Style/HomePageStyle.css").toExternalForm());
        mainStage.setScene(mainScene);
        mainStage.show();


    }


    private void startNewPage(Button buttonClick,Object object,Stage mainStage){
        EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (object==SecurityStage.class){
                    new SecurityStage(1000,800,400,100);
                    mainStage.close();
                }
                else if (object==CleaningStage.class){
                    new CleaningStage(400,100,1000,800);
                    mainStage.close();
                }
            }
        };
        buttonClick.setOnAction(startButton);
    }

}
