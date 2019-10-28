/*
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JFileChooser;

public class GUI extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FlowPane pane = new FlowPane();
		
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5);
		pane.setVgap(5);
		
		Button openBt = new Button("Choose File");		
		pane.getChildren().add(openBt);
		
		final TextField characterCalc = new TextField();
		characterCalc.setPromptText("Enter name of Character from selected play");
		characterCalc.setPrefColumnCount(25); //sets width of text field
		characterCalc.getText();
		
		Button enterBt1 = new Button ("Enter");
		
		final TextField fragment = new TextField();
		fragment.setPromptText("Enter a fragment from the play");
		fragment.setPrefColumnCount(25); //sets width of text field
		fragment.getText();
		
		Button enterBt2 = new Button ("Enter");
		
		Scene scene = new Scene(pane, 500, 300);
		primaryStage.setTitle("MyJavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		final FileChooser fileChooser = new FileChooser();
		
		openBt.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file!= null) {
					
					Text text1 = new Text(50,10, "Chosen file: " + file.getName());
					pane.getChildren().add(text1);
					pane.getChildren().add(characterCalc);
					pane.getChildren().add(enterBt1);
					pane.getChildren().add(fragment);
					pane.getChildren().add(enterBt2);
					
				}
			}

		});
		
		//determines chain of action once name of character is entered
		//to find number of times that character speaks
		enterBt1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(final ActionEvent f) {
				if(characterCalc.getText() != null && !characterCalc.getText().isEmpty()) {
					
				}
			}
		});
		
		//determines chain of action once a fragment from the play is entered 
		enterBt2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(final ActionEvent g) {
				if(fragment.getText() != null && !fragment.getText().isEmpty()) {
					
				}
			}
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
*/