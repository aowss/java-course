package course.ch05.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalTest {

    @Test
    void animalSpeakReturnsEllipsis() {
        var animal = new Animal("Rex");
        assertEquals("...", animal.speak());
    }

    @Test
    void dogSpeakReturnsWoof() {
        var dog = new Dog("Rex");
        assertEquals("Woof!", dog.speak());
    }

    @Test
    void catSpeakReturnsMeow() {
        var cat = new Cat("Whiskers");
        assertEquals("Meow!", cat.speak());
    }

    @Test
    void dynamicDispatchThroughAnimalReference() {
        Animal animal = new Dog("Buddy");
        assertEquals("Woof!", animal.speak());
    }

    @Test
    void getNameReturnsCorrectName() {
        var dog = new Dog("Rex");
        assertEquals("Rex", dog.getName());
    }

    @Test
    void animalToString() {
        var animal = new Animal("Rex");
        assertEquals("Animal{name='Rex'}", animal.toString());
    }

    @Test
    void dogToString() {
        var dog = new Dog("Rex");
        assertEquals("Dog{name='Rex'}", dog.toString());
    }

    @Test
    void catToString() {
        var cat = new Cat("Whiskers");
        assertEquals("Cat{name='Whiskers'}", cat.toString());
    }
}
