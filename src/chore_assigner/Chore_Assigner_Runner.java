package chore_assigner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chore_assigner.Chore_Assigner.Chore;






/*
 * Things left to do:
 * 
 * Create Chore_Week based on most_recent assignments if they exist instead of asking for user seed
 * Should remove the most_recent fields and have a Chore_Week most_recent_week in the Assigner to base future assignments from
 * 
 * Should be a feature that alerts user when these discrepancies happen:
 * 1. Not all people have the same total chore assignments
 * 2. When the difference between a person's bathroom and break assignments are greater than 1
 * 3. When the difference between a person's kitchen and floor assignments are greater than 1
 * 
 */






















/**
 * A simple application that assigns chores to my housemates and me for the year. It follows some simple rules and tracks how many times each person has done one of the 4 categories of chores. The data is then serialized so that I do not have to input data every time I want to make an adjustment to chore assignments.
 * 
 * This is the runner class for the application. Handles the IO with the user, i.e., me.
 * @author Jake Zhou
 *
 */

public class Chore_Assigner_Runner{

	private static Scanner in;
	
	private static String input;
	
	private static Chore_Assigner Assigner;
	
	/**
	 * Main runner method that reads input from user, i.e., me, and outputs computation
	 */
	public static void main(String[] args) {
		
		//Listener for application termination. Runs serialization on shut down
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					in.close();
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
		
		//Input reader
		in = new Scanner(System.in);
		
		while(true) {
			System.out.println("Which of the following operations would you like to preform:");
			System.out.println("\"view\" data");
			System.out.println("\"edit\" data");
			System.out.println("\"generate\" chores");
			System.out.println("\"quit\" application\n");
			
			input = in.next().toLowerCase().trim();
			
			switch(input) {
				case "view":
					Assigner.show_data(); break;
				case "edit":
					get_edit_input(); break;
				case "generate":
					get_generate_input(); break;
				case "quit":
					in.close(); System.exit(0); break;
				default:
					System.out.println("Invalid input. Please try again.\n");
			}
		}
		
	}

	/**
	 * Reads user input for how many weeks to generate chores for. Asks for a seed from the user for the first week
	 */
	private static void get_generate_input() {
		System.out.println("How many weeks of chore assignments would you like to generate?");
		
		//the num of weeks we are going to generate
		int cycles = 0;
		
		if(in.hasNextInt()) {
			cycles = in.nextInt();
		}
		else {
			System.out.println("Invalid input. Please try again.\n");
			get_generate_input();
			return;
		}
		
		//These fields store who is assigned to each chore for the first week
		Person bathroom_1;
		Person bathroom_2;
		Person break_1;
		Person break_2;
		Person kitchen;
		Person floors;
		
		System.out.println("Input chore assignments for first week:\n");
		
		//Calls chore_person to find person assigned to the following chores
		bathroom_1 = chore_person(Chore.BATHROOM);
		bathroom_2 = chore_person(Chore.BATHROOM);
		break_1 = chore_person(Chore.BREAK);
		break_2 = chore_person(Chore.BREAK);
		kitchen = chore_person(Chore.KITCHEN);
		floors = chore_person(Chore.FLOOR);
		
		//List of Chore_Week objs to print later
		ArrayList<Chore_Week> cw_list = new ArrayList<Chore_Week>();
		
		Chore_Week temp;
		
		//First chore week created from input
		temp = new Chore_Week(bathroom_1, bathroom_2, break_1, break_2, kitchen, floors);
		cw_list.add(temp);
		
		//Further chore weeks generated based on previous week
		for(int i = 1; i < cycles; i++) {
			temp = new Chore_Week(temp);
			cw_list.add(temp);
		}
		
		//Printing all the chore weeks generated
		for(int i = 1; i <= cw_list.size(); i++) {
			System.out.println("\nWeek " + i);
			cw_list.get(i-1).print();
		}
		
		
	}
	
	/*
	 * A helper function that asks for a person assigned to the input chore
	 */
	private static Person chore_person(Chore chore) {
		
		Person p;
		
		System.out.println("Input person assigned to " + chore);
		input = in.next().toLowerCase().trim();
		
		//p should hold the Person obj with the input name, null otherwise
		p = Assigner.find_person(input);
		
		if(p == null) {
			System.out.println("Invalid input. Please try again.\n");
			//Calling this method again to ask for person assigned to chore
			return chore_person(chore);
		}
		
		return p;
	}

	/**
	 * If the user chooses to edit existing data, this method reads which Person should be edited and what fields
	 */
	private static void get_edit_input() {
		
		int person = -1;
		int bathroom_count = 0, break_count = 0, kitchen_count = 0, floor_count = 0;
		Chore recent = null;
		
		boolean done = false;
		
		while(!done) {
			System.out.println("Which housemate's data would you like to edit?");
			input = in.next().toLowerCase().trim();
			switch(input) {
				case "jake":
					person = 0; done = true; break;
				case "nick":
					person = 1; done = true; break;
				case "olivia":
					person = 2; done = true; break;
				case "shreya":
					person = 3; done = true; break;
				case "sidd":
					person = 4; done = true; break;
				case "tom":
					person = 5; done = true; break;
				default:
					System.out.println("Invalid input. Please try again.\n");
			}
		}
		
		done = false;
		
		while(!done) {
			System.out.println("How many times have they cleaned the bathroom?");
			if(in.hasNextInt()) {
				bathroom_count = in.nextInt();
				done = true;
			}
			else {
				System.out.println("Invalid input. Please try again.\n");
			}
		}

		done = false;
		
		while(!done) {
			System.out.println("How many times have they been on break?");
			if(in.hasNextInt()) {
				break_count = in.nextInt();
				done = true;
			}
			else {
				System.out.println("Invalid input. Please try again.\n");
			}
		}

		done = false;
		
		while(!done) {
			System.out.println("How many times have they cleaned the kitchen?");
			if(in.hasNextInt()) {
				kitchen_count = in.nextInt();
				done = true;
			}
			else {
				System.out.println("Invalid input. Please try again.\n");
			}
		}

		done = false;
		
		while(!done) {
			System.out.println("How many times have they cleaned the floors?");
			if(in.hasNextInt()) {
				floor_count = in.nextInt();
				done = true;
			}
			else {
				System.out.println("Invalid input. Please try again.\n");
			}
		}
		
		done = false;
		
		while(!done) {
			System.out.println("What was their most recent chore? \"bathroom\", \"break\", \"kitchen\", \"floor\",  or \"null\"");
			input = in.next().toLowerCase().trim();
			switch(input) {
				case "bathroom":
					recent = Chore.BATHROOM; done = true; break;
				case "break":
					recent = Chore.BREAK; done = true; break;
				case "kitcen":
					recent = Chore.KITCHEN; done = true; break;
				case "floor":
					recent = Chore.FLOOR; done = true; break;
				case "null":
					recent = null; done = true; break;
				default:
					System.out.println("Invalid input. Please try again.\n");
			}
			
		}
		
		Assigner.update_person(person, bathroom_count, break_count, kitchen_count, floor_count, recent);
		
	}

	/**
	 * Reads the Chore Assigner obj back into this runner
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static void read_serialized() throws ClassNotFoundException, IOException {
		Assigner = (Chore_Assigner) Chore_Assigner.read_serialized();
		if(Assigner == null) {
			Assigner = new Chore_Assigner();
		}
		
	}
	
	/**
	 * Serializes the objects for this app
	 * @throws IOException 
	 */
	private static void serialize() throws IOException {
		Assigner.serialize();
		
	}
}
