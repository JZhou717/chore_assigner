package chore_assigner;

/**
 * This object represents a chore week with 6 slots of chores to do
 * 
 * A chore week can be constructed based off of a previous week, or with a seed from the user
 * 
 * @author Jake Zhou
 *
 */
public class Chore_Week {
	
	//The previous week of chore assignments
	Chore_Week previous;
	
	Person bathroom_1;
	Person bathroom_2;
	Person break_1;
	Person break_2;
	Person kitchen;
	Person floors;
	
	
	/**
	 * This constructor creates a new week based on info from last week. It follows these basic rules:
	 * Break > Floor/Kitchen
	 * Kitchen/Floor > Bathroom
	 * Bathroom > Break
	 * 
	 * For the two who are moving from break to floor/kitchen, the one who has cleaned kitchen fewer (or equals) times is assigned kitchen while the other is assigned floors
	 */
	public Chore_Week(Chore_Week prev_week) {
		
		/*
		 * To determine kitchen and floor assignments
		 */
		Person prev_break_1 = prev_week.break_1;
		Person prev_break_2 = prev_week.break_2;
		
		//Previous break_1 person has fewer or equal past kitchen assignments
		if(prev_break_1.kitchen_count <= prev_break_2.kitchen_count) {
			this.kitchen = prev_break_1;
			this.kitchen.kitchen_count++;
			
			this.floors = prev_break_2;
			this.floors.floors_count++;
		}
		//Previous break_2 person has fewer past kitchen assignments
		else {
			this.kitchen = prev_break_2;
			this.kitchen.kitchen_count++;
			
			this.floors = prev_break_1;
			this.floors.floors_count++;
		}
		/*
		 * end of kitchen and floor assignments
		 */
		
		//Simple assignments
		this.bathroom_1 = prev_week.kitchen;
		this.bathroom_1.bathroom_count++;
		
		this.bathroom_2 = prev_week.floors;
		this.bathroom_2.bathroom_count++;
		
		this.break_1 = prev_week.bathroom_1;
		this.break_1.break_count++;
		
		this.break_2 = prev_week.bathroom_2;
		this.break_2.break_count++;
		
	}
	
	/**
	 * This constructor creates a new week based on info seeded by the user
	 */
	public Chore_Week(Person bath_1, Person bath_2, Person br_1, Person br_2, Person kit, Person flr) {
		this.bathroom_1 = bath_1; 
		bath_1.bathroom_count++;
		
		this.bathroom_2 = bath_2; 
		bath_2.bathroom_count++;
		
		this.break_1 = br_1; 
		br_1.break_count++;
		
		this.break_2 = br_2; 
		br_2.break_count++;
		
		this.kitchen = kit; 
		kit.kitchen_count++;
		
		this.floors = flr; 
		flr.floors_count++;
	}

	/**
	 * Prints out the assignments for this Chore_Week
	 */
	public void print() {
		System.out.println(bathroom_1.name + " and " + bathroom_2.name + " on bathroom duty");
		System.out.println(break_1.name + " and " + break_2.name + " on break");
		System.out.println(kitchen.name + " on kitchen duty");
		System.out.println(floors.name + " on floor duty");
		System.out.println();
		
	}
}
