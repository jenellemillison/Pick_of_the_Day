/*
 * Group 11: Couch Potatoes
 * Class: CS3443-003
 * Assignment: Group project, Title: Pick of the Day
 * RandomGen.java
 * 
 * RandomGen generates a random number based on arraylist size
 */

package application;

import java.util.Random;


public class RandomGen {

	public int rando; //int to hold a random num
	public int arrayListSize; //int to hold the arraysize
	Random rand = new Random(System.currentTimeMillis()); //instance of random class
	
	/*
	 * GenerateRandom() is a method that generates a random number and returns it
	 * 		return: int
	 * @param int
	 *
	 */

	public Integer GenerateRandom(int arrayListSize) {
		//gets the size of the arraylist
		this.arrayListSize = arrayListSize; //finds the upperbound depending on arraylist size
		rando = rand.nextInt(this.arrayListSize); //finds a random number between the range of 0 to bounds - 1
		
		
		return rando; //returns the random number generated
	}
}
