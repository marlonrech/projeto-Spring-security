package tech.devinhouse.ProjetoSpringSecurity.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.devinhouse.ProjetoSpringSecurity.Model.Usuario;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class usuarioDAO {

    private int statusCode;
    private String mensagem;
    private Usuario dados;
}
