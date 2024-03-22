package evaluation.ec.eval.controllers;

import evaluation.ec.eval.dao.RoleDao;
import evaluation.ec.eval.models.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    RoleDao roleDao;

    @GetMapping("/admin/role/list")
    @Secured("ROLE_ADMIN")
    public List<Role> liste(){
        return roleDao.findAll();
    }

    @GetMapping("/admin/role/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> get(@PathVariable int id){
        Optional<Role> optRole = roleDao.findById(id);

        return optRole.map(role -> new ResponseEntity<>(role, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/admin/role/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> delete(@PathVariable int id) {
        Optional<Role> optRole = roleDao.findById(id);

        if(optRole.isPresent()) {
            roleDao.deleteById(id);
            return new ResponseEntity<>(optRole.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/role/")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> create(@RequestBody @Valid Role role) {
        role.setId(null);
        roleDao.save(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/admin/role/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Role> update(@RequestBody @Valid Role role, @PathVariable int id) {
        Optional<Role> optRole = roleDao.findById(id);

        if(optRole.isPresent()) {
            role.setId(id);
            roleDao.save(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
