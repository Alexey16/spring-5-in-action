package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.domain.Taco;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepo;

    @Autowired
    protected EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTaco() {
        PageRequest request = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(request).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
