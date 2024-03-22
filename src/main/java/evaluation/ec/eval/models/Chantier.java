package evaluation.ec.eval.models;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.models.Utilisateur;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.OperationView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChantierView.class, OperationView.class})
    protected Integer id;

    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @JsonView({ChantierView.class, OperationView.class})
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;

    @JsonView(ChantierView.class)
    @NotNull(message = "L'adresse est obligatoire")
    protected String adresse;

    @ManyToOne
    @JsonView(ChantierView.class)
    @NotNull(message = "Le proprietaire est obligatoire")
    protected Utilisateur proprietaire;

    @ManyToOne
    @JsonView(ChantierView.class)
    @NotNull(message = "Le directeur est obligatoire")
    protected Utilisateur directeur;

    @OneToMany
    @JsonView(ChantierView.class)
    protected List<Operation> operations;
}
