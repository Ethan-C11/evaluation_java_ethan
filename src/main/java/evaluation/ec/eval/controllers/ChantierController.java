package evaluation.ec.eval.controllers;

import evaluation.ec.eval.dao.ChantierDao;
import evaluation.ec.eval.models.Chantier;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {
    @Autowired
    ChantierDao chantierDao;

    @GetMapping("/admin/chantier/list")
    @Secured("ROLE_ADMIN")
    public List<Chantier> liste(){
        return chantierDao.findAll();
    }

    @GetMapping("/chantier/list")
    public List<Chantier> liste_chef(){
        return chantierDao.findByDirecteurId(1);
    }


    @GetMapping("/chantier/{id}")
    public ResponseEntity<Chantier> get(@PathVariable int id){
        Optional<Chantier> optChantier = chantierDao.findById(id);

        return optChantier.map(chantier -> new ResponseEntity<>(chantier, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/chantier/{id}")
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optChantier = chantierDao.findById(id);

        if(optChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/chantier/")
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier) {
        chantier.setId(null);
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.OK);
    }

    @PutMapping("/chantier/{id}")
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id) {
        Optional<Chantier> optChantier = chantierDao.findById(id);

        if(optChantier.isPresent()) {
            chantier.setId(id);
            chantierDao.save(chantier);
            return new ResponseEntity<>(chantier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
