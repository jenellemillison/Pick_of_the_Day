/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * BookController.java
 * 
 * ProgressControllerShow implements changes to the ProgressSceneShow based on user input and
 * logic found in the model class.
 */

package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProgressControllerShow implements Initializable{

	//Buttons, Textfields, and Panes shown on the ProgressSceneShow that the user can interact with to make changes
    @FXML
    private Button watchReadButton;

    @FXML
    private Button inProgressButton;

    @FXML
    private AnchorPane displayChoiceBckg;

    @FXML
    private TextArea progressResult;

    @FXML
    private TextField stillWatchingShow;

    @FXML
    private Button doneButton;

    @FXML
    private Label scene_title;
	
    //public static variables that can be accessed by the ShowController class to pass on the choice of show or movie
	public static Show chosenShow;
	public static Movie chosenMovie;
	public static boolean isShow;
	public static boolean isMovie;

	    
	/*
	 * initialize uses the Initialization interface in order to initialize display screen and
	 * text field once the Progress Control scene is launched.
	 * Params: URL location, ResourceBundle resources Returns: void
	 */	        	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateProgress();
		stillWatchingShow.setText("");	
	}
	    
	/*
	 * progressAction adds the validated progress on book info entered by the user to books.txt and the
	 * ArrayList of books.
	 * Params: ActionEvent event (click the progressAction) Returns: void
	 */    
	@FXML
	void progressAction(ActionEvent event) throws IOException { 
	   	if (isShow == true) {
	  		int progress = Integer.parseInt(stillWatchingShow.getText().toString());	 	    	  	
	   		chosenShow.episode= progress;  
	   		ShowController.sh.FileWriteShow(ShowController.showTitle); 
	   		updateProgress();
	   		stillWatchingShow.clear();
	   	}
	    	
	   	else if (isMovie == true ) {
	   		 progressResult.setText(chosenMovie.title);	
	    	 stillWatchingShow.clear();
    	}
    }
	    
    /*
     * returnHome launches the home scene from the progress scene
     * Params: ActionEvent event (javafx button click) Returns: void
     */
    @FXML
	void returnHome(ActionEvent event) throws IOException {
    	displayChoiceBckg = FXMLLoader.load(getClass().getResource("MainScene.fxml"));// pane you are GOING TO
        Scene scene = new Scene(displayChoiceBckg);// pane you are GOING TO show
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
	    
    /*
     * deleteContent removes and updates the list of books in the menu with the most recent options from the list of all books
     * Params: none Returns: void
     */    	   
	 	    
	@FXML
	public void deleteContent(ActionEvent event) throws FileNotFoundException, IOException {
		if (isShow == true) {
			progressResult.setText("Finished " + chosenShow.title);
			  
			//make sure the ArrayList of shows is updated
			ShowController.showTitle.clear();
			ShowController.sh.FileReadShow();
			int index = 0;

			//search the arraylist for a match to the title and episode of the show user finished
			while(index < ShowController.showTitle.size()) {
				//if found, remove show from arraylist and file
				if((ShowController.showTitle.get(index).title).equals(chosenShow.title) && ShowController.showTitle.get(index).episode == chosenShow.episode) {
					ShowController.showTitle.remove(index);
					ShowController.sh.FileWriteShow(ShowController.showTitle);
					break;
				}
  				index++;
			}	  	
		}
		
		else if (isMovie == true ){	
			progressResult.setText("Finished " + chosenMovie.title);
			
			//make sure the ArrayList of movies is updated
			ShowController.movies.clear();
		  	ShowController.sh.FileReadMovie();
			int index2 =0;
				
			//search the arraylist for a match to the title of the movie user finished
			while(index2 < ShowController.movies.size()) {
			//if found, remove movie from arraylist and file
				if ((ShowController.movies.get(index2).title).equals(chosenMovie.title)){
					ShowController.movies.remove(index2);
					ShowController.sh.FileWriteMovie(ShowController.movies);
					break;
				}
				index2 ++;
			}
		}  
	}

			  
	/*
	 * updateProgress displays the title and chapter on the text area with the most recent values.
	 * Params: none Returns: void
	 */
	@FXML
	public void updateProgress() {
		if(isShow) {
			progressResult.setText("Watching Episode " + chosenShow.episode + " of " + chosenShow.title);
		}
		else if (isMovie) {
			progressResult.setText("Watching: " + chosenMovie.title); 
		}
	}
}