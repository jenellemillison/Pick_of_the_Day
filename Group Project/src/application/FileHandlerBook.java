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
			Book bookToAdd = null;
			//adds book info so long as the list doesn't already contain it
			if((bookToAdd = BookController.setBook(temp[0].toLowerCase(), temp[1].toLowerCase(), Integer.parseInt(temp[2]))) != null && !BookController.allBooks.contains(bookToAdd))
				BookController.allBooks.add(bookToAdd);
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
	
	public void FileWrite(ArrayList<Book> bookWrite) throws IOException {
		//overwrites the file, creates one if it doesn't exist.
    	File outFile = new File("books.txt");
    	FileWriter fWrite = new FileWriter(outFile, false);
    	
    	//writes to the file in the format of bookTitle,author
    	for(int i = 0; i < bookWrite.size(); i++){
    	 	fWrite.write(bookWrite.get(i).title + "," + bookWrite.get(i).author + "\n");
    	}
    	fWrite.close();
	}
}
