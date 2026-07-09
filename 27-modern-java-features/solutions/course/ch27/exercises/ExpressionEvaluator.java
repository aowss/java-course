package course.ch27.exercises;

public final class ExpressionEvaluator {

    private ExpressionEvaluator() {
    }

    public sealed interface Expr permits Literal, Add, Mul, Neg {
    }

    public record Literal(double value) implements Expr {
    }

    public record Add(Expr left, Expr right) implements Expr {
    }

    public record Mul(Expr left, Expr right) implements Expr {
    }

    public record Neg(Expr expr) implements Expr {
    }

    public static double evaluate(Expr expr) {
        return switch (expr) {
            case Literal l -> l.value();
            case Add a -> evaluate(a.left()) + evaluate(a.right());
            case Mul m -> evaluate(m.left()) * evaluate(m.right());
            case Neg n -> -evaluate(n.expr());
        };
    }
}
