package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByPseudo(String pseudo);
}
