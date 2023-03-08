package tech.devinhouse.ProjetoSpringSecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.ProjetoSpringSecurity.Model.Endereco;

@Repository
public interface enderecoRepository extends JpaRepository<Endereco,Long> {

}
