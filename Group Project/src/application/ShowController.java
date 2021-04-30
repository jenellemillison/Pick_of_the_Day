/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * ShowController.java
 * 
 * ShowController implements changes to the ShowScene based on user input and
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.FileHandlerShow;

public class ShowController implements Initializable{

	//Buttons, Textfields, and Panes shown on the ShowScene that the user can interact with to make changes
    @FXML
    private MenuButton listShowsButton;

    @FXML
    private Button addShowToListButton;

    @FXML
    private Button watchNowButton;

    @FXML
    private TextField addshowtext;

    @FXML
    private Button randomWatchButton;

    @FXML
    private AnchorPane movieSelectorBckg;

    @FXML
    private RadioButton Showbutton;

    @FXML
    private RadioButton Moviebutton;
    
    @FXML
    private Button homeButton;
    
    //static varaibles that can be accessed from the ProgressControllerShow class to update show and movie progress
    public static FileHandlerShow sh = new FileHandlerShow();
    static ArrayList<Show> showTitle = new ArrayList<Show>();
	static ArrayList<Movie> movies = new ArrayList<Movie>();
	
	//variables for this class only, the menu and the chosen show and chosen movie
	private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	private Show chosens = null;
	private Movie chosenm = null;
	
	/*
	 * progress switches the scene to ProgressScene based on if
	 * either the chosens or chosenm variables hold any information.
	 * chosens is for the chosen show and chosnm is for the chosen movie.
	 * Params: ActionEvent (javafx button click) Returns: void
	 */
    @FXML
	public void progress(ActionEvent event) throws IOException{
    	if(chosens != null || chosenm != null) {
    		movieSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressSceneShow.fxml"));
    		Scene scene = new Scene(movieSelectorBckg);
    		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
    		Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();
    		window.setScene(scene);
    		window.show();
    	}
    }
    
    /*
	 * randitem is a method for choosing a random movie or show
	 * depending on if the movie button, show button, or if nothing is selected.
	 * Params: none Returns: void
	 */
    public void randitem() {
    	int rindex = 0;
    	RandomGen rand = new RandomGen();
    	if(Moviebutton.isSelected()) {
    		rindex = rand.GenerateRandom(movies.size());
    		ProgressControllerShow.chosenMovie = movies.get(rindex);
    		ProgressControllerShow.isMovie = true;
    		ProgressControllerShow.isShow = false;
    	}
    	else if (Showbutton.isSelected()){
        	rindex = rand.GenerateRandom(showTitle.size());
        	ProgressControllerShow.chosenShow = showTitle.get(rindex);
        	ProgressControllerShow.isMovie = true;
    		ProgressControllerShow.isShow = false;
        }
    }
    
