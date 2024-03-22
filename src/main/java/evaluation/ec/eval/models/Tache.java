package evaluation.ec.eval.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.ConsommableView;
import evaluation.ec.eval.views.OperationView;
import evaluation.ec.eval.views.TacheView;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({TacheView.class, ConsommableView.class, OperationView.class})
    protected Integer id;

    @JsonView({TacheView.class, ConsommableView.class, OperationView.class})
    protected String nom;

    @JsonView({TacheView.class, OperationView.class})
    protected Float tempsRealisation;
    @ManyToMany
    @JsonView(TacheView.class)
    protected List<Consommable> consommables;
}
