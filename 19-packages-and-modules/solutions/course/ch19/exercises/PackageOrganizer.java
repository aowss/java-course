package course.ch19.exercises;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PackageOrganizer {

    public static Map<String, String> organize(List<String> classNames) {
        var result = new LinkedHashMap<String, String>();
        for (String className : classNames) {
            result.put(className, categorize(className));
        }
        return result;
    }

    private static String categorize(String className) {
        if (className.endsWith("Service")) {
            return "service";
        } else if (className.endsWith("Repository") || className.endsWith("Dao")) {
            return "repository";
        } else if (className.endsWith("Controller")) {
            return "controller";
        } else if (className.endsWith("Util") || className.endsWith("Utils") || className.endsWith("Helper")) {
            return "util";
        } else {
            return "model";
        }
    }
}
