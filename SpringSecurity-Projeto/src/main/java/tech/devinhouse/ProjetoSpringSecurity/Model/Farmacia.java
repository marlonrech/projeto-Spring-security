package tech.devinhouse.ProjetoSpringSecurity.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="farmacia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Farmacia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razao_social;
    private String cnpj;
    private String nome_fantasia;
    private String email;

    private String telefone;
    private String celular;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="id_endereco", referencedColumnName = "id")
    private Endereco endereco;

}
