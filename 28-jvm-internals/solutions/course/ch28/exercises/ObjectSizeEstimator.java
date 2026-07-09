package course.ch28.exercises;

import java.lang.reflect.Array;

public final class ObjectSizeEstimator {

    public static final int OBJECT_HEADER_BYTES = 12;
    public static final int ARRAY_HEADER_BYTES = 16;
    public static final int REFERENCE_BYTES = 4;

    private ObjectSizeEstimator() {
    }

    public static long estimate(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Integer || obj instanceof Float) {
            return OBJECT_HEADER_BYTES + 4;
        }
        if (obj instanceof Long || obj instanceof Double) {
            return OBJECT_HEADER_BYTES + 8;
        }
        if (obj instanceof Boolean || obj instanceof Byte) {
            return OBJECT_HEADER_BYTES + 1;
        }
        if (obj instanceof Character || obj instanceof Short) {
            return OBJECT_HEADER_BYTES + 2;
        }
        if (obj instanceof String s) {
            return estimateString(s);
        }
        if (obj.getClass().isArray()) {
            return estimateArray(obj);
        }
        return OBJECT_HEADER_BYTES + (long) REFERENCE_BYTES * obj.getClass().getDeclaredFields().length;
    }

    public static long estimateString(String value) {
        return OBJECT_HEADER_BYTES + ARRAY_HEADER_BYTES + 2L * value.length() + REFERENCE_BYTES;
    }

    public static long estimatePrimitiveArray(Object array, int bytesPerElement) {
        int length = Array.getLength(array);
        return ARRAY_HEADER_BYTES + (long) bytesPerElement * length;
    }

    private static long estimateArray(Object array) {
        Class<?> componentType = array.getClass().getComponentType();
        int length = Array.getLength(array);
        int bytesPerElement = switch (componentType.getName()) {
            case "byte" -> 1;
            case "boolean" -> 1;
            case "char", "short" -> 2;
            case "int", "float" -> 4;
            case "long", "double" -> 8;
            default -> REFERENCE_BYTES;
        };
        return ARRAY_HEADER_BYTES + (long) bytesPerElement * length;
    }
}
