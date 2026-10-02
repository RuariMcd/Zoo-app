public abstract class Animal {

    private String name;
    private int age;
    private String color;
    private String weight;

    //Paramaterised constructor
    public Animal(String name, int age, String color, String weight) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.weight = weight;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public abstract void makeSound();

    public String getString(){ //Method to return the stuff to be wrote to file
        return name + "," + age + "," + color + "," + weight;
    }

    public boolean isValid() { //Method used to make sure thta there are not blank columns in the animal before saving.
        return !name.isBlank() && !color.isBlank() && !weight.isBlank();
    }
}
