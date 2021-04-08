/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * FileHandlerBook.java
 * 
 * FileHandlerBook reads and write to the save file for Books
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
 * FileHandlerBook class reads and writes to the
 * books.txt save file.
 * Methods include:
 * 		FileRead()
 * 		FileWrite(ArrayList<String>, ArrayList<String>)
 */
public class FileHandlerBook {
	//book will have Title and author
	ArrayList<String> bookTitle = new ArrayList<String>();
	ArrayList<String> author = new ArrayList<String>();

    /*
     * FileRead() method reads the books.txt file into two separate arraylists
     * for the book title and the author.
     * throws IOException and FileNotFoundException
     */
	public void FileRead() throws IOException, FileNotFoundException{
		BufferedReader in;
		//savepath file
		File inFile = new File("books.txt"); 
		
		//if the files doesn't exist, create it, otherwise we will read the existing file
		if(!(inFile.exists())) {
			File newFile = new File("books.txt");
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
			bookTitle.add(temp[0].toLowerCase()); //places the string before the comma into bookTitle arraylist
			author.add(temp[1].toLowerCase()); //places the string after the comma into author arraylist
			//read next line
			line = in.readLine();
		}
		in.close();
	}
	
	 /*
     * FileWrite() method writes the updated ArrayLists to the file inventory.txt
     * @param two ArrayList<String> 
     * throws IOException and FileNotFoundException
     */
	
	public void FileWrite(ArrayList<String> titleWrite, ArrayList<String> authorWrite) throws IOException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("books.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of bookTitle,author
    	for(int i = 0; i < titleWrite.size(); i++){
    	 	fWrite.write(titleWrite.get(i) + "," + authorWrite.get(i) + "\n");
    	}
    	fWrite.close();
	}
}
