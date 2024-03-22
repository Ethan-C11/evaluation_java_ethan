package evaluation.ec.eval.models;

import com.fasterxml.jackson.annotation.JsonView;
import evaluation.ec.eval.views.ChantierView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @Length(min= 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractères")
    @JsonView(ChantierView.class)
    protected String nom;

    protected boolean admin;
}
