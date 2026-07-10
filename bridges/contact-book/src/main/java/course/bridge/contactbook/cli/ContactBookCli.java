package course.bridge.contactbook.cli;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import course.bridge.contactbook.model.Contact;
import course.bridge.contactbook.repository.InMemoryContactRepository;
import course.bridge.contactbook.service.ContactService;

/**
 * Simple CLI for the contact book bridge project.
 */
public final class ContactBookCli {

    private final ContactService service;
    private final Scanner scanner;

    public ContactBookCli(ContactService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        ContactService service = new ContactService(new InMemoryContactRepository());
        ContactBookCli cli = new ContactBookCli(service, new Scanner(System.in));
        System.out.println("Contact Book — type 'help' for commands");
        cli.run();
    }

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

    boolean handleCommand(String line) {
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1].trim() : "";

        return switch (command) {
            case "add" -> {
                handleAdd(rest);
                yield true;
            }
            case "list" -> {
                handleList();
                yield true;
            }
            case "find" -> {
                handleFind(rest);
                yield true;
            }
            case "remove" -> {
                handleRemove(rest);
                yield true;
            }
            case "count" -> {
                System.out.println("Contacts: " + service.count());
                yield true;
            }
            case "help" -> {
                printHelp();
                yield true;
            }
            case "exit", "quit" -> false;
            default -> {
                System.out.println("Unknown command. Type 'help'.");
                yield true;
            }
        };
    }

    private void handleAdd(String rest) {
        String[] tokens = rest.split("\\s+");
        if (tokens.length < 2) {
            System.out.println("Usage: add <name> <email> [phone]");
            return;
        }
        String name = tokens[0];
        String email = tokens[1];
        String phone = tokens.length > 2 ? tokens[2] : "";
        try {
            Contact created = service.addContact(name, email, phone);
            System.out.println("Added: " + formatContact(created));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void handleList() {
        List<Contact> contacts = service.listContacts();
        if (contacts.isEmpty()) {
            System.out.println("(no contacts)");
            return;
        }
        contacts.forEach(c -> System.out.println(formatContact(c)));
    }

    private void handleFind(String query) {
        if (query.isBlank()) {
            System.out.println("Usage: find <name fragment>");
            return;
        }
        List<Contact> matches = service.searchByName(query);
        if (matches.isEmpty()) {
            System.out.println("(no matches)");
            return;
        }
        matches.forEach(c -> System.out.println(formatContact(c)));
    }

    private void handleRemove(String idText) {
        if (idText.isBlank()) {
            System.out.println("Usage: remove <id>");
            return;
        }
        try {
            UUID id = UUID.fromString(idText);
            if (service.removeContact(id)) {
                System.out.println("Removed: " + id);
            } else {
                System.out.println("Contact not found: " + id);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid id: " + idText);
        }
    }

    private static String formatContact(Contact contact) {
        String phone = contact.phone().isBlank() ? "" : " | " + contact.phone();
        return "[" + contact.id() + "] " + contact.name() + " <" + contact.email() + ">" + phone;
    }

    private static void printHelp() {
        System.out.println("""
                Commands:
                  add <name> <email> [phone]   Add a contact
                  list                         List all contacts (sorted by name)
                  find <name fragment>         Search by name
                  remove <id>                  Delete a contact
                  count                        Show total contacts
                  help                         Show this help
                  exit                         Quit
                """);
    }
}
