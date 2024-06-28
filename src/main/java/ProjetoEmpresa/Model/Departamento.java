package ProjetoEmpresa.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL) /*gpt*/
   /*@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER) prof*/
    private List<Funcionario> funcionarios;

}
