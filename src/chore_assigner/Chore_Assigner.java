/**
 * @author Jake Zhou
 * 
 * This class offers the abilities to review the user data for # times of each chore done per person, compute future chore assignments, or to edit existing data
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Chore_Assigner{
	
	/**
	 * A useful enum when referrring to a specific chore
	 */
	public static enum Chore {
		BATHROOM, BREAK, KITCHEN, FLOOR
	}
	/**
	 * Directory to store serialization data in
	 */
	public static final String store_dir = "dat";
	/**
	 * File to store this class's data
	 */
	public static final String store_file = "Chore_Assinger.dat";
	/**
	 * A list of the housemates. Should be NULL if this program has never been run, otherwise, list should have been read from serialized data
	 */
	private ArrayList<Person> people_list;
	
	/**
	 * Creates a new Chore Assigner that initializes an arraylist of all house members
	 */
	public Chore_Assigner() {
		people_list = new ArrayList<Person>();
		people_list.add(new Person("Jake"));
		people_list.add(new Person("Nick"));
		people_list.add(new Person("Olivia"));
		people_list.add(new Person("Shreya"));
		people_list.add(new Person("Sidd"));
		people_list.add(new Person("Tom"));
	}
	
	/**
	 * Reads Serialized Data
	 */
	static Chore_Assigner read_serialized() {
		
		Chore_Assigner ret = new Chore_Assigner();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(store_dir + File.separator + store_file));
			
			Chore_Assigner serialized_assigner = (Chore_Assigner) ois.readObject();
			
			//Chore_Assigner obj found properly, reading data back in
			ret.people_list = serialized_assigner.people_list;
			
			ois.close();
			return ret;
			
		}
		catch(FileNotFoundException e) {
			File file = new File(store_dir + File.separator + store_file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//If there is no serialized list or if something went wrong
		System.out.println("TESTING: NO SERIALIZED LIST. CREATING A NEW BLANK LIST");
		return ret;
		
	}
	
	/**
	 * Serializes this class that has a list of housemates, each of which is a Serializable Person class
	 * @throws IOException
	 */
	void serialize() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(store_dir + File.separator + store_file));
			oos.writeObject(this);
			oos.close();
		}
		catch(FileNotFoundException e) {
			File file = new File(store_dir + File.separator + store_file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Prints out each Person and their fields
	 */
	public void show_data() {
		System.out.println("");
		for(Person p: people_list) {
			p.print();
			System.out.println();
		}
		System.out.println();
		
	}

	public void edit_data() {
		// TODO Auto-generated method stub
		
	}

	public void generate_data() {
		// TODO Auto-generated method stub
		
	}
	
}
