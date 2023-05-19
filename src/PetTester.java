import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetTester {

	public static void main(String[] args) {
		
		//get user input
		Scanner input = new Scanner(System.in);

		//data structure to manage the pets
		List<Pet> pets = loadFile();
		
		//
		int choice = showMenu(input);
		while(choice != 4) {
			if(choice == 1) {
				viewAllPets(pets);
			} else if(choice == 2) {
			addPets(input, pets);	
			} else if(choice == 3) {
				removePet(input, pets);
			}
			choice = showMenu(input);
		}
		saveFile(pets);
		System.out.println("Goodbye!");
	}

	//display the menu and get the user choice
	private static int showMenu(Scanner input) {
		System.out.println("What would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("3) Remove an existing pet");
		System.out.println("4) Exit program");
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
	
	private static void updatePet(Scanner input, List<Pet> pets) {
		viewAllPets(pets);
		System.out.print("Enter the pet ID you want to update:");
		int id = input.nextInt();
		input.nextLine();
		if(id < 0 || id >= pets.size()) {
			System.out.println("Pet not found");
			return;
		}
		Pet pet = pets.get(id);
		String oldName = pet.getName();
		int oldAge = pet.getAge();
		System.out.print("Enter new name and new age:");
		String name = input.next();
		int age = input.nextInt();
		input.nextLine();
		pet.setName(name);
		pet.setAge(age);
		System.out.printf("%s %d changed to %s %d %n ", oldName, oldAge, pet.getName(), pet.getAge());
	}
	
	private static void removePet(Scanner input, List<Pet> pets) {
		viewAllPets(pets);
		System.out.print("Enter the pet ID to remove:");
		int id = input.nextInt();
		input.nextLine();
		if(id < 0 || id >= pets.size()) {
			System.out.println("Pet not found");
			return;
		}
		Pet pet = pets.remove(id);
		System.out.println(pet + " is removed.");
	}
	
	private static void saveFile(List<Pet> pets) {
		try(PrintWriter writer = new PrintWriter(new File("Pets.txt"))) {
			for(Pet pet: pets) {
				writer.println(pet);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<Pet> loadFile() {
		List<Pet> pets = new ArrayList<>();
		try(Scanner reader = new Scanner(new File("Pets.txt"))) {
			while(reader.hasNextLine()) {
			String data = reader.nextLine();
			String name = data.substring(0, data.indexOf(" "));
			int age = Integer.parseInt(data.substring(data.indexOf(" ") + 1));
			Pet pet = new Pet(name, age);
			pets.add(pet);
			}
		} catch (FileNotFoundException e) {
			return new ArrayList<>();
		}
		return pets;
	}
}
