package ProjetoEmpresa.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Projeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Funcionario> funcionarios = new HashSet<>();
}
