package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDao extends JpaRepository<Operation,Integer> {
}
