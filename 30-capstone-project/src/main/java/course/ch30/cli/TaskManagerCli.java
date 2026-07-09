package course.ch30.cli;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;
import course.ch30.repository.FileTaskRepository;
import course.ch30.service.TaskService;

/**
 * Command-line interface for the Task Manager application.
 *
 * <p>Commands:
 * <ul>
 *   <li>{@code add <title> [--desc <description>]}</li>
 *   <li>{@code list [--status <STATUS>]}</li>
 *   <li>{@code show <id>}</li>
 *   <li>{@code update <id> <STATUS>}</li>
 *   <li>{@code delete <id>}</li>
 *   <li>{@code count}</li>
 *   <li>{@code help}</li>
 *   <li>{@code exit}</li>
 * </ul>
 */
public final class TaskManagerCli {

    private final TaskService service;
    private final Scanner scanner;

    /**
     * Creates a CLI backed by the given service.
     *
     * @param service the task service
     * @param scanner the input scanner
     */
    public TaskManagerCli(TaskService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments (optional data file path)
     */
    public static void main(String[] args) {
        Path dataFile = args.length > 0
                ? Path.of(args[0])
                : Path.of(System.getProperty("user.home"), ".task-manager", "tasks.txt");

        FileTaskRepository repository = new FileTaskRepository(dataFile);
        TaskService service = new TaskService(repository);
        TaskManagerCli cli = new TaskManagerCli(service, new Scanner(System.in));

        System.out.println("Task Manager — type 'help' for commands");
        cli.run();
    }

    /**
     * Runs the interactive command loop.
     */
    public void run() {
        boolean running = true;
        while (running) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            running = handleCommand(line);
        }
        System.out.println("Goodbye.");
    }

    /**
     * Processes a single command line.
     *
     * @param line the raw input line
     * @return {@code false} if the application should exit
     */
    boolean handleCommand(String line) {
        String[] parts = line.split("\\s+");
        String command = parts[0].toLowerCase();

        return switch (command) {
            case "add" -> {
                handleAdd(parts);
                yield true;
            }
            case "list" -> {
                handleList(parts);
                yield true;
            }
            case "show" -> {
                handleShow(parts);
                yield true;
            }
            case "update" -> {
                handleUpdate(parts);
                yield true;
            }
            case "delete" -> {
                handleDelete(parts);
                yield true;
            }
            case "count" -> {
                System.out.println("Total tasks: " + service.taskCount());
                yield true;
            }
            case "help" -> {
                printHelp();
                yield true;
            }
            case "exit", "quit" -> false;
            default -> {
                System.out.println("Unknown command: " + command + ". Type 'help' for usage.");
                yield true;
            }
        };
    }

    private void handleAdd(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: add <title> [--desc <description>]");
            return;
        }
        StringBuilder title = new StringBuilder();
        String description = "";
        boolean readingDesc = false;
        StringBuilder descBuilder = new StringBuilder();

        for (int i = 1; i < parts.length; i++) {
            if ("--desc".equals(parts[i])) {
                readingDesc = true;
            } else if (readingDesc) {
                if (!descBuilder.isEmpty()) {
                    descBuilder.append(' ');
                }
                descBuilder.append(parts[i]);
            } else {
                if (!title.isEmpty()) {
                    title.append(' ');
                }
                title.append(parts[i]);
            }
        }
        description = descBuilder.toString();

        if (title.isEmpty()) {
            System.out.println("Title is required.");
            return;
        }

        Task task = service.createTask(title.toString(), description);
        System.out.println("Created: " + formatTask(task));
    }

    private void handleList(String[] parts) {
        List<Task> tasks;
        if (parts.length >= 3 && "--status".equals(parts[1])) {
            try {
                TaskStatus status = TaskStatus.valueOf(parts[2].toUpperCase());
                tasks = service.listByStatus(status);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Use: PENDING, IN_PROGRESS, COMPLETED, CANCELLED");
                return;
            }
        } else {
            tasks = service.listTasks();
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(formatTask(task));
        }
    }

    private void handleShow(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: show <id>");
            return;
        }
        try {
            UUID id = UUID.fromString(parts[1]);
            service.getTask(id)
                    .ifPresentOrElse(
                            task -> System.out.println(formatTaskDetail(task)),
                            () -> System.out.println("Task not found: " + id)
                    );
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID: " + parts[1]);
        }
    }

    private void handleUpdate(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: update <id> <STATUS>");
            return;
        }
        try {
            UUID id = UUID.fromString(parts[1]);
            TaskStatus status = TaskStatus.valueOf(parts[2].toUpperCase());
            Task updated = service.updateStatus(id, status);
            System.out.println("Updated: " + formatTask(updated));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleDelete(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: delete <id>");
            return;
        }
        try {
            UUID id = UUID.fromString(parts[1]);
            if (service.deleteTask(id)) {
                System.out.println("Deleted task: " + id);
            } else {
                System.out.println("Task not found: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID: " + parts[1]);
        }
    }

    private static String formatTask(Task task) {
        return String.format("[%s] %s (%s)", task.id(), task.title(), task.status());
    }

    private static String formatTaskDetail(Task task) {
        return String.format(
                "ID:          %s%nTitle:       %s%nDescription: %s%nStatus:      %s%nCreated:     %s",
                task.id(), task.title(), task.description(), task.status(), task.createdAt()
        );
    }

    private static void printHelp() {
        System.out.println("""
                Commands:
                  add <title> [--desc <description>]  Create a new task
                  list [--status <STATUS>]            List all tasks (optionally filter)
                  show <id>                           Show task details
                  update <id> <STATUS>                Update task status
                  delete <id>                         Delete a task
                  count                               Show total task count
                  help                                Show this help
                  exit                                Exit the application
                """);
    }
}
