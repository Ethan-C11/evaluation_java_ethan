package evaluation.ec.eval.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotNull(message = "Le pseudo est obligatoire")
    protected String pseudo;

    @NotNull(message = "Le mot de passe est obligatoire")
    protected String motDePasse;

    @ManyToOne(optional = false)
    protected Role role;
}
