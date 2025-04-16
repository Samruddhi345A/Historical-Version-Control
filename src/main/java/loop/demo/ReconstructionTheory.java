package loop.demo;

import java.util.ArrayList;
import java.util.List;

public class ReconstructionTheory {
    private String id;
    private String name;
    private String scholar;
    private String date;
    private String description;
    private List<String> orderedVersionIds; // IDs in evolution order

    // Constructor
    public ReconstructionTheory(String id, String name, String scholar, 
                               String date, String description) {
        this.id = id;
        this.name = name;
        this.scholar = scholar;
        this.date = date;
        this.description = description;
        this.orderedVersionIds = new ArrayList<>();
    }
    
    public void addVersion(String versionId) {
        orderedVersionIds.add(versionId);
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getScholar() { return scholar; }
    public void setScholar(String scholar) { this.scholar = scholar; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getOrderedVersionIds() { return orderedVersionIds; }
    public void setOrderedVersionIds(List<String> orderedVersionIds) { this.orderedVersionIds = orderedVersionIds; }
}
