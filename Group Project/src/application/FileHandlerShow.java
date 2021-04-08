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
	ArrayList<String> showTitle = new ArrayList<String>();
	ArrayList<String> episode = new ArrayList<String>();
	ArrayList<String> movies = new ArrayList<String>();
	
	
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
			//reads in each line and trims any leading or preceding spaces, if any
			showTitle.add(line.trim().toLowerCase()); 
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
	public void FileWriteMovie(ArrayList<String> titleWrite) throws IOException, FileNotFoundException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("movies.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of showTitle,episode
    	for(int i = 0; i < titleWrite.size(); i++){
    	 	fWrite.write(titleWrite.get(i) + "\n");
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
			showTitle.add(temp[0].toLowerCase()); //places the string before the comma into showTitle arraylist
			episode.add(temp[1]); //places the string after the comma into episode arraylist
			//read next line
			line = in.readLine();
		}
		in.close();
	}
	
	 /*
     * FileWriteShow() method writes the updated ArrayLists to the file shows.txt
     * @param two ArrayList<String> 
     * throws IOException and FileNotFoundException
     */
	
	public void FileWriteShow(ArrayList<String> titleWrite, ArrayList<String> epWrite) throws IOException, FileNotFoundException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("shows.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of showTitle,episode
    	for(int i = 0; i < titleWrite.size(); i++){
    	 	fWrite.write(titleWrite.get(i) + "," + epWrite.get(i) + "\n");
    	}
    	fWrite.close();
	}

}
