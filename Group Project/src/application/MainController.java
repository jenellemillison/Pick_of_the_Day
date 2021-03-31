/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * MainController.java
 * 
 * MainController links actions to the main.fxml
 */

package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
	//arraylists to hold lists read in from save files. Move to another class??
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Show> shows = new ArrayList<Show>();
	ArrayList<String> movies = new ArrayList<String>();
	
	@FXML
	private Button readButton; //button to change scene to the book select scene

	@FXML
	private AnchorPane homeScreenBckg; //AnchorPane object to change scenes

	@FXML
	private Button watchButton; //button to change scene to the watch select scene
	
	
	/*
     * watch() is the method that will switch screens from the
     * main menu to the watch selection scene when the Watch Button is clicked.
     * @param -- button is clicked
     * @throws IOException
     */
	@FXML
	void watch(ActionEvent event) throws IOException {
		homeScreenBckg = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
		Scene scene = new Scene(homeScreenBckg,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage) ((Button)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
	}
	
	/*
     * read() is the method that will switch screens from the
     * main menu to the book selection scene when the Read Button is clicked.
     * @param -- button is clicked
     * @throws IOException
     */
	@FXML
	void read(ActionEvent event) throws IOException {
		homeScreenBckg = FXMLLoader.load(getClass().getResource("Scene3.fxml"));
		Scene scene = new Scene(homeScreenBckg,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage) ((Button)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();
	}

}
