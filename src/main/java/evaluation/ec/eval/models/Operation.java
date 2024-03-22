package evaluation.ec.eval.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.OperationView;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChantierView.class, OperationView.class})
    protected Integer id;

    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @JsonView({ChantierView.class, OperationView.class})
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;

    @ManyToOne
    @JsonView(OperationView.class)
    protected Chantier chantier;

    @JsonView({ChantierView.class, OperationView.class})
    protected Date date;

    @OneToMany
    @JsonView(OperationView.class)
    protected List<Tache> tache;

    @ManyToOne
    @JsonView(OperationView.class)
    protected Utilisateur ouvrier;
}
