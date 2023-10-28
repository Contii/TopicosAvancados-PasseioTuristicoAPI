package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "TB_Passeio")
public class Passeio extends BaseEntity {
   
    @Column(name = "name", length = 140, nullable = false)
    private String name;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "durationInHours", nullable = false)
    private int durationInHours;

    @Column(name = "availableVacancies", nullable = false)
    private int availableVacancies;

    @Column(name = "classification", nullable = false)
    private int classification;

    @ManyToOne
    private Pacote pacote;
}