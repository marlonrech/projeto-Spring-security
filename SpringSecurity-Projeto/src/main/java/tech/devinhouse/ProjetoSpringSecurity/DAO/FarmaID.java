package tech.devinhouse.ProjetoSpringSecurity.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.ProjetoSpringSecurity.Model.Farmacia;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmaID {
    private int status_code;
    private String mensagem;
    private Optional<Farmacia> dados;
}
