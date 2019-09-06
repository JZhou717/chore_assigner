/**
 * @author Jake Zhou
 * 
 * A simple application that assigns chores to my housemates and me for the year. It follows some simple rules and tracks how many times each person has done one of the 4 categories of chores. The data is then serialized so that I do not have to input data everytime I want to make an adjustment to chore assignments.
 */




/*
 * WILL have 3 features
 * 1. View current data, i.e., people and their amounts per chore
 * 2. Compute future weeks of chores
 * 3. Edit people data 
 * 
 * NEED TO IMPLEMENT
 * ~ Serialization
 * ~ IO messages
 * ~ Assignment algorithm
 */




package chore_assigner;

import java.io.IOException;
import java.util.ArrayList;

public class Chore_Assigner{
	
	/**
	 * A useful enum when referrring to a specific chore
	 */
	public enum Chore {
		BATHROOM, BREAK, KITCHEN, FLOOR
	}

	/**
	 * A list of the housemates. Should be NULL if this program has never been run, otherwise, list should have been read from serialized data
	 */
	private ArrayList<Person> people_list;
	
	/**
	 * Main runner method that reads input from user, i.e., me, and outputs computation
	 */
	public static void main(String[] args) {
		//Listener for application termination. Runs serialization on shut down
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					serialize();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void serialize() throws IOException {
		
	}
	
}
