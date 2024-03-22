package evaluation.ec.eval.models;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import evaluation.ec.eval.views.OperationView;
import evaluation.ec.eval.views.UtilisateurView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ChantierView.class, UtilisateurView.class, OperationView.class})
    protected Integer id;

    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @NotNull(message = "Le pseudo est obligatoire")
    @JsonView({ChantierView.class, UtilisateurView.class, OperationView.class})
    protected String pseudo;

    @Length(min= 6, max = 20, message = "Le mot de passe doit avoir entre 3 et 20 caractères")
    @NotNull(message = "Le mot de passe est obligatoire")
    protected String motDePasse;

    @ManyToOne(optional = false)
    @JsonView({ChantierView.class, UtilisateurView.class, OperationView.class})
    protected Role role;
}
