import javafx.application.Platform;
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
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Chao Ni
 */
public class ApplianceStage {


    public ApplianceStage(double positionX, double positionY, double sizeX, double sizeY) {
        Stage mainStage = new Stage();
        mainStage.setX(positionX);
        mainStage.setY(positionY);
        mainStage.setResizable(false);

        FlowPane centerLayout = new FlowPane();
        BorderPane topLayout = new BorderPane();
        BorderPane content = new BorderPane();
        Text title = new Text("\t\t Smart Home");
        title.setStyle("-fx-font-size: 30");
        ImageView backView = new ImageView(new Image("pic/ApplianceImg/back.png"));
        backView.setFitWidth(100);
        backView.setFitHeight(100);
        Button back = new Button("", backView);
        back.setStyle("    -fx-pref-height: 80; -fx-pref-width: 120;");
        EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new HomePageStage(400, 100, 1000, 800);
                mainStage.close();
            }
        };
        back.setOnAction(backButtonEvent);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Text clock = new Text(dateFormat.format(date));
        clock.setStyle("-fx-font-size: 20;");
        topLayout.setLeft(back);
        topLayout.setRight(clock);

        centerLayout.setHgap(250);
        centerLayout.setVgap(50);


        ImageView light = new ImageView(new Image("pic/ApplianceImg/light.png"));
        ImageView tv = new ImageView(new Image("pic/ApplianceImg/tv.png"));
        ImageView sleep = new ImageView(new Image("pic/ApplianceImg/moon.png"));
        ImageView air = new ImageView(new Image("pic/ApplianceImg/aircondition.jpeg"));
        ImageView window = new ImageView(new Image("pic/ApplianceImg/window.jpeg"));
//        ImageView setting = new ImageView(new Image("pic/ApplianceImg/setting.jpeg"));

        light.setFitHeight(125);
        light.setFitWidth(125);
        tv.setFitHeight(125);
        tv.setFitWidth(125);
        sleep.setFitHeight(125);
        sleep.setFitWidth(125);
        air.setFitHeight(125);
        air.setFitWidth(125);
        window.setFitHeight(125);
        window.setFitWidth(125);
//        setting.setFitHeight(125);
//        setting.setFitWidth(125);


        Button lightButton = new Button("Light", light);
        Button tvButton = new Button("TV", tv);
        Button sleepButton = new Button("Sleep Mode", sleep);
        Button airconditionButton = new Button("aircondition", new ImageView(new Image("pic/ApplianceImg/aircondition.jpeg")));
        Button windowButton = new Button("Window", window);
        Button helpButton = new Button("Setting", new ImageView(new Image("pic/HomePage/setting.png")));

        topLayout.setCenter(title);
        centerLayout.getChildren().addAll(lightButton, tvButton,
                sleepButton, airconditionButton, windowButton, helpButton);

        content.setTop(topLayout);
        content.setCenter(centerLayout);
        centerLayout.setPadding(new Insets(50));


        startNewPage(helpButton, Setting.class, mainStage);

        Scene mainScene = new Scene(content, sizeX, sizeY);


        mainScene.getStylesheets().add(getClass().getResource("Style/HomePageStyle.css").toExternalForm());
        mainStage.setScene(mainScene);
        mainStage.show();

    }


    private void startNewPage(Button buttonClick, Object object, Stage mainStage) {
        EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (object == Setting.class) {
                    Platform.runLater(() -> {
                        new Setting(400, 100, 1000, 800);
                    });
                    mainStage.close();
                }
            }
        };
        buttonClick.setOnAction(startButton);
    }


}
