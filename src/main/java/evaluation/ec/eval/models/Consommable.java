package evaluation.ec.eval.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ConsommableView;
import evaluation.ec.eval.views.TacheView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Consommable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({TacheView.class, ConsommableView.class})
    protected Integer id;

    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @JsonView({TacheView.class, ConsommableView.class})
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;
    @ManyToMany
    @JsonView(ConsommableView.class)
    protected List<Tache> pourTaches;
}
