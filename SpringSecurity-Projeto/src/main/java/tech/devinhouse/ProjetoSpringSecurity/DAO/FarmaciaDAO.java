package tech.devinhouse.ProjetoSpringSecurity.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.ProjetoSpringSecurity.Model.Farmacia;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmaciaDAO {
    private int status_code;
    private String mensagem;

    private Farmacia dados;
}
