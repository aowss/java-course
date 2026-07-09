package course.ch05.exercises;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return "Meow!";
    }

    @Override
    public String toString() {
        return "Cat{name='" + getName() + "'}";
    }
}
