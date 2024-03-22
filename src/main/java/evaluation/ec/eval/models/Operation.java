package evaluation.ec.eval.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @JsonView(ChantierView.class)
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;

    @ManyToOne
    protected Chantier chantier;

    @JsonView(ChantierView.class)
    protected Date date;

    @OneToMany
    @JsonView(ChantierView.class)
    protected List<Tache> tache;

    @ManyToOne
    @JsonView(ChantierView.class)
    protected Utilisateur ouvrier;
}
