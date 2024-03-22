package evaluation.ec.eval.controllers;

import evaluation.ec.eval.dao.OperationDao;
import evaluation.ec.eval.models.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {
    @Autowired
    OperationDao operationDao;

    @GetMapping("/operation/list")
    public List<Operation> liste(){
        return operationDao.findAll();
    }

    @GetMapping("/operation/{id}")
    public ResponseEntity<Operation> get(@PathVariable int id){
        Optional<Operation> optOperation = operationDao.findById(id);

        return optOperation.map(operation -> new ResponseEntity<>(operation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/operation/{id}")
    public ResponseEntity<Operation> delete(@PathVariable int id) {
        Optional<Operation> optOperation = operationDao.findById(id);

        if(optOperation.isPresent()) {
            operationDao.deleteById(id);
            return new ResponseEntity<>(optOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/operation/")
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation operation) {
        operation.setId(null);
        operationDao.save(operation);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PutMapping("/operation/{id}")
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
