import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class ZooApp {
    static ArrayList<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        animals = FileManager.loadAnimals("AnimalDetails.txt"); //Makes it so it loads from the text files on startup
        Zoo zoo = FileManager.loadZoo("ZooDetails.txt");

        int choice = 9; //Default it to 9 so even if the user puts some weird stuff later itll restart fine.

        System.out.printf("Welcome to the system for %s in %s!\n", zoo.getName(), zoo.getLocation());

        do {
            System.out.println("\n--- ZOO MENU ---");
            System.out.println("1. Add Animal");
            System.out.println("2. Remove Animal");
            System.out.println("3. Edit Animal");
            System.out.println("4. View Animals");
            System.out.println("5. Search Animals");
            System.out.println("6. Daily Care");
            System.out.println("7. Zoo Report");
            System.out.println("8. Update Zoo");
            System.out.println("0. Exit");

            try { //Validation for user input
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 0 && choice <= 8) {


                    switch (choice) {

                        case 1:
                            addAnimalMenu(scanner);
                            break;

                        case 2:
                            removeAnimal(scanner, animals);
                            break;


                        case 3:
                            editAnimal(scanner, animals);
                            break;


                        case 4:
                            viewAnimals(animals);
                            break;

                        case 5 :
                            searchMenu(scanner);
                            break;


                        case 6 :
                            dailyCare();
                            break;


                        case 7:
                            printZooReport(animals, zoo);
                            break;


                        case 8:
                            editZoo(scanner, zoo);
                            break;

                    }
                }
                else {
                    System.out.println("Invalid choice! Enter 0–8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 0 and 8!");
            }

        } while (choice != 0);

        FileManager.saveZoo("ZooDetails.txt", zoo);
        FileManager.saveAnimals("AnimalDetails.txt", animals); //This is what auto saves it when the user exits.
        System.out.println("File Saved!\nGoodbye!");
        scanner.close();
    }

    public static void addAnimalMenu(Scanner scanner) {
        int type = 0;
        int age = 0;
        int fin = 0; //have to initialize them up here so the try catches work. These will not be 0 at the end anyway.

        while(true){
            System.out.println("Note: An animal with any blank data will NOT be saved when exiting.");
            System.out.println("1. Lion 2. Eagle 3. Fish");
            try {
                type = Integer.parseInt(scanner.nextLine());

                if (type >= 1 && type <= 3) { //valid entry
                    break; //move on
                } else {
                    System.out.println("Enter a number between 1 and 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        while (true) {//age validation
            System.out.print("Age: ");

            try {
                age = Integer.parseInt(scanner.nextLine());

                if (age >= 0) {
                    break; //move on if good
                } else {
                    System.out.println("Age must be 0 or higher.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer.");
            }
        }

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Weight: ");
        String weight = scanner.nextLine();

        //some of the above can be left blank, but will be dealt with when writing to file
        switch (type) {

            case 1 : //Lion
                System.out.print("Mane size: ");
                String mane = scanner.nextLine();
                animals.add(new Lion(name, age, color, weight, mane));
                break;


            case 2 ://Eagle
                System.out.print("Wing span: ");
                String wing = scanner.nextLine();
                animals.add(new Eagle(name, age, color, weight, wing));
                break;


            case 3 : //Fish
                while (true) {
                    System.out.print("Fin number: ");

                    try {
                        fin = Integer.parseInt(scanner.nextLine());

                        if (fin >= 0) {
                            break;
                        } else {
                            System.out.println("Must be 0 or higher.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter a valid integer.");
                    }
                }

                animals.add(new Fish(name, age, color, weight, fin));
                System.out.println("Animal added successfully.");
                break;

        }
    }

    public static void removeAnimal(Scanner scanner, ArrayList<Animal> animals) {

        System.out.print("Enter the name of the animal you want to remove: ");
        String name = scanner.nextLine();

        boolean removed = false;

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);

            if (animal.getName().equalsIgnoreCase(name)) {
                animals.remove(i); //Remoevs from the array, will overwrite the file when saving.
                System.out.println("Animal '" + name + "' was removed successfully.");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("No animal with the name '" + name + "' was found.");
        }
    }

    public static void viewAnimals(ArrayList<Animal> animals) {

        if (animals.isEmpty()) {
            System.out.println("No animals registered.");
            return;
        }

        System.out.println("\n--- ALL ANIMALS ---");

        for (Animal animal : animals) {
            System.out.println(animal.getString());
        }
    }

    public static void searchMenu(Scanner scanner) {
        int option = 0;
        while (true) {
            System.out.println("1. Search by name \n2. Search by color");

            try {
                option = Integer.parseInt(scanner.nextLine());

                if (option == 1 || option == 2) {
                    break;
                } else {
                    System.out.println("Must be 1 or 2.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer.");
            }
        }


        if (option == 1) {

            System.out.print("Name: ");
            String name = scanner.nextLine();

            for (Animal animal : animals) {
                if (animal.getName().equalsIgnoreCase(name)) { //**This does not deal with duplicate names, but the color one does
                    System.out.println("Found: " + animal.getName());
                    animal.makeSound(); // polymorphism
                    return;
                }
            }

            System.out.println("Not found");
        }

        else if (option == 2) {

            System.out.print("Color: ");
            String color = scanner.nextLine();

            for (Animal animal : animals) {
                if (animal.getColor().equalsIgnoreCase(color)) {
                    System.out.println("\n" + animal.getName());
                    animal.makeSound();
                }
            }
        }
    }

    public static void dailyCare() {

        for (Animal animal : animals) {

            System.out.println("\nChecking " + animal.getName());
            animal.makeSound();

            if (animal instanceof Flyable fly) { //check to see if it uses an interface, if yes do the special method.
                fly.wingCheck();
            }

            if (animal instanceof Swimmable swim) {
                swim.waterCheck();
            }

            System.out.println("\nDaily check complete.");
        }
    }

    public static void editAnimal(Scanner scanner, ArrayList<Animal> animals) {
        System.out.println("Note: An animal with any blank data will NOT be saved when exiting.");
        System.out.print("Enter name of animal to edit: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {

            if (animal.getName().equalsIgnoreCase(name)) {

                System.out.println("Editing " + animal.getName());

                //General edit for every type of animal. No special fields ye

                System.out.print("New name (leave blank to keep): ");
                String newName = scanner.nextLine();
                if (!newName.isBlank()){
                    animal.setName(newName);
                }

                System.out.print("New age (-1 to keep): ");
                try {
                    int newAge = Integer.parseInt(scanner.nextLine());

                    if (newAge != -1 && newAge >= 0){
                        animal.setAge(newAge);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid age, skipped.");
                }

                System.out.print("New color (leave blank to keep): ");
                String newColor = scanner.nextLine();
                if (!newColor.isBlank()){
                    animal.setColor(newColor);
                }

                System.out.print("New weight (leave blank to keep): ");
                String newWeight = scanner.nextLine();
                if (!newWeight.isBlank()){
                    animal.setWeight(newWeight);
                }

                //Had to add this so that the user can also edit the special variables like mane size wingspan etc.

                if (animal instanceof Lion lion) {
                    System.out.print("New mane size(Leave blank to keep): ");
                    String mane = scanner.nextLine();
                    if (!mane.isBlank()){
                        lion.setManeSize(mane);
                    }

                } else if (animal instanceof Eagle eagle) {
                    System.out.print("New wingspan (leave blank to keep): ");
                    String wing = scanner.nextLine();
                    if (!wing.isBlank()){
                        eagle.setWingspan(wing);
                    }

                } else if (animal instanceof Fish fish) {
                    System.out.print("New fin number (-1 to keep): ");
                    try {
                        int fins = Integer.parseInt(scanner.nextLine());

                        if (fins != -1 && fins >= 0){
                            fish.setFinNumber(fins);
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid input, skipped.");
                    }
                }

                System.out.println("Animal updated.");
                return;
            }
        }

        System.out.println("Animal not found.");
    }

    public static void printZooReport(ArrayList<Animal> animals, Zoo zoo) {

        if (animals.isEmpty()) {
            System.out.println("No animals registered.");
            return;
        }

        int lions = 0;
        int eagles = 0;
        int fish = 0;

        HashMap<String, Integer> colorCount = new HashMap<>(); //dictionary to store the colours and their count

        for (Animal animal : animals) {

            //Get the amount of lions eagles and fish
            if (animal instanceof Lion){
                lions++;
            }
            else if (animal instanceof Eagle){
                eagles++;
            }
            else if (animal instanceof Fish){
                fish++;
            }

            //Get the different colours and their count
            String color = animal.getColor().toLowerCase();

            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
        }

        //just get the highest colour by comparting
        String dominantColor = "";
        int max = 0;

        for (String color : colorCount.keySet()) {
            int count = colorCount.get(color);

            if (count > max) {
                max = count;
                dominantColor = color;
            }
        }

        System.out.println("\n--- ZOO REPORT ---");

        if (zoo != null) {
            System.out.println("Zoo Name: " + zoo.getName());
            System.out.println("Zoo location: " + zoo.getLocation());
        } else {
            System.out.println("Zoo Name: My Zoo");
        }

        System.out.println("Lions: " + lions);
        System.out.println("Eagles: " + eagles);
        System.out.println("Fish: " + fish);

        System.out.println("Dominant Colour: " + dominantColor);
    }

    public static void editZoo(Scanner scanner, Zoo zoo) {
        //this top one is for if there is no zoo name registerd. Wont really be used but oh well.
        if (zoo == null) {
            System.out.print("Enter zoo name: ");
            String name = scanner.nextLine();

            System.out.print("Enter zoo location: ");
            String location = scanner.nextLine();

            zoo = new Zoo(name, location);
            System.out.println("New zoo created.");
            return;
        }

        //This is for editing the current one .
        System.out.println("\n--- EDIT ZOO ---");

        System.out.println("Current Name: " + zoo.getName());
        System.out.print("New name (leave blank to keep): ");
        String newName = scanner.nextLine();

        if (!newName.isBlank()) {
            zoo.setName(newName);
        }

        System.out.println("Current Location: " + zoo.getLocation());
        System.out.print("New location (leave blank to keep): ");
        String newLocation = scanner.nextLine();

        if (!newLocation.isBlank()) {
            zoo.setLocation(newLocation);
        }

        System.out.println("Zoo updated.");
    }

}