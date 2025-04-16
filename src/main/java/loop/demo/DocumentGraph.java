package loop.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DocumentGraph {
    private Map<String, TextVersion> versions;
    private List<Relationship> relationships;
    private Map<String, Annotation> annotations;
    private Map<String, Manuscript> manuscripts;
    private Map<String, ReconstructionTheory> theories;

    public DocumentGraph() {
        versions = new HashMap <>();
        relationships = new ArrayList<>();
        annotations = new HashMap<>();
        manuscripts = new HashMap<>();
        theories = new HashMap<>();
    }

    // Methods to add items
    public void addVersion(TextVersion version) {
        versions.put(version.getId(), version);
    }
    
    public void addRelationship(Relationship relationship) {
        relationships.add(relationship);
    }
    
    public void addAnnotation(Annotation annotation) {
        annotations.put(annotation.getId(), annotation);
    }
    
    public void addManuscript(Manuscript manuscript) {
        manuscripts.put(manuscript.getId(), manuscript);
    }
    
    public void addTheory(ReconstructionTheory theory) {
        theories.put(theory.getId(), theory);
    }

    // Methods to retrieve items
    public TextVersion getVersion(String id) {
        return versions.get(id);
    }
    
    public List<TextVersion> getAllVersions() {
        return new ArrayList<>(versions.values());
    }
    
    // Methods to find related items
    public List<Relationship> getRelationshipsForVersion(String versionId) {
        List<Relationship> result = new ArrayList<>();
        for (Relationship rel : relationships) {
            if (rel.getSource().getId().equals(versionId) || 
                rel.getTarget().getId().equals(versionId)) {
                result.add(rel);
            }
        }
        return result;
    }
    
    public List<Annotation> getAnnotationsForVersion(String versionId) {
        List<Annotation> result = new ArrayList<>();
        for (Annotation anno : annotations.values()) {
            if (anno.getTargetId().equals(versionId) && 
                anno.getTargetType().equals("version")) {
                result.add(anno);
            }
        }
        return result;
    }
    
    public List<ReconstructionTheory> getAllTheories() {
        return new ArrayList<>(theories.values());
    }
}