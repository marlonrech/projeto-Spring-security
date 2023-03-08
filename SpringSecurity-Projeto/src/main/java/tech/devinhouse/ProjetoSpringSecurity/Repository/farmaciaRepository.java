package tech.devinhouse.ProjetoSpringSecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.ProjetoSpringSecurity.Model.Farmacia;


@Repository
public interface farmaciaRepository extends JpaRepository<Farmacia,Long> {

}
