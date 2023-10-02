package br.edu.utfpr.commerceapi.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//importa todos os lombok
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// ou somente @Data

//jakarta - JPA
@Entity
@Table(name = "TB_Passeio") // caso nao inserir ele coloca o nome da tabela
public class Passeio extends BaseEntity {
   
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "duracaoEmHoras", nullable = false)
    private int duracaoEmHoras;

    @Column(name = "vagasDisponiveis", nullable = false)
    private int vagasDisponiveis;

    @Column(name = "classificacao", nullable = false)
    private int classificacao;

    //qual pacote inclui este passeio
    @ManyToOne
    @JoinColumn(name = "pacote_id") //pacote_id é a chave estrangeira importada da tabela Pacote
    private Pacote pacote; //trocar por UUID?
}