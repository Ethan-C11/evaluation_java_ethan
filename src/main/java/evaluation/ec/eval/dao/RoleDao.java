package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
