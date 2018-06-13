import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * Author: Minting Lu
 *
 */
public class Note{
	
	double x;
	double y;
	double newX;
	double newY;
	double distanceX; // distance of X-coordinate between mouse and Stage's top left 
	double distanceY; // distance of Y-coordinate between mouse and Stage's top left
	double pressedX; // X-coordinate of mouse pressed
	double pressedY; // Y-coordinate of mouse pressed
	double originalWidth; // The width of stage before mouse pressed
	double originalHeight; // The height of stage before mouse pressed
	
	
	public Note(double sizeX, double sizeY, 
			double positionX, double positionY) {
		
		// Create a new stage with position and no decorated
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setX(positionX);
		stage.setY(positionY);
		
		// Create text area, content and button panes
		BorderPane content = new BorderPane();
		BorderPane buttonArea = new BorderPane();
		BorderPane resize = new BorderPane();
		//BorderPane resizePoint = new BorderPane();
		TextArea textArea = new TextArea();
		buttonArea.setStyle("-fx-background-color: rgb(248, 247, 182)");
		resize.setStyle("-fx-background-color: rgb(248, 247, 182)");
		
		// Create two buttons and set color
		Button newPostItNote = new Button("+");
		Button deletePostItNote = new Button("x");
		Button resizePoint = new Button("Resize");
		newPostItNote.setStyle("-fx-background-color: rgb(248, 247, 182)");
		deletePostItNote.setStyle("-fx-background-color: rgb(248, 247, 182)");
		// Change fonts on buttons 
		Font buttonFont = Font.font("Arial", FontWeight.BOLD, 20);
	 	newPostItNote.setFont(buttonFont);
	 	newPostItNote.setTextFill(Color.GREY);
	 	deletePostItNote.setFont(buttonFont);
	 	deletePostItNote.setTextFill(Color.GREY);
	 	
		// Attach button and divide Top, Center and Bottom area 
		buttonArea.setLeft(newPostItNote);
		buttonArea.setRight(deletePostItNote);
		resize.setRight(resizePoint);
		content.setTop(buttonArea);	
		content.setBottom(resize);
		content.setCenter(textArea);
		
		
	 	
		// BUTTON newPostItNote create new stage 
	 	newPostItNote.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Get the position of new stage
				x = stage.getX();
				y = stage.getY();
				newX = x + stage.getWidth();
				newY = y;
				// Check we fit within the screen x size
				Rectangle2D screen = Screen.getPrimary().getVisualBounds();
				if(stage.getX() + originalWidth*2 > screen.getWidth()) {
					newX = 0;
					newY = y + stage.getHeight();
				}
				new Note(sizeX,sizeY,newX,newY);
			}
	 	});
		// BUTTON deletePostItNote close the current stage
	 	deletePostItNote.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
	 	});
		
	 	// Create a new scene and assign it size and pane
		Scene scene = new Scene(content, sizeX, sizeY);
		stage.setScene(scene);
		stage.show();  // Show the current stage
		
		// Change the color of textArea
		Region region = (Region) textArea.lookup(".content");
	 	region.setStyle("-fx-background-color: rgb(255, 253, 201)");
	
	 	
	 	// Create and modify new right click menu
 	 	ContextMenu rightClickMenu = new ContextMenu();
 		MenuItem cut = new MenuItem("Cut");
 		MenuItem copy = new MenuItem("Copy");
 		MenuItem paste = new MenuItem("Paste");
 		MenuItem about = new MenuItem("About");
 		MenuItem exit = new MenuItem("Exit");
 		rightClickMenu.getItems().addAll(cut,copy,paste,about,exit);
 		// Filter the old right click event
 	 	textArea.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
 		// Implement the new right click menu for textArea and content panes
 	 	EventHandler<MouseEvent> rightClick = new EventHandler<MouseEvent>() {
 	 		@Override
 			public void handle(MouseEvent e) {
 				if(e.getButton() == MouseButton.SECONDARY) {
 					rightClickMenu.show(content, e.getScreenX(), e.getScreenY());
 				}
 				else {rightClickMenu.hide();}
 			}
 	 	};
 	 	textArea.setOnMouseClicked(rightClick);
 	 	content.setOnMouseClicked(rightClick);
 	 	
 	 	
 	 	// Implement functions for menu items
 	 	paste.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(Clipboard.getSystemClipboard().hasString()) {
					textArea.appendText(
							(String)Clipboard.getSystemClipboard()
							.getContent(DataFormat.PLAIN_TEXT));
				}
				else {}
			}
 	 	}); 	
 	 	exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
 	 	});	
 	 	about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert dialog = new Alert(AlertType.INFORMATION);
				dialog.setTitle("Post-It Note");
				dialog.setHeaderText("Post-It Note");
				GridPane aboutLayout = new GridPane(); 

				// Get original DialogPane and change the content 
				DialogPane oldDialog = dialog.getDialogPane();
				oldDialog.setContent(aboutLayout);
				dialog.setDialogPane(oldDialog);			
				dialog.show();
			}
 	 	});
 	 	

 	 	// Implement drag function for buttonArea
 	 	buttonArea.setOnMousePressed(new EventHandler<MouseEvent>() {
 	 		@Override
			public void handle(MouseEvent e) {
				distanceX = stage.getX() - e.getScreenX();
				distanceY = stage.getY() - e.getScreenY();
			}
 	 	});
 	 	buttonArea.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				stage.setX(distanceX + e.getScreenX());
				stage.setY(distanceY + e.getScreenY());
			}
 	 	});
 	 	
 	 	
 	 	// Implement resize function for 'resizePoint' pane
 	 	originalWidth = stage.getWidth();
		originalHeight = stage.getHeight();
 	 	resizePoint.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {			
				pressedX = e.getScreenX();
				pressedY = e.getScreenY();
			}
 	 	});
 	 	resizePoint.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {		
				double newWidth = originalWidth + e.getScreenX() - pressedX;
				double newHeight = originalHeight + e.getScreenY() - pressedY;	
				// Restrict minimum size
				if(newWidth > originalWidth) {
					stage.setWidth(newWidth);
				}				
				if(newHeight > originalHeight) {
					stage.setHeight(newHeight);
				}				
			}
 	 	});
	}
}
