package evaluation.ec.eval.controllers;

import evaluation.ec.eval.dao.ConsommableDao;
import evaluation.ec.eval.models.Consommable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsommableController {
    @Autowired
    ConsommableDao consommableDao;

    @GetMapping("/consommable/list")
    public List<Consommable> liste(){
        return consommableDao.findAll();
    }

    @GetMapping("/consommable/{id}")
    public ResponseEntity<Consommable> get(@PathVariable int id){
        Optional<Consommable> optConsommable = consommableDao.findById(id);

        return optConsommable.map(consommable -> new ResponseEntity<>(consommable, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/consommable/{id}")
    public ResponseEntity<Consommable> delete(@PathVariable int id) {
        Optional<Consommable> optConsommable = consommableDao.findById(id);

        if(optConsommable.isPresent()) {
            consommableDao.deleteById(id);
            return new ResponseEntity<>(optConsommable.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/consommable/")
    public ResponseEntity<Consommable> create(@RequestBody @Valid Consommable consommable) {
        consommable.setId(null);
        consommableDao.save(consommable);
        return new ResponseEntity<>(consommable, HttpStatus.OK);
    }

    @PutMapping("/consommable/{id}")
    public ResponseEntity<Consommable> update(@RequestBody @Valid Consommable consommable, @PathVariable int id) {
        Optional<Consommable> optConsommable = consommableDao.findById(id);

        if(optConsommable.isPresent()) {
            consommable.setId(id);
            consommableDao.save(consommable);
            return new ResponseEntity<>(consommable, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
