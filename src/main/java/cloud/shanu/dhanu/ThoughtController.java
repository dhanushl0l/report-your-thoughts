package cloud.shanu.dhanu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ThoughtController {

    private final ThoughtRepository repo;

    public ThoughtController(ThoughtRepository repo) {
        this.repo = repo;
    }

    // health check
    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    // list all
    @GetMapping("/get")
    public List<Thought> getAll() {
        return repo.findAll();
    }

    @PostMapping("/send")
    public Thought create(@RequestBody Map<String,String> body) {
        String name = body.get("name");
        String message = body.get("message");
        Thought t = new Thought(name, message);
        return repo.save(t);
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody Map<String,String> body) {
        Long id = Long.valueOf(body.get("id"));
        String message = body.get("message");
        return repo.findById(id)
                .map(t -> {
                    t.setText(message);
                    repo.save(t);
                    return ResponseEntity.ok("Updated");
                })
                .orElse(ResponseEntity.status(404).body("Not found"));
    }


    // delete by id
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(404).body("Not found");
    }
}


