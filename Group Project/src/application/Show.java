/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * Show.java
 * 
 * Show creates an instance of Show with title and episode attributes
 */

package application;

public class Show {
	
	String title; //title of the show
	int episode; //episode that the user is on
	
	public Show(String title, int episode) {
		this.title = title; //sets the local title to that passed in
		this.episode = episode; //sets the episode to that passed in
	}
}
