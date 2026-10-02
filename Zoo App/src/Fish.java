public class Fish extends Animal implements Swimmable {
    private int finNumber;

    public Fish(String name, int age, String color, String weight, int finNumber) {
        super(name, age, color, weight);
        this.finNumber = finNumber;
    }

    public int getFinNumber() {
        return finNumber;
    }

    public void setFinNumber(int finNumber) {
        this.finNumber = finNumber;
    }

    @Override
    public void makeSound() {
        System.out.printf("Blub... I am %s, a %s year old fish.",this.getName(), this.getAge());
    }

    @Override
    public void swim() {
        System.out.println("I am swimming!");
    }

    @Override
    public void dive(){
        System.out.println("I am diving!");
    }

    @Override
    public void surface() {
        System.out.println("I am surfacing!");
    }

    @Override
    public String getString(){
        return "Fish," + super.getString() + "," + finNumber;
    }

    @Override
    public void waterCheck() {
        System.out.print("\nThe water in " + this.getName() + "'s enclosure is in good condition.");
    }

    //Dont actually need an is valid check here because i made it so they have to have an age.
}
