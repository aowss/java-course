package course.ch05.exercises;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Woof!";
    }

    @Override
    public String toString() {
        return "Dog{name='" + getName() + "'}";
    }
}
