package kea.edu.eksamensprojekt.repositories;

import kea.edu.eksamensprojekt.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartyRepository extends JpaRepository<Party, Long> {

}
