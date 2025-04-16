package loop.demo;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private DocumentGraph graph;
    private Scanner scanner;
    
    public ConsoleUI(DocumentGraph graph) {
        this.graph = graph;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n==== Mahabharata Version Control System ====");
            System.out.println("1. List all versions");
            System.out.println("2. View version details");
            System.out.println("3. List relationships for a version");
            System.out.println("4. View reconstruction theories");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    listAllVersions();
                    break;
                case 2:
                    viewVersionDetails();
                    break;
                case 3:
                    listRelationships();
                    break;
                case 4:
                    viewTheories();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        
        System.out.println("Thank you for using the system.");
    }
    
    private void listAllVersions() {
        System.out.println("\nAll Available Versions:");
        List<TextVersion> versions = graph.getAllVersions();
        if (versions.isEmpty()) {
            System.out.println("No versions available.");
            return;
        }
        for (int i = 0; i < versions.size(); i++) {
            System.out.println((i + 1) + ". " + versions.get(i));
        }
    }
    
    private void viewVersionDetails() {
        listAllVersions();
        System.out.print("Enter version number to view: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        List<TextVersion> versions = graph.getAllVersions();
        if (idx >= 0 && idx < versions.size()) {
            TextVersion version = versions.get(idx);
            System.out.println("\nDetails for " + version.getName());
            System.out.println("Region: " + version.getRegion());
            System.out.println("Date: " + version.getApproximateDate());
            System.out.println("Verses: " + version.getVerseCount());
            System.out.println("Unique Features: " + version.getUniqueFeatures());
            System.out.println("Completeness: " + (version.getCompleteness() * 100) + "%");
            
            // Display annotations
            System.out.println("\nAnnotations:");
            List<Annotation> annotations = graph.getAnnotationsForVersion(version.getId());
            if (annotations.isEmpty()) {
                System.out.println("No annotations found.");
            } else {
                for (Annotation anno : annotations) {
                    System.out.println("- " + anno.getAuthor() + ": " + anno.getContent());
                }
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    private void listRelationships() {
        System.out.print("Enter version number to list relationships: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        List<TextVersion> versions = graph.getAllVersions();
        if (idx >= 0 && idx < versions.size()) {
            TextVersion version = versions.get(idx);
            List<Relationship> relationships = graph.getRelationshipsForVersion(version.getId());
            
            System.out.println("\nRelationships for " + version.getName() + ":");
            if (relationships.isEmpty()) {
                System.out.println("No relationships found.");
            } else {
                for (Relationship rel : relationships) {
                    System.out.println("- " + rel.getSource().getName() + " -> " + rel.getTarget().getName() + 
                                       " (" + rel.getType() + ", Similarity: " + rel.getSimilarity() + ")");
                }
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
    
    private void viewTheories() {
        System.out.println("\nReconstruction Theories:");
        List<ReconstructionTheory> theories = graph.getAllTheories();
        if (theories.isEmpty()) {
            System.out.println("No theories available.");
        }
        for (ReconstructionTheory theory : theories) {
            System.out.println("Name: " + theory.getName());
            System.out.println("Scholar: " + theory.getScholar());
            System.out.println("Date: " + theory.getDate());
            System.out.println("Description: " + theory.getDescription());
            System.out.println("Ordered Versions: " + String.join(", ", theory.getOrderedVersionIds()));
            System.out.println();
        }
    }
}
