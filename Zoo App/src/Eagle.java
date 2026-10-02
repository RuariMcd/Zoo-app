public class Eagle extends Animal implements Flyable {

    private String wingspan;

    public Eagle(String name, int age, String color, String weight, String wingspan) {
        super(name, age, color, weight);
        this.wingspan = wingspan;
    }

    public void setWingspan(String wingspan) {
        this.wingspan = wingspan;
    }

    public String getWingspan() {
        return wingspan;
    }

    @Override

    public void makeSound(){
        System.out.printf("SCREECH! I am %s, a %s year old eagle.", this.getName(), this.getAge());
    }
    @Override

    public void fly() {
        System.out.println("I am flying!");
    }

    @Override
    public void takeoff() {
        System.out.println("I am taking off!");
    }

    @Override
    public void land() {
        System.out.println("I am landing!");
    }

    @Override
    public String getString(){
        return "Eagle," + super.getString() + "," + wingspan;
    }

    @Override
    public void wingCheck() {
        System.out.print("\n" + this.getName() + "'s wings are checked and healthy.");
    }

    @Override
    public boolean isValid() { //Used for blank check before saving
        return super.isValid() && !wingspan.isBlank();
    }
}
