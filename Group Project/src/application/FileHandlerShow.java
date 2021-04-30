/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * FileHandlerShow.java
 * 
 * FileHandlerShow reads and writes to the save file for shows
 */

package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * FileHandlerShow class reads and writes to the
 * shows.txt and movies.txt save files.
 * Methods include:
 * 		FileReadMovie()
 * 		FileWriteMovie(ArrayList<String>)
 * 		FileReadShow()
 * 		FileWriteShow(ArrayList<String>, ArrayList<String>)
 */

public class FileHandlerShow {
	//shows will have Title and Episode, movies just has titles
	
	
	/*
     * FileReadMovie() method reads the movies.txt file into an arraylist
     * for the movie title.
     * throws IOException and FileNotFoundException
     */
	public void FileReadMovie() throws IOException, FileNotFoundException{
		BufferedReader in;
		//savepath file
		File inFile = new File("movies.txt"); 
		
		//if the files doesn't exist, create it, otherwise we will read the existing file
		if(!(inFile.exists())) {
			File newFile = new File("movies.txt");
			newFile.createNewFile();
		
			in = new BufferedReader(new FileReader(newFile));
		}
		else {
			in = new BufferedReader(new FileReader(inFile));
		}
		
		//read in each line of movies.txt
		String line = in.readLine();
		while(line != null) {
			//String temp[] = line.split("");
			Movie movieToAdd = null;
			if((movieToAdd = ShowController.setmovie(line)) != null && !ShowController.movies.contains(movieToAdd))
				ShowController.movies.add(movieToAdd);
			//read next line
			line = in.readLine();
		}
		in.close();
	}

	 /*
     * FileWriteMovie() method writes the updated ArrayLists to the file movies.txt
     * @param ArrayList<String> 
     * throws IOException and FileNotFoundException
     */
	public void FileWriteMovie(ArrayList<Movie> titleWrite) throws IOException, FileNotFoundException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("movies.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of showTitle,episode
    	for(int i = 0; i < titleWrite.size(); i++){
    	 	fWrite.write(titleWrite.get(i).title + "\n");
    	}
    	fWrite.close();
	}
	
	

    /*
     * FileReadShow() method reads the shows.txt file into two separate arraylists
     * for the show title and the episode.
     * throws IOException and FileNotFoundException
     */
	public void FileReadShow() throws IOException, FileNotFoundException{
		BufferedReader in;
		//savepath file
		File inFile = new File("shows.txt"); 
		
		//if the files doesn't exist, create it, otherwise we will read the existing file
		if(!(inFile.exists())) {
			File newFile = new File("shows.txt");
			newFile.createNewFile();
		
			in = new BufferedReader(new FileReader(newFile));
		}
		else {
			in = new BufferedReader(new FileReader(inFile));
		}
	
	  	
		//read in each line of books.txt 
		String line = in.readLine();
		while(line != null) {
			String temp[] = line.split(","); //splits the lines at the , mark
			Show showToAdd = null;
			//adds book info so long as the list doesn't already contain it
			if((showToAdd = ShowController.setshow(temp[0].toLowerCase(), Integer.parseInt(temp[1])))!= null && !ShowController.showTitle.contains(showToAdd))
				ShowController.showTitle.add(showToAdd);
			line = in.readLine();
		}
		in.close();
	}
	
	 /*
     * FileWriteShow() method writes the updated ArrayLists to the file shows.txt
     * @param two ArrayList<String> 
     * throws IOException and FileNotFoundException
     */
	
	public void FileWriteShow(ArrayList<Show> titleWrite) throws IOException, FileNotFoundException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("shows.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of showTitle,episode
    	for(int i = 0; i < titleWrite.size(); i++){
    	 	fWrite.write(titleWrite.get(i).title + "," + titleWrite.get(i).episode + "\n");
    	}
    	fWrite.close();
	}

}
