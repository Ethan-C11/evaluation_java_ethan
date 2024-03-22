package evaluation.ec.eval.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.dao.*;
import evaluation.ec.eval.models.*;
import evaluation.ec.eval.security.JwtUtils;
import evaluation.ec.eval.views.ChantierView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {
    @Autowired
    ChantierDao chantierDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/admin/chantier/list")
    @Secured("ROLE_ADMIN")
    @JsonView(ChantierView.class)
    public List<Chantier> liste(){
        return chantierDao.findAll();
    }

    @GetMapping("/chantier/list")
    @JsonView(ChantierView.class)
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    public ResponseEntity<List<Chantier>> liste_chef() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> optUtilisateur = utilisateurDao.findByPseudo(currentPrincipalName);
        return optUtilisateur.map(utilisateur -> new ResponseEntity<>(chantierDao.findByDirecteurId(utilisateur.getId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @GetMapping("/chantier/temps/{id}")
    public ResponseEntity<Float> tempsChantier(@PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> optUtilisateur = utilisateurDao.findByPseudo(currentPrincipalName);
        Optional<Chantier> optChantier = chantierDao.findById(id);

        if(optChantier.isPresent() && optUtilisateur.isPresent()){
            if(optChantier.get().getDirecteur() == optUtilisateur.get()){
                //Renvoi le temps
                float tempsTotal = 0F;
                List<Tache> TacheList = new ArrayList<>();
                List<Operation> OperationList = optChantier.get().getOperations();
                for(Operation operation: OperationList ){
                    TacheList.addAll(operation.getTache());
                }
                for(Tache tache : TacheList){
                    tempsTotal+=tache.getTempsRealisation();
                }
                return new ResponseEntity<>(tempsTotal, HttpStatus.OK);
            }
            // acces refuse
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        //not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chantier/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id){
        Optional<Chantier> optChantier = chantierDao.findById(id);

        return optChantier.map(chantier -> new ResponseEntity<>(chantier, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/chantier/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optChantier = chantierDao.findById(id);

        if(optChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/chantier/")
    @Secured("ROLE_ADMIN")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier) {
        chantier.setId(null);
        chantierDao.save(chantier);
        return new ResponseEntity<>(chantier, HttpStatus.OK);
    }

    @PutMapping("/chantier/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(ChantierView.class)
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