    /*
	 * progressrand uses the randitem method to get a random show or movie
	 * and switch to the ProgressScene when the random button is clicked on
	 * the ShowScene.
	 * Params: ActionEvent (javafx button click) Returns: void
	 */
    @FXML
	public void progressrand(ActionEvent event) throws IOException{
    	randitem();
    	movieSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressSceneShow.fxml"));
    	Scene scene = new Scene(movieSelectorBckg);
    	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    /*
	 * initialize, initializes variables and the starting values used
	 * when the ShowScene is launched
	 * Params: a URL location, ResourceBundle resources Returns: void
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//reset fields on screen
		ToggleGroup radBtnsToggle = new ToggleGroup();
		Showbutton.setToggleGroup(radBtnsToggle);
		Moviebutton.setToggleGroup(radBtnsToggle);
		addshowtext.setText("");
		
		//reset ArrayLists
		showTitle.clear();
		movies.clear();
		listShowsButton.getItems().clear();
		
		//read in file data on shows and movies
		try {
			sh.FileReadMovie();
			sh.FileReadShow();
		} catch (Exception e) {
			System.out.println("Error while reading file of show and movie information.");
		}
		
		//use read in data to update menus
		updateshows();
		updatemovies();
	}
	
	/*
	 * setmovie is a method to add movies to the movies array list,
	 * only if they aren't already in it.
	 * Params: String title of movie Returns: Movie object just created
	 */
	public static Movie setmovie(String title) {
    	Movie m = new Movie(title);
    	if(!movies.contains(m))
    		movies.add(m);
    	return m;
    }
	
	/*
	 * setshow is a method to add movies to the movies array list,
	 * only if they aren't already in it.
	 * Params: String title of show, int current episode number of show user is watching
	 * Returns: Show object just created
	 */
	public static Show setshow(String title, int episode) {
    	Show s = new Show(title, episode);
    	if(!showTitle.contains(s))
    		showTitle.add(s);
    	return s;
    }
	
	/*
	 * getmovie returns a movie based on if it's title
	 * exists in the movies array list
	 * Params: String title of movie to get, Returns: Movie object matching the title string
	 */
	public static Movie getmovie(String title) {
    	int index = 0;
    	while(index < movies.size()) {
    		if(movies.get(index).title.contains(title)) {
    			return movies.get(index);
    		}
    		index++;
    	}
    	return null;
    }
	
	/*
	 * getshow returns a movie based on if it's title
	 * exists in the showTitle array list
	 * Params: String title of show to get, Returns: Show object matching the title string
	 */
	public static Show getshow(String title) {
    	int index = 0;
    	while(index < showTitle.size()) {
    		if(showTitle.get(index).title.contains(title)) {
    			return showTitle.get(index);
    		}
    		index++;
    	}
    	return null;
    }
    
	/*
	 * addlist adds movies or shows to their respective array lists
	 * depending on if the show or movie button is selected
	 * Params: ActionEvent (javafx button click of the add button)
	 * Returns: void
	 */
	public void addlist(ActionEvent event) throws IOException {
		
		//if nothing entered, error
    	Alert a = new Alert(AlertType.NONE);
    	if(addshowtext.getText().contentEquals("")) {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Enter a show or movie into the text field if you would like to add one.");
    		a.show();
    	}
    	
    	//otherwise, add the show or movie with the user information
    	else {
    		//add the show
    		if(Showbutton.isSelected()) {
    		String showadd = addshowtext.getText();
    		setshow(showadd,0); //add to ArrayList
    		sh.FileWriteShow(showTitle); //add to file
    		updateshows(); //update the menu of shows
    		
    		//show confirmation show was added
    		a.setAlertType(AlertType.CONFIRMATION);
    		a.setContentText("Added the show " + showadd);
    		a.show();
    		
    		addshowtext.clear(); //reset text field
    		}
    		//add the movie
    		else if(Moviebutton.isSelected()) {
    			String movieadd = addshowtext.getText();
        		setmovie(movieadd); //add to ArrayList
        		sh.FileWriteMovie(movies); //add to file
        		updatemovies(); //update the menu of movies
        		
        		//show confirmation movie was added
        		a.setAlertType(AlertType.CONFIRMATION);
        		a.setContentText("Added the movie " + movieadd);
        		a.show();
        		
        		addshowtext.clear(); //reset text field
    		}
    	}
    }
	
	/*
	 * buttonshow sets the items in the menubutton to
	 * show only the shows from shows.txt
	 * Params: ActionEvent (javafx button click on show radio button) Returns: void
	 */
	public void buttonshow(ActionEvent event) {
		if(Showbutton.isSelected()) {
			updateshows();
		}
	}
	
	/*
	 * buttonmovie sets the items in the menubutton to
	 * show only the shows from movie.txt
	 * Params: ActionEvent (javafx button click on movie radio button) Returns: void
	 */
	public void buttonmovie(ActionEvent event) {
		if(Moviebutton.isSelected()) {
			updatemovies();
		}
	}
	
	/*
	 * updateshows updates any changes that have been made
	 * to the showTitle arraylist and the shows.txt file,
	 * and applies them to the menu for choosing shows or movies
	 * Params: none Returns: void
	 */
	public void updateshows() {
		listShowsButton.getItems().clear(); //clear the current menu
    	
		//add each show in the ArrayList to the menu
		for(Show show : showTitle) {
    		MenuItem item = new MenuItem(show.title);
    		
    		//set an action event that makes each show menu item selectable
    		item.setOnAction(e -> {
    			chosens = null;
    			String showInfo = item.getText();
    			chosens = getshow(showInfo);
    			if(chosens != null) {
    				ProgressControllerShow.chosenShow = getshow(showInfo);
    				ProgressControllerShow.isMovie = false;
    				ProgressControllerShow.isShow = true;
    			}			
    		});
    		listShowsButton.getItems().add(item);
    		menuItems.add(item);
    	}
    	listShowsButton.show(); //show the updated menu of shows 	
    }
	
	/*
	 * updatemovies updates any changes that have been made
	 * to the movies arraylist and the movies.txt file,
	 * and applies them to the menu for choosing shows or movies
	 * Params: none Returns: void
	 */
	public void updatemovies() {
		listShowsButton.getItems().clear(); //clear current menu
    	
		//add each movie in the ArrayList to the menu
		for(Movie movie : movies) {
    		MenuItem item = new MenuItem(movie.title);
    		
    		//set an action event that makes each movie menu item selectable
    		item.setOnAction(e -> {
    			chosenm = null;
    			String movieInfo = item.getText();
    			chosenm = getmovie(movieInfo);
    			if(chosenm != null) {
    				ProgressControllerShow.chosenMovie = getmovie(movieInfo);
    				ProgressControllerShow.isMovie = true;
    				ProgressControllerShow.isShow = false;
    			}			
    		});
    		listShowsButton.getItems().add(item);
    		menuItems.add(item);
    	}
    	listShowsButton.show();  //show the updated menu of movies  	
    }
	
	
	/*
	 * returnHome switches to the MainScene
	 * from the ShowScene
	 * Params: ActionEvent (javafx button click on home icon) Returns: void
	 */
	@FXML
	void returnHome(ActionEvent event) throws IOException {
		movieSelectorBckg = FXMLLoader.load(getClass().getResource("/application/MainScene.fxml"));// pane you are GOING TO
        Scene scene = new Scene(movieSelectorBckg);// pane you are GOING TO show
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
	
}
	
