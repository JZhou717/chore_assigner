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
	int bathroom_count;
	int break_count;
	int kitchen_count;
	int floors_count;
	Chore most_recent;
	
	
}
