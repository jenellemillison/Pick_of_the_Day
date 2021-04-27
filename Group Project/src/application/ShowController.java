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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.FileHandlerShow;
public class ShowController implements Initializable{

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
    private MenuButton chooseShowsButton;

    FileHandlerShow sh = new FileHandlerShow();
    static ArrayList<Show> showTitle = new ArrayList<Show>();
	static ArrayList<Movie> movies = new ArrayList<Movie>();
    
    @FXML
	public void progress(ActionEvent event) throws IOException{
    	movieSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressScene.fxml"));
    	Scene scene = new Scene(movieSelectorBckg);
        Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public void randitem() {
    	RandomGen rand = new RandomGen();
    	int rindex = rand.GenerateRandom(movies.size()) - 1;
    	//ProgressController.chosenshow = showTitle.get(rindex); uncomment when progresscontroll class is ready
    }
    
    @FXML
	public void progressrand(ActionEvent event) throws IOException{
    	randitem();
    	movieSelectorBckg = FXMLLoader.load(getClass().getResource("/application/ProgressScene.fxml"));
    	Scene scene = new Scene(movieSelectorBckg);
        Stage window = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseShowsButton.getItems().clear();
		chooseShowsButton.getItems().add(new MenuItem("Movies"));
		chooseShowsButton.getItems().add(new MenuItem("Shows"));
		addshowtext.setText("");
		try {
			sh.FileReadMovie();
			sh.FileReadShow();
		} catch (IOException e) {
			System.out.println("Error while reading file of show and movie information.");
		}
		updateshows();
		updatemovies();
	}
	
	public static Movie setmovie(String title) {
    	Movie m = new Movie(title);
    	if(!movies.contains(m))
    		movies.add(m);
    	return m;
    }
	
	public static Show setshow(String title, int episode) {
    	Show s = new Show(title, episode);
    	if(!showTitle.contains(s))
    		showTitle.add(s);
    	return s;
    }
    
	public void addlist(ActionEvent event) throws IOException {
    	Alert a = new Alert(AlertType.NONE);
    	if(addshowtext.getText().contentEquals("")) {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Enter a show or movie into the text field if you would like to add one.");
    		a.show();
    	}
    	else {
    		if(chooseShowsButton.equals("Shows")) {
    		String showadd = addshowtext.getText();
    		setshow(showadd,0);
    		sh.FileWriteShow(showTitle);
    		updateshows();
    		a.setAlertType(AlertType.CONFIRMATION);
    		a.setContentText("Added the show " + addshowtext);
    		a.show();
    		addshowtext.clear();
    		}
    		else if(chooseShowsButton.equals("Movies")) {
    			String movieadd = addshowtext.getText();
        		setmovie(movieadd);
        		sh.FileWriteMovie(movies);
        		updatemovies();
        		a.setAlertType(AlertType.CONFIRMATION);
        		a.setContentText("Added the movie " + addshowtext);
        		a.show();
        		addshowtext.clear();
    		}
    	}
    }
	
	public void updateshows() {
		listShowsButton.getItems().clear();
    	for(Show show : showTitle) {
    		listShowsButton.getItems().add(new MenuItem(show.title + ", " + show.episode));
    	}
    	listShowsButton.show();    	
    }
	
	public void updatemovies() {
		listShowsButton.getItems().clear();
    	for(Movie movie : movies) {
    		listShowsButton.getItems().add(new MenuItem(movie.title));
    	}
    	listShowsButton.show();    	
    }
	
	public void chooseShow(ActionEvent event) {
    	//ProgressController.chosenshow = getItem.getText(); uncomment when progresscontroll class is ready
    }
	
}