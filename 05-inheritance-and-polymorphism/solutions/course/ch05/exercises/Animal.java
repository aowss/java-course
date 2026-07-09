package course.ch05.exercises;

public class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String speak() {
        return "...";
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "'}";
    }
}
