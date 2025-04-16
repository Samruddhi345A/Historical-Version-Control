package loop.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WebController {

    private final DocumentGraph graph;

    public WebController(DocumentGraph graph) {
        this.graph = graph;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // This will return the index.html
    }

    @GetMapping("/versions")
    @ResponseBody
    public List<TextVersion> getAllVersions() {
        return graph.getAllVersions();
    }

    @GetMapping("/version/{id}")
    public String viewVersionDetails(@PathVariable String id, Model model) {
        TextVersion version = graph.getVersion(id);
        model.addAttribute("version", version);
        model.addAttribute("annotations", graph.getAnnotationsForVersion(id));
        model.addAttribute("relationships", graph.getRelationshipsForVersion(id));
        return "versionDetails"; // This will return versionDetails.html
    }

    @GetMapping("/relationships/{id}")
    @ResponseBody
    public List<Relationship> getRelationships(@PathVariable String id) {
        return graph.getRelationshipsForVersion(id);
    }

    @GetMapping("/theories")
    @ResponseBody
    public List<ReconstructionTheory> getAllTheories() {
        return graph.getAllTheories();
    }

    @GetMapping("/theory/{id}")
public String viewTheoryDetails(@PathVariable String id, Model model) {
    ReconstructionTheory theory = graph.getAllTheories().stream()
        .filter(t -> t.getId().equals(id))
        .findFirst()
        .orElse(null);
    model.addAttribute("theory", theory);
    return "theoryDetails"; // This will return theoryDetails.html
}
}