import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
/**
 *
 * Author: Chao Ni NIYCY004
 *
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
		ImageView backView=new ImageView(new Image("pic/ApplianceImg/back.png"));
		backView.setFitWidth(100);
		backView.setFitHeight(100);
		Button back=new Button("",backView);
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

		// icon 
		ImageView light = new ImageView(new Image("pic/ApplianceImg/light.png"));
		ImageView tv=new ImageView(new Image("pic/ApplianceImg/tv.png"));
		ImageView sleep=new ImageView(new Image("pic/ApplianceImg/moon.png"));
		ImageView air= new ImageView(new Image("pic/ApplianceImg/aircondition.jpeg"));
		ImageView window=new ImageView(new Image("pic/ApplianceImg/window.jpeg"));
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
		Button airconditionButton = new Button("air condition", new ImageView(new Image("pic/ApplianceImg/aircondition.jpeg")));
		Button windowButton = new Button("window",window );
		Button helpButton = new Button("Help", new ImageView(new Image("pic/HomePage/setting.png")));

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

		EventHandler<ActionEvent> newAirConditionButton = new EventHandler<ActionEvent>(){
			@Override 
			public void handle(ActionEvent event){

				Stage stage = new Stage();
				stage.setX(positionX);
				stage.setY(positionY);
				BorderPane root = new BorderPane();
				GridPane gridpane = new GridPane();

				gridpane.setPadding(new Insets(6));
				gridpane.setHgap(5);
				gridpane.setVgap(5);
				ColumnConstraints column1 = new ColumnConstraints(100);
				ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
				column2.setHgrow(Priority.ALWAYS);
				gridpane.getColumnConstraints().addAll(column1, column2);

				Scene scene = new Scene(root, 450, 300);

				final Label temperatureLabel = new Label("");
				final Label durationLabel = new Label("");
				final Label textLabel = new Label("");


				final Slider slider = new Slider(0, 35, 16);
				final Slider slider1 = new Slider(0, 10, 0);
				final Slider slidertext = new Slider(0, 10, 0);
				slidertext.setShowTickMarks(true);
				slidertext.setShowTickLabels(true);




				final Label temperaturelValue = new Label(
						Integer.toString((int) slider.getValue()));
				final Label durationValue = new Label(
						Integer.toString((int) slider1.getValue()));
				final Label textValue = new Label(
						Integer.toString((int) slidertext.getValue()));

				slider.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						System.out.println(new_val.intValue());
						temperaturelValue.setText(String.format("%.1f", new_val));
					}
				});

				GridPane.setConstraints(temperaturelValue, 2, 0);
				gridpane.getChildren().add(temperaturelValue);

				slider1.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						System.out.println(new_val.intValue());
						durationLabel.setText(String.format("%.1f", new_val));
					}
				});

				GridPane.setConstraints(durationLabel, 2, 1);
				gridpane.getChildren().add(durationLabel);

				slidertext.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						System.out.println(new_val.intValue());
						textValue.setText(String.format("%.1f", new_val));
					}
				});

				GridPane.setConstraints(textValue, 2, 2);
				gridpane.getChildren().add(textValue);
				Button saveButton = new Button("Save");

				//Temperature slider
				GridPane.setHalignment(slider, HPos.LEFT);
				gridpane.add(slider, 1, 0);

				// duration slider
				GridPane.setHalignment(slider1, HPos.LEFT);
				gridpane.add(slider1, 1, 1);

				GridPane.setHalignment(slidertext, HPos.LEFT);
				gridpane.add(slidertext, 1, 2);
				// Save button
				GridPane.setHalignment(saveButton, HPos.RIGHT);
				saveButton.setPrefSize(60, 30);
				gridpane.add(saveButton, 2, 7);


				final ToggleGroup group = new ToggleGroup();

				RadioButton rb1 = new RadioButton("Cool Mode");
				rb1.setToggleGroup(group);
				rb1.setUserData("cool model turn on");

				RadioButton rb2 = new RadioButton("Heat Mode");
				rb2.setToggleGroup(group);
				rb2.setUserData("heat mode turn on");


				group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle old_toggle, Toggle new_toggle) {
						if (group.getSelectedToggle() != null) {
							System.out.println(group.getSelectedToggle().getUserData().toString());
						}
					}
				});

				HBox hbox = new HBox();
				VBox vbox = new VBox();

				vbox.getChildren().add(rb1);
				vbox.getChildren().add(rb2);

				vbox.setSpacing(15);

				hbox.getChildren().add(vbox);
				hbox.setSpacing(50);
				hbox.setPadding(new Insets(20, 10, 10, 20));

				gridpane.add(vbox, 0, 4);


				Button Temperature = new Button();
				Temperature.setText("Temperature");
				gridpane.add(Temperature, 0, 0);


				Button duration = new Button();
				duration.setText("Duration");
				gridpane.add(duration, 0, 1);


				Button dryMode = new Button();
				dryMode.setText("Dry Mode");
				gridpane.add(dryMode, 0, 2);



				EventHandler<ActionEvent> success = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Save");
						alert.setHeaderText("");
						VBox vbox = new VBox();
						vbox.setLayoutX(20);
						vbox.setLayoutY(20);


						final String content = "Air condition setting has been successfuly!";
						final Text text = new Text(10, 20, "");

						final Animation animation = new Transition() {
							{
								setCycleDuration(Duration.millis(2000));
							}

							protected void interpolate(double frac) {
								final int length = content.length();
								final int n = Math.round(length * (float) frac);
								text.setText(content.substring(0, n));
							}

						};
						animation.play();
						vbox.getChildren().add(text);
						vbox.setSpacing(10);
						gridpane.add(vbox, 1, 9);


					}
				};


				saveButton.setOnAction(success);
				Button backhomeButt = new Button("Home");
				EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new HomePageStage(400, 100, 1000, 800);
						stage.close();
					}
				};
				backhomeButt.setOnAction(backButtonEvent);

				backhomeButt.setPrefSize(60,30);
				gridpane.add(backhomeButt, 0, 7);
				root.setCenter(gridpane);
				stage.setScene(scene);
				stage.show();
			}
		};
		airconditionButton.setOnAction(newAirConditionButton);
		//Nested Sleep Mode
		EventHandler<ActionEvent> sleepMode = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = new Stage();
				stage.setX(positionX);
				stage.setY(positionY);
				BorderPane root = new BorderPane();
				GridPane gridpane = new GridPane();

				gridpane.setPadding(new Insets(6));
				gridpane.setHgap(5);
				gridpane.setVgap(5);
				ColumnConstraints column1 = new ColumnConstraints(100);
				ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
				column2.setHgrow(Priority.ALWAYS);
				gridpane.getColumnConstraints().addAll(column1, column2);

				Scene scene = new Scene(root, 450, 300);


				final Label textLabel = new Label("");

				final Slider slidertext = new Slider(0, 10, 0);
				slidertext.setShowTickMarks(true);
				slidertext.setShowTickLabels(true);

				final Label textValue = new Label(
						Integer.toString((int) slidertext.getValue()));


				slidertext.valueProperty().addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						System.out.println(new_val.intValue());
						textValue.setText(String.format("%.1f", new_val));
					}
				});

				GridPane.setConstraints(textValue, 2, 2);
				gridpane.getChildren().add(textValue);
				Button saveButton = new Button("Save");

				GridPane.setHalignment(slidertext, HPos.LEFT);
				gridpane.add(slidertext, 1, 2);
				// Save button
				GridPane.setHalignment(saveButton, HPos.RIGHT);
				saveButton.setPrefSize(60, 30);
				gridpane.add(saveButton, 2, 7);


				final ToggleGroup group = new ToggleGroup();

				RadioButton rb1 = new RadioButton(" No disturb");
				rb1.setToggleGroup(group);
				rb1.setUserData("Sleep Mode turn on");

				RadioButton rb2 = new RadioButton("Cancel");
				rb2.setToggleGroup(group);
				rb2.setUserData("Cancel");


				group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle old_toggle, Toggle new_toggle) {
						if (group.getSelectedToggle() != null) {
							System.out.println(group.getSelectedToggle().getUserData().toString());
						}
					}
				});

				HBox hbox = new HBox();
				VBox vbox = new VBox();

				vbox.getChildren().add(rb1);
				vbox.getChildren().add(rb2);

				vbox.setSpacing(30);

				hbox.getChildren().add(vbox);
				hbox.setSpacing(50);
				hbox.setPadding(new Insets(20, 10, 10, 20));

				gridpane.add(vbox, 0, 4);

				Button dryMode = new Button();
				dryMode.setText("Duration");
				gridpane.add(dryMode, 0, 2);

				EventHandler<ActionEvent> success = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						
//						BackgroundFill backGround = new BackgroundFill(null, null, null)
//
//						    Circle rect = new Circle(0,0,10);
//						    
//						    FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
//						    ft.setFromValue(0.0);
//						    ft.setToValue(0.5);
//						    ft.play();
//
//						
//						dryMode.setGraphic(rect);
						
						
						
						GridPane AlertLayout2 = new GridPane();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
		     			alert.setHeaderText("Sleep Mode has been set!");
		     			alert.setTitle("Sleep Mode");
		     			Image image = new Image("pic/ApplianceImg/night.gif");
		     			ImageView imageView = new ImageView(image);
		     			AlertLayout2.add(imageView, 0, 0);
		     			alert.getDialogPane().setContent(AlertLayout2);
		     			alert.show();
		     			
					}	
				};
				saveButton.setOnAction(success);
	
				Button backhomeButt = new Button("Home");
				EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new HomePageStage(400, 100, 1000, 800);
						stage.close();
					}
				};
				backhomeButt.setOnAction(backButtonEvent);

				backhomeButt.setPrefSize(60,30);
				gridpane.add(backhomeButt, 0, 7);
				root.setCenter(gridpane);
				stage.setScene(scene);
				stage.show();
			}
		};
		sleepButton.setOnAction(sleepMode);
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

