package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Operation;
import evaluation.ec.eval.models.Tache;
import evaluation.ec.eval.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OperationDao extends JpaRepository<Operation,Integer> {
    List<Operation> findByOuvrier(Utilisateur user);
}
