package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_Avaliacao")
public class Avaliacao extends BaseEntity {

  @Column(name = "titulo", length = 100, nullable = false)
  private String titulo;

  @Column(name = "comentario")
  private String comentario;

  @Column(name = "nota", nullable = false)
  private int nota;

  @Column(name = "dataAvaliacao", nullable = false)
  private LocalDate dataAvaliacao;

  @ManyToOne
  @JoinColumn(name = "passeio_id")
  private Pacote passeio;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Person cliente;
}