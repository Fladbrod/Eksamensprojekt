package kea.edu.eksamensprojekt.models;

import kea.edu.eksamensprojekt.restcontrollers.Parties;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "politicians")
@Entity
public class Politician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String politicianName;

    @ManyToOne
    private Party party;

}
