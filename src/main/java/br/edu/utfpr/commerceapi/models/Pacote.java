package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "TB_Pacote")
public class Pacote extends BaseEntity {

    @Column(name = "name", length = 140, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "durationInDays", nullable = false)
    private int durationInDays;

    @Column(name = "beginDate", nullable = false)
    private LocalDate beginDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "availableVacancies", nullable = false)
    private int availableVacancies;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Passeio> passeios = new ArrayList<>();
}