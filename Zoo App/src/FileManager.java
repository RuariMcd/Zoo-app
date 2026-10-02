import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static void saveAnimals(String file, ArrayList<Animal> animals) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Animal animal : animals) { //for every animal in the array
                if (animal != null && //if nothing is blank
                        !animal.getName().isBlank() &&
                        !animal.getColor().isBlank() &&
                        !animal.getWeight().isBlank()   ) { //Age literally cannot be blank so dont need to check it

                    pw.println(animal.getString()); //write it to file
                }
            }
        } catch (IOException e) {
            System.out.println("Save error");
        }
    }

    public static void saveZoo(String file, Zoo zoo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(zoo.getString()); //Write to file
        } catch (IOException e) {
            System.out.println("Save error");
        }
    }

    public static ArrayList<Animal> loadAnimals(String file) {
        ArrayList<Animal> animals = new ArrayList<>();  //create the array

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) { //get each line
                String[] current = line.split(","); //split the line into words in an array

                switch (current[0]) { //create a new object of type whatever based on the case, then add in each value.
                    case "Lion":
                        animals.add(new Lion(current[1], Integer.parseInt(current[2]), current[3], current[4], (current[5])));
                        break;

                    case "Eagle":
                        animals.add(new Eagle(current[1], Integer.parseInt(current[2]), current[3], current[4], (current[5])));
                        break;

                    case "Fish":
                        animals.add(new Fish(current[1], Integer.parseInt(current[2]), current[3], current[4], Integer.parseInt(current[2])));
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("No animal file found");
        }

        return animals; //give back the filled array
    }

    public static Zoo loadZoo(String file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); //get the line

            if (line != null) {
                String[] parts = line.split(","); //split it up into the array again
                return new Zoo(parts[0], parts[1]); //return the zoo object
            }

        } catch (IOException e) {
            System.out.println("No zoo file found.");
        }

        return null;
    }
}
