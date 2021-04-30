/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * BookController.java
 * 
 * BookController implements changes to the BookScene based on user input and
 * logic found in the model class.
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import application.FileHandlerBook;

public class BookController implements Initializable{
	
	//Buttons, Textfields, and Panes shown on the ShowScene that the user can interact with to make changes
    @FXML
    private Button randomReadButton;

    @FXML
    private MenuButton listAuthorsButton;

    @FXML
    private Button addBookToListButton;

    @FXML
    private AnchorPane bookSelectorBckg;
    
    @FXML
    private AnchorPane mainBckg;

    @FXML
    private Button readNowButton;
    
    @FXML
    private TextField bookTitleTxt;
    
    @FXML
    private TextField bookAuthorTxt;
    
    //static varaibles that can be accessed from the ProgressControllerShow class to update show and movie progress
    public static FileHandlerBook fb = new FileHandlerBook();
    public static ArrayList<Book> allBooks = new ArrayList<Book>();
    
    //variables for this class only, the menu and the chosen book.
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private Book chosen = null;

	 /*
	  * initialize uses the Initialization interface in order to initialize variables and
	  * other starting values once the Inventory scene is launched.
	  * Params: URL location, ResourceBundle resources Returns: void
	  */
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		//clear pre-existing data
    	allBooks.clear();
		listAuthorsButton.getItems().clear();
		bookTitleTxt.setText("");
		bookAuthorTxt.setText("");
		
		//read in file data on books
		try {
			fb.FileRead();
		} catch (Exception e) {
			System.out.println("Error with reading file of book information.");
		}
		
		//use read in data to update menus
		updateBookMenu();
	}
    	
	/*
	  * addToList adds the validated title and author info entered by the user to books.txt and the
	  * ArrayList of books.
	  * Params: ActionEvent event (click the addBookToListButton) Returns: void
	  */
    public void addToList(ActionEvent event) throws IOException {
    	Alert a = new Alert(AlertType.NONE);
    	//if nothing entered, error
    	if(bookTitleTxt.getText().contentEquals("") || bookAuthorTxt.getText().contentEquals("")) {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("To add a book, you must fill out the book title and book author fields");
    		a.show();
    	}
    	//otherwise, add the book with the user information
    	else {
    		String bookTitleToAdd = bookTitleTxt.getText();
    		String bookAuthorToAdd = bookAuthorTxt.getText();
    		setBook(bookTitleToAdd, bookAuthorToAdd,0); //add book to the ArrayList
    		fb.FileWrite(allBooks); //add book to the file
    		updateBookMenu(); //update the menu of books
    		
    		//show confirmation book was added
    		a.setAlertType(AlertType.CONFIRMATION);
    		a.setContentText("Added the book " + bookTitleToAdd + " by " + bookAuthorToAdd + " to your list");
    		a.show();
    		
    		clearData(); //reset the fields
    	}
    }
    
   /*
    * setBook takes a String for the title and String for the author as arguments and uses
    * those to initialize and return a Book object. The book object is added to the current
    * book list if it is not already in the list
    * Params: String title, String author Returns: Book b
    */
    public static Book setBook(String title, String author, int chapter) {
    	Book b = new Book(title, author, chapter);
    	if(!allBooks.contains(b))
    		allBooks.add(b);//add book to arraylist of books
    	return b;
    }
    
    /*
     * getBook takes a String for the title and String for the author as arguments and uses
     * those to return a Book object in the list that matches. 
     * Params: String title, String author Returns: Book b
     */
    public static Book getBook(String title, String author) {
    	int index = 0;
    	while(index < allBooks.size()) {
    		if(allBooks.get(index).title.contains(title) && allBooks.get(index).author.contains(author)) {
    			return allBooks.get(index);
    		}
    		index++;
    	}
    	return null;
    }
    
    /*
     * chooseRandom is invoked when the user clicks on the random button (this is called from the
     * switchSceneProgressRandom class because that also is a method invoked by the same button).
     * This will save a random book to a variable in the ProgressController class so that class can
     * display the information about that book.
     * Params: Returns: void
     */
    public void chooseRandom() {
    	RandomGen r = new RandomGen();
    	int randIndex = r.GenerateRandom(allBooks.size());
    	ProgressController.chosenBook = allBooks.get(randIndex); //uncomment when progresscontroll class is ready
    }
    
    /*
     * updateBookMenu updates the list of books in the menu with the most recent options from the list of all books.
     * It also sets an event listener for each menu item to get the title and author and match it with a book
     * in order to pass that variable to the ProgressController class for display via the chosenBook variable
     * Params: none Returns: void
     */
    public void updateBookMenu() {
    	listAuthorsButton.getItems().clear(); //clear current menu
    	
    	//add each movie in the ArrayList to the menu
    	for(Book book : allBooks) {
    		MenuItem item = new MenuItem(book.author + ", " + book.title);
    		
    		//set an action event that makes each book menu item selectable
    		item.setOnAction(e -> {
    			chosen = null;
    			String bookInfo = item.getText();
    			String[] splitBookInfo = bookInfo.split(", ");
    			chosen = getBook(splitBookInfo[1], splitBookInfo[0]);
    			if(chosen != null) {
    				ProgressController.chosenBook = getBook(splitBookInfo[1], splitBookInfo[0]);
    			}			
    		});
    		listAuthorsButton.getItems().add(item);
    		menuItems.add(item);
    	}
    	listAuthorsButton.show();  //show the updated menu of books  	
    }
    
    /*
	 * clearData() will clear the bookAuthorTxt and bookTitleTxt fields
	 * Params: none Returns: void
	 */
	@FXML
	private void clearData() {
		bookAuthorTxt.clear();
		bookTitleTxt.clear();
	}
    
	/*
	 *  switchHomeSceneRandom launches the ProgressScene from the BookScene
	 *  on an ActionEvent (Read Now! or Choose Random button press) to provide user
	 *  with interface to log their progress on a randomly selected book
	 *  Params: ActionEvent event (javafx button click) Returns: void
	 */
	@FXML
	public void switchProgressSceneRandom(ActionEvent event) throws IOException{
			chooseRandom();
			bookSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressScene.fxml"));
			Scene scene = new Scene(bookSelectorBckg);// pane you are GOING TO show
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();// pane you are ON
			window.setScene(scene);
			window.show();
    }
	
	/*
	 *  switchHomeScene launches the ProgressScene from the BookScene
	 *  on an ActionEvent (Read Now! or Choose Random button press) to provide user
	 *  with interface to log their progress on a user-selected book
	 *  Params: ActionEvent event (javafx button click) Returns: void
	 */
	@FXML
	public void switchProgressScene(ActionEvent event) throws IOException{
		if(chosen != null) {
			bookSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressScene.fxml"));
    		Scene scene = new Scene(bookSelectorBckg);// pane you are GOING TO show
    		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        	Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();// pane you are ON
        	window.setScene(scene);
        	window.show();
		}
    }
	
	/*
     * returnHome launches the MainScene from the BookController class
     * Params: ActionEvent event (javafx button click) Returns: void
     */
    @FXML
	void returnHome(ActionEvent event) throws IOException {
    	 mainBckg = FXMLLoader.load(getClass().getResource("/application/MainScene.fxml"));// pane you are GOING TO
         Scene scene = new Scene(mainBckg);// pane you are GOING TO show
         scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
         Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
         window.setScene(scene);
         window.show();
    }
}
