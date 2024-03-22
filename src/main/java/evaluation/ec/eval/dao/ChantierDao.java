package evaluation.ec.eval.dao;

import evaluation.ec.eval.models.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChantierDao extends JpaRepository<Chantier,Integer> {
    List<Chantier> findByDirecteurId(int idRecherche);
}

