package course.ch20.exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionResolver {

    public record SemVer(int major, int minor, int patch) implements Comparable<SemVer> {

        public static SemVer parse(String version) {
            if (version == null) {
                throw new IllegalArgumentException("Version must not be null");
            }
            String[] parts = version.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid version format: " + version);
            }
            try {
                return new SemVer(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2])
                );
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid version format: " + version, e);
            }
        }

        @Override
        public int compareTo(SemVer other) {
            int cmp = Integer.compare(this.major, other.major);
            if (cmp != 0) return cmp;
            cmp = Integer.compare(this.minor, other.minor);
            if (cmp != 0) return cmp;
            return Integer.compare(this.patch, other.patch);
        }

        @Override
        public String toString() {
            return major + "." + minor + "." + patch;
        }
    }

    private static final Pattern CONSTRAINT_PATTERN = Pattern.compile("(>=|<=|>|<|=)(\\d+\\.\\d+\\.\\d+)");

    public static boolean satisfies(SemVer version, String range) {
        String[] constraints = range.split(",");
        for (String constraint : constraints) {
            constraint = constraint.trim();
            Matcher m = CONSTRAINT_PATTERN.matcher(constraint);
            if (!m.matches()) {
                throw new IllegalArgumentException("Invalid constraint: " + constraint);
            }
            String op = m.group(1);
            SemVer target = SemVer.parse(m.group(2));
            int cmp = version.compareTo(target);

            boolean satisfied = switch (op) {
                case ">=" -> cmp >= 0;
                case "<=" -> cmp <= 0;
                case ">"  -> cmp > 0;
                case "<"  -> cmp < 0;
                case "="  -> cmp == 0;
                default   -> throw new IllegalArgumentException("Unknown operator: " + op);
            };

            if (!satisfied) {
                return false;
            }
        }
        return true;
    }
}
