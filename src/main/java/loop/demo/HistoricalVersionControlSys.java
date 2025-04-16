package loop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HistoricalVersionControlSys {

    private final DocumentGraph graph;

    public HistoricalVersionControlSys(DocumentGraph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        SpringApplication.run(HistoricalVersionControlSys.class, args);
    }

    @jakarta.annotation.PostConstruct
    private void initializeExampleData() {
        // Add versions
        TextVersion northern = new TextVersion("v1", "Northern Recension", "North India", 
                                              "1500-1700 CE", 90000, 
                                              "More extensive battle descriptions", 0.95);
        
        TextVersion southern = new TextVersion("v2", "Southern Recension", "South India", 
                                              "1600-1800 CE", 95000, 
                                              "Additional philosophical sections", 0.9);
        
        TextVersion bengali = new TextVersion("v3", "Bengali Recension", "Bengal", 
                                             "1700-1800 CE", 92000, 
                                             "Expanded sections on Bhima", 0.85);
        
        TextVersion critical = new TextVersion("v4", "Critical Edition", "Bhandarkar Institute", 
                                              "1919-1966 CE", 88000, 
                                              "Scholarly compilation of core text", 1.0);
        
        graph.addVersion(northern);
        graph.addVersion(southern);
        graph.addVersion(bengali);
        graph.addVersion(critical);
        
        // Add relationships
        Relationship rel1 = new Relationship("r1", northern, southern, "regional variant", 
                                            0.8, "Shares core narrative but with regional variations");
        
        Relationship rel2 = new Relationship("r2", northern, bengali, "regional variant", 
                                            0.7, "Bengali version shows significant unique material");
        
        Relationship rel3 = new Relationship("r3", critical, northern, "derived from", 
                                            0.9, "Critical edition heavily based on northern sources");
        
        graph.addRelationship(rel1);
        graph.addRelationship(rel2);
        graph.addRelationship(rel3);
        
        // Add annotations
        Annotation anno1 = new Annotation("a1", 
            "The Southern Recension contains philosophical material likely added after 1700 CE", 
            "Dr. Sharma", "v2", "version");
        
        Annotation anno2 = new Annotation("a2", 
            "Bengali manuscript traditions show evidence of independent development", 
            "Prof. Das", "v3", "version");
        
        graph.addAnnotation(anno1);
        graph.addAnnotation(anno2);
        
        // Add manuscripts
        Manuscript ms1 = new Manuscript("ms1", "v1", "British Museum", "Sanskrit", "1700 CE", "Good");
        Manuscript ms2 = new Manuscript("ms2", "v2", "National Archives, India", "Sanskrit", "1800 CE", "Fair");
        
        graph.addManuscript(ms1);
        graph.addManuscript(ms2);
        
        // Add theories
        ReconstructionTheory theory1 = new ReconstructionTheory("t1", "Sukthankar's Theory", 
                                                               "V.S. Sukthankar", "1933", 
                                                               "Classic theory of Mahabharata evolution");
        theory1.addVersion("v1"); // First northern
        theory1.addVersion("v2"); // Then southern evolved
        theory1.addVersion("v3"); // Then bengali evolved
        
        graph.addTheory(theory1);
    }
}