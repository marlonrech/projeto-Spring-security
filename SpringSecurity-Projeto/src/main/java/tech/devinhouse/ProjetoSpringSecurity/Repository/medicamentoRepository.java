package tech.devinhouse.ProjetoSpringSecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.ProjetoSpringSecurity.Model.Medicamento;


@Repository
public interface medicamentoRepository extends JpaRepository<Medicamento,Long> {

}
