package evaluation.ec.eval.models;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.models.Utilisateur;
import evaluation.ec.eval.views.ChantierView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @JsonView(ChantierView.class)
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
    protected List<Operation> operations;
}
