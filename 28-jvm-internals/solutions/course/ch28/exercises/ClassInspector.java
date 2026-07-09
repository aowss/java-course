package course.ch28.exercises;

import java.lang.reflect.Modifier;

public final class ClassInspector {

    private ClassInspector() {
    }

    public static String describe(Class<?> clazz) {
        String kind = clazz.isInterface() ? "interface" : "class";
        String name = clazz.getName();
        String superName = clazz.getSuperclass() != null ? clazz.getSuperclass().getName() : "none";
        String modifiers = formatModifiers(clazz);
        if (clazz.isInterface()) {
            return kind + " " + name + " [" + modifiers + "]";
        }
        return kind + " " + name + " extends " + superName + " [" + modifiers + "]";
    }

    public static int declaredFieldCount(Class<?> clazz) {
        return clazz.getDeclaredFields().length;
    }

    public static boolean isRecord(Class<?> clazz) {
        return clazz.isRecord();
    }

    private static String formatModifiers(Class<?> clazz) {
        int mods = clazz.getModifiers();
        StringBuilder sb = new StringBuilder();
        if (Modifier.isFinal(mods)) {
            sb.append("final");
        }
        if (Modifier.isAbstract(mods) && !clazz.isInterface()) {
            if (!sb.isEmpty()) {
                sb.append(' ');
            }
            sb.append("abstract");
        }
        if (clazz.isSealed()) {
            if (!sb.isEmpty()) {
                sb.append(' ');
            }
            sb.append("sealed");
        }
        return sb.isEmpty() ? "none" : sb.toString();
    }
}
