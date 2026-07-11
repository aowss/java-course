package course.bridge.urs.cli;

import course.bridge.urs.RegistrationSystem;
import course.bridge.urs.model.Course;
import course.bridge.urs.model.Instructor;
import course.bridge.urs.model.Student;

import java.util.Scanner;

/**
 * Interactive CLI for the university registration system.
 */
public final class UniversityRegistrationCli {

    private UniversityRegistrationCli() {
    }

    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        seedDemoData(system);
        Scanner scanner = new Scanner(System.in);
        System.out.println("University Registration — type 'help' for commands");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (handleCommand(system, line)) {
                break;
            }
        }
        System.out.println("Goodbye.");
    }

    static void seedDemoData(RegistrationSystem system) {
        Instructor moussa = system.addInstructor("Moussa");
        Instructor salman = system.addInstructor("Salman");
        system.addCourse("Java", moussa.getId());
        system.addCourse("C", salman.getId());
        Student ali = system.addStudent("Ali");
        Student zaid = system.addStudent("Zaid");
        system.register(ali.getId(), 1);
        system.register(ali.getId(), 2);
        system.register(zaid.getId(), 1);
    }

    static boolean handleCommand(RegistrationSystem system, String line) {
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1].trim() : "";

        return switch (command) {
            case "help" -> {
                printHelp();
                yield false;
            }
            case "exit", "quit" -> true;
            case "instructors" -> {
                system.listInstructors().forEach(System.out::println);
                yield false;
            }
            case "courses" -> {
                system.listCourses().forEach(System.out::println);
                yield false;
            }
            case "students" -> {
                system.listStudents().forEach(System.out::println);
                yield false;
            }
            case "add-instructor" -> {
                if (rest.isBlank()) {
                    System.out.println("Usage: add-instructor <name>");
                } else {
                    Instructor i = system.addInstructor(rest);
                    System.out.println("Created " + i);
                }
                yield false;
            }
            case "add-course" -> {
                String[] tokens = rest.split("\\s+");
                if (tokens.length < 2) {
                    System.out.println("Usage: add-course <name> <instructorId>");
                } else {
                    try {
                        int instructorId = Integer.parseInt(tokens[tokens.length - 1]);
                        String name = String.join(" ", java.util.Arrays.copyOf(tokens, tokens.length - 1));
                        Course c = system.addCourse(name, instructorId);
                        System.out.println("Created " + c);
                    } catch (NumberFormatException e) {
                        System.out.println("instructor id must be a number");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                yield false;
            }
            case "add-student" -> {
                if (rest.isBlank()) {
                    System.out.println("Usage: add-student <name>");
                } else {
                    Student s = system.addStudent(rest);
                    System.out.println("Created " + s);
                }
                yield false;
            }
            case "register" -> {
                String[] tokens = rest.split("\\s+");
                if (tokens.length != 2) {
                    System.out.println("Usage: register <studentId> <courseId>");
                } else {
                    try {
                        system.register(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                        System.out.println("Registered.");
                    } catch (NumberFormatException e) {
                        System.out.println("ids must be numbers");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                yield false;
            }
            case "student-courses" -> {
                if (rest.isBlank()) {
                    System.out.println("Usage: student-courses <studentId>");
                } else {
                    try {
                        int id = Integer.parseInt(rest);
                        system.coursesForStudent(id).forEach(c ->
                                System.out.println("  " + c.getName() + " (instructor: "
                                        + c.getInstructor().getName() + ")"));
                    } catch (NumberFormatException e) {
                        System.out.println("student id must be a number");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                yield false;
            }
            case "course-roster" -> {
                if (rest.isBlank()) {
                    System.out.println("Usage: course-roster <courseId>");
                } else {
                    try {
                        int id = Integer.parseInt(rest);
                        system.studentsInCourse(id).forEach(s ->
                                System.out.println("  " + s.getName() + " (id=" + s.getId() + ")"));
                    } catch (NumberFormatException e) {
                        System.out.println("course id must be a number");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                yield false;
            }
            default -> {
                System.out.println("Unknown command. Type 'help'.");
                yield false;
            }
        };
    }

    private static void printHelp() {
        System.out.println("""
                instructors
                courses
                students
                add-instructor <name>
                add-course <name> <instructorId>
                add-student <name>
                register <studentId> <courseId>
                student-courses <studentId>
                course-roster <courseId>
                exit
                """);
    }
}
