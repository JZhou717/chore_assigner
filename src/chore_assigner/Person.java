/**
 * @author Jake Zhou
 * There will be a person class for each of the house members. Each Person obj will have a name, a count for each of the 4 jobs, and the most recent chore assigned
 */

package chore_assigner;

import java.io.Serializable;
import chore_assigner.Chore_Assigner.Chore;

public class Person implements Serializable {

	/**
	 * Default value... no current expectations for expansion or alteration after final version to this class
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	int bathroom_count = 0;
	int break_count = 0;
	int kitchen_count = 0;
	int floors_count = 0;
	Chore most_recent;
	
	/**
	 * Constructor that creates a Person with the given name
	 * @param string
	 */
	public Person(String string) {
		name = string;
	}
	
	/**
	 * Prints the person's data fields
	 */
	public void print() {
		System.out.println(name);
		System.out.println("bathroom_count: " + bathroom_count);
		System.out.println("break_count: " + break_count);
		System.out.println("kitchen_count: " + kitchen_count);
		System.out.println("floors_count: " + floors_count);
		System.out.println("most_recent: " + most_recent);
		
	}
	
}
