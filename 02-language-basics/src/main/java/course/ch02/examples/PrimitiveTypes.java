package course.ch02.examples;

/**
 * Demonstrates all eight primitive types, their literals, and their ranges.
 */
public class PrimitiveTypes {

    public static void main(String[] args) {
        byte   b = 127;
        short  s = 32_767;
        int    i = 2_147_483_647;
        long   l = 9_223_372_036_854_775_807L;

        float  f = 3.14f;
        double d = 3.141_592_653_589_793;

        char   c = 'A';
        boolean flag = true;

        System.out.println("byte:    " + b + " (max: " + Byte.MAX_VALUE + ")");
        System.out.println("short:   " + s + " (max: " + Short.MAX_VALUE + ")");
        System.out.println("int:     " + i + " (max: " + Integer.MAX_VALUE + ")");
        System.out.println("long:    " + l + " (max: " + Long.MAX_VALUE + ")");
        System.out.println("float:   " + f);
        System.out.println("double:  " + d);
        System.out.println("char:    " + c + " (numeric value: " + (int) c + ")");
        System.out.println("boolean: " + flag);

        // Integer literals in different bases
        int decimal = 42;
        int hex     = 0x2A;
        int octal   = 052;
        int binary  = 0b0010_1010;

        System.out.println("\n42 in different bases:");
        System.out.println("  decimal: " + decimal);
        System.out.println("  hex:     " + hex);
        System.out.println("  octal:   " + octal);
        System.out.println("  binary:  " + binary);

        // Integer division truncates
        System.out.println("\nInteger division: 7 / 2 = " + (7 / 2));
        System.out.println("Double division:  7.0 / 2 = " + (7.0 / 2));
    }
}
