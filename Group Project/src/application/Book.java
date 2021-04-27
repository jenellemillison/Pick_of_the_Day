/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * Book.java
 * 
 * Book creates an instance of Book with the attributes of title and author
 */

package application;

public class Book {

	String title; //title of the book
	String author; //author of the book
	int chapter; //chapter of the book
	
	public Book(String title, String author, int chapter) {
		this.title = title; //sets the local title to the title passed in
		this.author = author; //sets the local author to the author passed in
		this.chapter = chapter; //sets the local chapter to the chapter passed in
	}
}
