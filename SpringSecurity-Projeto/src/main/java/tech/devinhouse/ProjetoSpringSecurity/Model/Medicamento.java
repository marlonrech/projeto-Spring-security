package tech.devinhouse.ProjetoSpringSecurity.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="medicamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome_medicamento;
    private String nome_laboratorio;
    private String dosagem;
    private String desc_medicamento;
    private Double preco_unitario;

    private String tipo_medicamento;
}
