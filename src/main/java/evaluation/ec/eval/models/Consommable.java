package evaluation.ec.eval.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ConsommableView;
import evaluation.ec.eval.views.TacheView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
public class Consommable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({TacheView.class, ConsommableView.class})
    protected Integer id;

    @JsonView({TacheView.class, ConsommableView.class})
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;
    @ManyToMany
    protected List<Tache> pourTaches;
}
