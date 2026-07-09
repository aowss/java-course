package course.ch05.exercises;

public class Num extends Expr {

    private final double value;

    public Num(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
