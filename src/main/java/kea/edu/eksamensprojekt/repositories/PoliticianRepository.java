package kea.edu.eksamensprojekt.repositories;

import kea.edu.eksamensprojekt.models.Party;
import kea.edu.eksamensprojekt.models.Politician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliticianRepository extends JpaRepository<Politician, Long> {

    List<Politician> findAllByParty(Party party);
}
