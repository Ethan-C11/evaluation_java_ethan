package evaluation.ec.eval.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.dao.UtilisateurDao;
import evaluation.ec.eval.models.Utilisateur;
import evaluation.ec.eval.security.JwtUtils;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.UtilisateurView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UtilisateurController {
    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody Utilisateur utilisateur) {

        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));

        utilisateurDao.save(utilisateur);

    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur utilisateur) {

        try {

            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    utilisateur.getPseudo(),
                    utilisateur.getMotDePasse()
            )).getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex) {
            return null;
        }

    }

    @GetMapping("/utilisateur/list")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(UtilisateurView.class)
    public List<Utilisateur> liste(){
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_OUVRIER"})
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> get(@PathVariable int id){
        Optional<Utilisateur> optUtilisateur = utilisateurDao.findById(id);

        return optUtilisateur.map(utilisateur -> new ResponseEntity<>(utilisateur, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Utilisateur> delete(@PathVariable int id) {
        Optional<Utilisateur> optUtilisateur = utilisateurDao.findById(id);

        if(optUtilisateur.isPresent()) {
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(optUtilisateur.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/utilisateur/")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Utilisateur> create(@RequestBody @Valid Utilisateur utilisateur) {
        utilisateur.setId(null);
        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @PutMapping("/admin/utilisateur/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Utilisateur> update(@RequestBody @Valid Utilisateur utilisateur, @PathVariable int id) {
        Optional<Utilisateur> optUtilisateur = utilisateurDao.findById(id);

        if(optUtilisateur.isPresent()) {
            utilisateur.setId(id);
            utilisateurDao.save(utilisateur);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
