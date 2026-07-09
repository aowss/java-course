package course.ch05.exercises;

public class Add extends Expr {

    private final Expr left;
    private final Expr right;

    public Add(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval() {
        return left.eval() + right.eval();
    }

    @Override
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}
