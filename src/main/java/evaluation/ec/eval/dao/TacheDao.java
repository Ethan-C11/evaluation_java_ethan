package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TacheDao extends JpaRepository<Tache,Integer> {

}
