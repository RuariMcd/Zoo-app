public class Lion extends Animal{

    private String maneSize;

    public Lion(String name, int age, String color, String weight, String maneSize){
        super(name, age, color, weight);
        this.maneSize = maneSize;
    }

    public void setManeSize(String maneSize) {
        this.maneSize = maneSize;
    }

    public String getManeSize() {
        return maneSize;
    }

    @Override
    public void makeSound() {
        System.out.printf("ROAR! I am %s, a %s year old lion", this.getName(), this.getAge());
    }

    @Override
    public String getString(){
        return "Lion," + super.getString() + "," + maneSize;
    }
    @Override
    public boolean isValid() { //Used to make sure that nothing is blank before saving.
        return super.isValid() && !maneSize.isBlank();
    }
}
