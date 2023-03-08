package tech.devinhouse.ProjetoSpringSecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.devinhouse.ProjetoSpringSecurity.Model.Usuario;


@Repository
public interface usuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmailAndSenha(String email, String senha);

    @Query("select u from Usuario u where u.email = ?1")
    Usuario findUserByLogin(String email);

}
