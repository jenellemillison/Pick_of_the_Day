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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProgressController implements Initializable{

	//Buttons, Textfields, and Panes shown on the ProgressScene that the user can interact with to make changes
	@FXML
	private Button watchReadButton;

    @FXML
    private Button inProgressButton;

    @FXML
    private AnchorPane displayChoiceBckg;   
	   
    @FXML
    private TextArea progressResult;

    @FXML
    private Button doneButton;

    @FXML
    private TextField stillReading;

    @FXML
    private Label scene_title;
	    
    //public static variables that can be accessed by the ShowController class to pass on the choice of book
    public static Book chosenBook;
	    
    /*
	 * initialize uses the Initialization interface in order to initialize display screen and
	 * text field once the Progress Control scene is launched.
	 * Params: URL location, ResourceBundle resources Returns: void
	 */
	        	    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	updateProgress();
		stillReading.setText("");	
	}
	    
	/*
	 * progressAction adds the validated progress on book info entered by the user to books.txt and the
	 * ArrayList of books.
	 * Params: ActionEvent event (click the progressAction) Returns: void
	 */    
	@FXML
	void progressAction(ActionEvent event) throws IOException {  
	  	Alert a = new Alert(AlertType.NONE);
	   	try { 
	   		int progress = Integer.parseInt(stillReading.getText().toString());
	   		chosenBook.chapter= progress;
	   	}
	   	catch (NumberFormatException e) {
	   		a.setAlertType(AlertType.ERROR);
	   		a.setContentText("Please enter a valid chapter number.");
	   		a.show();
	   	}
	   	BookController.fb.FileWrite(BookController.allBooks); 
	   	stillReading.clear();
	   	updateProgress();
	}
	    
	/*
     * returnHome launches the home scene from the progress scene
     * Params: ActionEvent event (javafx button click) Returns: void
     */
    @FXML
	void returnHome(ActionEvent event) throws IOException {
    	displayChoiceBckg  = FXMLLoader.load(getClass().getResource("/application/MainScene.fxml"));// pane you are GOING TO
	    Scene scene = new Scene(displayChoiceBckg );// pane you are GOING TO show
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
	void deleteContent(ActionEvent event) throws FileNotFoundException, IOException {	
		progressResult.setText("Finished " + chosenBook.title + " by " + chosenBook.author);
	  	
		//make sure the ArrayList of books is updated
		BookController.allBooks.clear();
	  	BookController.fb.FileRead(); 
		int index = 0;
		
		//search the arraylist for a match to the title of the book user finished
    	while(index < BookController.allBooks.size()) {
    		//once found a match, remove from arraylist and file
    		if((BookController.allBooks.get(index).title).equals(chosenBook.title) && (BookController.allBooks.get(index).author).equals(chosenBook.author)) {
    			 BookController.allBooks.remove(index);
   			     BookController.fb.FileWrite(BookController.allBooks);    			
    		}
    		index++;
    	}    	
    }
		    	
	/*
	 * updateProgress displays the title and chapter on the text area with the most recent values.
	 * Params: none Returns: void
	 */
	@FXML
	public void updateProgress() {
		progressResult.setText(chosenBook.title + " "+ chosenBook.chapter);	   	    
	}
}

