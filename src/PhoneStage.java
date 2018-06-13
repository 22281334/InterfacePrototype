import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PhoneStage {
    BorderPane content;
    BorderPane buttonArea;
    Button letterButton;
    Button memberButton;
    Button questionButton;
    Button warningButton;
    Button bigButton;
    Button homeButton;
    Button keyboardButton;
    TextArea textArea;
    Font buttonFont;
    ContextMenu rightClickMenu;
    MenuItem cut;
    MenuItem copy;
    MenuItem paste;
    MenuItem about;
    MenuItem exit;

    public PhoneStage(double sizeX, double sizeY, double positionX, double positionY) {
        Stage stage = new Stage();
        stage.setX(positionX);
        stage.setY(positionY);
        content = new BorderPane();
        Scene scene = new Scene(content, sizeX, sizeY);
        buttonArea = new BorderPane();
        stage.setScene(scene);


        Image background=new Image("pic/phone/sky.jpg",1000,800,false,true);

        BackgroundImage backgroundImage=new BackgroundImage(background,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        content.setBackground(new Background(backgroundImage));



        buttonArea.setStyle("-fx-background-color: rgb(139, 145, 155)");
        content.setTop(buttonArea);

//		
        Image home = new Image("pic/phone/home.jpg", 75, 75, false, false);
        ImageView homeView = new ImageView(home);
        homeButton = new Button();
        homeButton.setGraphic(homeView);
//
        Image keyboard = new Image("pic/phone/keyboard.jpg", 1000, 200, false, false);
        ImageView keyboardView = new ImageView(keyboard);
        keyboardButton = new Button();
        keyboardButton.setGraphic(keyboardView);
//		
        Image big = new Image("pic/phone/big.jpg", 200, 200, false, false);
        ImageView bigView = new ImageView(big);
        bigButton = new Button();
        bigButton.setGraphic(bigView);
//		
        Image letter = new Image("pic/phone/letter.jpg", 100, 100, false, false);
        ImageView letterView = new ImageView(letter);
        letterButton = new Button();
        letterButton.setGraphic(letterView);
//		
        Image member = new Image("pic/phone/member.jpg", 100, 100, false, false);
        ImageView memberView = new ImageView(member);
        memberButton = new Button();
        memberButton.setGraphic(memberView);
//		
        Image question = new Image("pic/phone/question.jpg", 75, 75, false, false);
        ImageView questionView = new ImageView(question);
        questionButton = new Button();
        questionButton.setGraphic(questionView);

//	 	// Create a new scene and assign it size and pane
//		Scene phoneCallscene = new Scene(content, sizeX, sizeY);
//		stage.setScene(phoneCallscene);
//		stage.show();  // Show the current stage

        buttonArea.setLeft(homeButton);
        buttonFont = Font.font("Arial", FontWeight.BOLD, 20);
        textArea = new TextArea();
        buttonArea.setRight(questionButton);
        content.setRight(memberButton);
        content.setLeft(letterButton);
        content.setCenter(bigButton);
        content.setBottom(keyboardButton);

        EventHandler<ActionEvent> newButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                new HomePageStage(400, 100, 1000, 800);
                stage.close();
            }
        };
        homeButton.setOnAction(newButton);

        EventHandler<ActionEvent> newNote = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Note newNote = new Note(600, 600, 0, 0);
            }
        };
        keyboardButton.setOnAction(newNote);
        letterButton.setOnAction(newNote);
        memberButton.setOnAction(newNote);
        questionButton.setOnAction(newNote);

        EventHandler<ActionEvent> newCall = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Timer
                time();
                //Timer.main(null);
            }
        };
        bigButton.setOnAction(newCall);

        rightClickMenu = new ContextMenu();
        cut = new MenuItem("Cut");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        about = new MenuItem("About");
        exit = new MenuItem("Exit");
        rightClickMenu.getItems().add(cut);
        rightClickMenu.getItems().add(copy);
        rightClickMenu.getItems().add(paste);
        rightClickMenu.getItems().add(about);
        rightClickMenu.getItems().add(exit);
        textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        EventHandler<MouseEvent> rightClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    rightClickMenu.show(content, e.getScreenX(), e.getScreenY());
                    ;
                }
            }
        };
        textArea.setOnMouseClicked(rightClick);
        stage.show();
    }

    private void time() {
        Stage primaryStage = new Stage();
        Label label = new Label();
        DoubleProperty time = new SimpleDoubleProperty();
        label.textProperty().bind(time.asString("%.3f seconds"));

        BooleanProperty running = new SimpleBooleanProperty();

        AnimationTimer timer = new AnimationTimer() {

            private long startTime;

            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                running.set(true);
                super.start();
            }

            @Override
            public void stop() {
                running.set(false);
                super.stop();
            }

            @Override
            public void handle(long timestamp) {
                long now = System.currentTimeMillis();
                time.set((now - startTime) / 1000.0);
            }
        };


        Image answer = new Image("pic/phone/answer.png", 75, 75, false, false);
        ImageView answerView = new ImageView(answer);
        Button startStop = new Button();
        startStop.setGraphic(answerView);

        startStop.textProperty().bind(
                Bindings.when(running)
                        .then("Finish")
                        .otherwise("Answer"));

        startStop.setOnAction(e -> {
            if (running.get()) {
                timer.stop();
                primaryStage.close();
            } else {
                timer.start();
            }
        });

        VBox root = new VBox(10, label, startStop);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
