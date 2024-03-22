package evaluation.ec.eval.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.ConsommableView;
import evaluation.ec.eval.views.TacheView;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({TacheView.class, ConsommableView.class})
    protected Integer id;

    @JsonView({TacheView.class, ConsommableView.class})
    protected String nom;

    @JsonView(TacheView.class)
    protected Float tempsRealisation;
    @ManyToMany
    protected List<Consommable> consommables;
}
