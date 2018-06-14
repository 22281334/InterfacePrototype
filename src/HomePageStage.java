import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * Author: Yanjing Teng, Jiawei Chen, Minting Lu, Chao Ni
 *
 */
public class HomePageStage {

    public HomePageStage(double positionX,double positionY,double sizeX,double sizeY){
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        mainStage.setResizable(false);

        FlowPane centerLayout = new FlowPane();
        BorderPane topLayout = new BorderPane();
        BorderPane content = new BorderPane();
        Text title = new Text("\t\t Smart Home");
        title.setStyle("-fx-font-size: 30");

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Text clock = new Text(dateFormat.format(date));
        clock.setStyle("-fx-font-size: 20;");
        topLayout.setRight(clock);

        centerLayout.setHgap(250);
        centerLayout.setVgap(50);
        ImageView cleaning=new ImageView(new Image("pic/HomePage/Cleaning.png"));
        ImageView phone=new ImageView(new Image("pic/HomePage/Phone.png"));
        ImageView appliance=new ImageView(new Image("pic/HomePage/Appliance.png"));
        cleaning.setFitHeight(125);
        cleaning.setFitWidth(125);
        phone.setFitHeight(100);
        phone.setFitWidth(100);
        appliance.setFitHeight(125);
        appliance.setFitWidth(125);
        Button cleaningButton = new Button("Cleaning",cleaning);
        Button securityButton = new Button("Security",new ImageView(new Image("pic/Security/SecurityIcon.png")));
        Button phoneButton = new Button("Phone",phone);
        Button applianceButton = new Button("Appliance\nController",appliance);
        Button healthButton = new Button("Health Monitor",new ImageView(new Image("pic/HomePage/Health.png")));
        Button settingButton = new Button("Setting",new ImageView(new Image("pic/HomePage/setting.png")));
        startNewPage(settingButton, Setting.class, mainStage);

        topLayout.setCenter(title);
        centerLayout.getChildren().addAll(cleaningButton,securityButton,
                phoneButton,applianceButton,healthButton,settingButton );

        content.setTop(topLayout);
        content.setCenter(centerLayout);
        centerLayout.setPadding(new Insets(50));



        startNewPage(cleaningButton,CleaningStage.class,mainStage);
        startNewPage(securityButton,SecurityStage.class,mainStage);
        startNewPage(settingButton,Setting.class,mainStage);
        startNewPage(applianceButton,ApplianceStage.class,mainStage);
        startNewPage(phoneButton, PhoneStage.class,mainStage);

        Scene mainScene = new Scene(content,sizeX,sizeY);
        Image background=new Image("pic/background.jpg",1000,800,false,true);

        BackgroundImage backgroundImage=new BackgroundImage(background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        content.setBackground(new Background(backgroundImage));
        topLayout.setStyle("-fx-background-color: rgb(245, 245, 245)");
        mainScene.getStylesheets().add(getClass().getResource("Style/HomePageStyle.css").toExternalForm());
        mainStage.setScene(mainScene);
        mainStage.show();

    }


    private void startNewPage(Button buttonClick,Object object,Stage mainStage){
        EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (object==SecurityStage.class){
                    Platform.runLater(()->{
                        new SecurityStage(1000,800,400,100);
                    });
                    mainStage.hide();
                }
                else if (object==CleaningStage.class){
                    Platform.runLater(()->{
                        new CleaningStage(400,100,1000,800);
                    });
                    mainStage.hide();
                }
                else if (object==Setting.class){
                    Platform.runLater(()->{
                        new Setting(400,100,1000,800);
                    });
                    mainStage.hide();
                }
                else if (object==ApplianceStage.class){
                    Platform.runLater(()->{
                        new ApplianceStage(400,100,1000,800);
                    });
                    mainStage.hide();
                }
                else if (object== PhoneStage.class){
                    Platform.runLater(()->{
                        new PhoneStage(1000,800,400,100);
                    });
                    mainStage.hide();
                }
            }
        };
        buttonClick.setOnAction(startButton);
    }

}
