package chore_assigner;

import java.io.IOException;

/**
 * A simple application that assigns chores to my housemates and me for the year. It follows some simple rules and tracks how many times each person has done one of the 4 categories of chores. The data is then serialized so that I do not have to input data every time I want to make an adjustment to chore assignments.
 * 
 * This is the runner class for the application. Handles the IO with the user, i.e., me.
 * @author Jake Zhou
 *
 */

public class Chore_Assigner_Runner {

	private static Chore_Assigner Assigner;
	
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
					e.printStackTrace();
				}
			}

		});
	
		//Read people_list back into this obj
		try {
			read_serialized();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Reads the Chore Assigner obj back into this runner
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static void read_serialized() throws ClassNotFoundException, IOException {
		Assigner = (Chore_Assigner) Chore_Assigner.read_serialized();
		
	}
	
	/**
	 * Serializes the objects for this app
	 * @throws IOException 
	 */
	private static void serialize() throws IOException {
		Assigner.serialize();
		
	}
}
