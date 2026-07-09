package course.ch07.exercises;

public enum Color {

    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF"),
    WHITE("#FFFFFF"),
    BLACK("#000000");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    public String hex() {
        return hex;
    }

    public static Color fromHex(String hex) {
        String normalized = normalizeHex(hex);
        for (Color color : values()) {
            if (color.hex.equals(normalized)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unknown color: " + hex);
    }

    public boolean isDark() {
        int[] rgb = parseRgb(hex);
        return rgb[0] < 128 && rgb[1] < 128 && rgb[2] < 128;
    }

    public String complement() {
        int[] rgb = parseRgb(hex);
        return String.format("#%02X%02X%02X",
                255 - rgb[0], 255 - rgb[1], 255 - rgb[2]);
    }

    private static String normalizeHex(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex must not be null");
        }
        String trimmed = hex.trim().toUpperCase();
        if (!trimmed.startsWith("#")) {
            trimmed = "#" + trimmed;
        }
        return trimmed;
    }

    private static int[] parseRgb(String hexCode) {
        String digits = hexCode.substring(1);
        int r = Integer.parseInt(digits.substring(0, 2), 16);
        int g = Integer.parseInt(digits.substring(2, 4), 16);
        int b = Integer.parseInt(digits.substring(4, 6), 16);
        return new int[]{r, g, b};
    }

    public static void main(String[] args) {
        System.out.println(Color.RED.hex());
        System.out.println("RED is dark? " + Color.RED.isDark());
        System.out.println("RED complement: " + Color.RED.complement());
    }
}
