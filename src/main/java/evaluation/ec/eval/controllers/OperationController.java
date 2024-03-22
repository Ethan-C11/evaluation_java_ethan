package evaluation.ec.eval.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import evaluation.ec.eval.dao.OperationDao;
import evaluation.ec.eval.dao.UtilisateurDao;
import evaluation.ec.eval.models.Chantier;
import evaluation.ec.eval.models.Operation;
import evaluation.ec.eval.models.Utilisateur;
import evaluation.ec.eval.views.ConsommableView;
import evaluation.ec.eval.views.OperationView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {
    @Autowired
    OperationDao operationDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping("/admin/operation/list")
    @Secured("ROLE_ADMIN")
    @JsonView(OperationView.class)
    public List<Operation> liste(){
        return operationDao.findAll();
    }

    @GetMapping("/operation/list")
    @Secured({"ROLE_ADMIN","ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<List<Operation>> liste_employe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<Utilisateur> optUtilisateur = utilisateurDao.findByPseudo(currentPrincipalName);
        List<Operation> listOpe = new ArrayList<>();
        return optUtilisateur.map(utilisateur -> new ResponseEntity<>(operationDao.findByOuvrier(utilisateur), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/operation/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> get(@PathVariable int id){
        Optional<Operation> optOperation = operationDao.findById(id);

        return optOperation.map(operation -> new ResponseEntity<>(operation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/operation/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> delete(@PathVariable int id) {
        Optional<Operation> optOperation = operationDao.findById(id);

        if(optOperation.isPresent()) {
            operationDao.deleteById(id);
            return new ResponseEntity<>(optOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/operation/")
    @Secured("ROLE_ADMIN")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation operation) {
        operation.setId(null);
        operationDao.save(operation);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PutMapping("/operation/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> update(@RequestBody @Valid Operation operation, @PathVariable int id) {
        Optional<Operation> optOperation = operationDao.findById(id);

        if(optOperation.isPresent()) {
            operation.setId(id);
            operationDao.save(operation);
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
