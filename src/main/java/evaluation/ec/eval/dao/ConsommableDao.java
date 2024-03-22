package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Consommable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsommableDao extends JpaRepository<Consommable,Integer> {
}
