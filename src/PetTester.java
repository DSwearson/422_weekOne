import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetTester {

	public static void main(String[] args) {
		
		//get user input
		Scanner input = new Scanner(System.in);

		//data structure to manage the pets
		List<Pet> pets = new ArrayList<>();
		
		//
		int choice = showMenu(input);
		while(choice != 7) {
			if(choice == 1) {
				viewAllPets(pets);
			} else if(choice == 2) {
			addPets(input, pets);	
			}
			choice = showMenu(input);
		}
		System.out.println("Goodbye!");
	}

	//display the menu and get the user choice
	private static int showMenu(Scanner input) {
		System.out.println("What would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("7) Exit program");
		System.out.print("Your choice:");
		int choice = input.nextInt();
		input.nextLine();
		
		return choice;
		
	}
	
	private static void viewAllPets(List<Pet> pets) {
		System.out.println("+----------------------+");
		System.out.printf("| %-3s| %-10s| %-4s| %n","ID","NAME","AGE");
		System.out.println("+----------------------+");
		int count = 0;
		for(; count<pets.size();count++) {
			Pet pet = pets.get(count);
			System.out.printf("|%3d | %-10s|%4d | %n",count,pet.getName(),pet.getAge());
		}
		System.out.println("+----------------------+");
		System.out.println(count + " rows in set");

	}
	
	private static void addPets(Scanner input, List<Pet> pets) {
		while(true) {
			System.out.print("add pet (name, age): ");
			String name = input.next();
			if(name.equalsIgnoreCase("done")) {
				input.nextLine();
				return;
			}
			int age = input.nextInt();
			input.nextLine();
			Pet pet = new Pet(name, age);
			pets.add(pet);
		}
	}
}
