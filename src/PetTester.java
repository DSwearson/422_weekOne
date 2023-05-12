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
			} else if(choice == 5) {
				List<Pet> results = searchByName(input, pets);
				viewAllPets(results);
			} else if(choice == 6) {
				List<Pet> results = searchByAge(input, pets);
				viewAllPets(results);
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
		System.out.println("5) Search pets by name");
		System.out.println("6) Search pets by age");
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
	
	private static List<Pet> searchByName(Scanner input, List<Pet> pets) {
		List<Pet> results = new ArrayList<>();
		System.out.print("Enter a name to search:");
		String name = input.nextLine();
		for(Pet pet: pets) {
			if(pet.getName().equalsIgnoreCase(name)) {
				results.add(pet);
			}
		}
		return results;
	}
	
	private static List<Pet> searchByAge(Scanner input, List<Pet> pets) {
		List<Pet> results = new ArrayList<>();
		System.out.print("Enter age to search:");
		int age = input.nextInt();
		input.nextLine();
		for(Pet pet: pets) {
			if(pet.getAge() == age) {
				results.add(pet);
			}
		}
		return results;
	}
}
