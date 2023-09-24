import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servers")
public class ServerController {
    @Autowired
    private ServerRepository serverRepository;

    @GetMapping
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable String id) {
        Optional<Server> server = serverRepository.findById(id);
        return server.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Server> createServer(@RequestBody Server server) {
        Server savedServer = serverRepository.save(server);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedServer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable String id) {
        serverRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findByName")
    public List<Server> findServersByName(@RequestParam String name) {
        return serverRepository.findByNameContaining(name);
    }
}
