package evaluation.ec.eval.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.dao.TacheDao;
import evaluation.ec.eval.models.Tache;
import evaluation.ec.eval.views.OperationView;
import evaluation.ec.eval.views.TacheView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TacheController {
    @Autowired
    TacheDao tacheDao;

    @GetMapping("/tache/list")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(TacheView.class)
    public List<Tache> liste(){
        return tacheDao.findAll();
    }

    @GetMapping("/tache/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> get(@PathVariable int id){
        Optional<Tache> optTache = tacheDao.findById(id);

        return optTache.map(tache -> new ResponseEntity<>(tache, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/admin/tache/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> delete(@PathVariable int id) {
        Optional<Tache> optTache = tacheDao.findById(id);

        if(optTache.isPresent()) {
            tacheDao.deleteById(id);
            return new ResponseEntity<>(optTache.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/tache/")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> create(@RequestBody @Valid Tache tache) {
        tache.setId(null);
        tacheDao.save(tache);
        return new ResponseEntity<>(tache, HttpStatus.OK);
    }

    @PutMapping("/tache/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(TacheView.class)
    public ResponseEntity<Tache> update(@RequestBody @Valid Tache tache, @PathVariable int id) {
        Optional<Tache> optTache = tacheDao.findById(id);

        if(optTache.isPresent()) {
            tache.setId(id);
            tacheDao.save(tache);
            return new ResponseEntity<>(tache, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
