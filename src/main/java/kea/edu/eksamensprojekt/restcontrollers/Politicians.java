package kea.edu.eksamensprojekt.restcontrollers;

import kea.edu.eksamensprojekt.models.Politician;
import kea.edu.eksamensprojekt.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Politicians {

    @Autowired
    PoliticianRepository politicianRepository;

    @GetMapping("/politicians")
    public List<Politician> getPoliticians() {
        return politicianRepository.findAll();
    }

    @GetMapping("/politicians/{id}")
    public Politician getPoliticianById(@PathVariable Long id) {
        return politicianRepository.findById(id).get();
    }

    @PostMapping("/politicians")
    public Politician addPolitician(@RequestBody Politician newPolitician) {
        return politicianRepository.save(newPolitician);
    }

    @PatchMapping("/politicians/{id}")
    public String patchPoliticianById(@PathVariable Long id, @RequestBody Politician politicianToUpdateWith) {
        return politicianRepository.findById(id).map(foundPolitician -> {
            if (politicianToUpdateWith.getPoliticianName() != null) foundPolitician.setPoliticianName(politicianToUpdateWith.getPoliticianName());
            if (politicianToUpdateWith.getParty() != null) foundPolitician.setParty(politicianToUpdateWith.getParty());

            politicianRepository.save(foundPolitician);
            return "Politician was updated";
        }).orElse("Could not find politician");
    }

    @DeleteMapping("/politicians/{id}")
    public void deletePoliticianById(@PathVariable Long id) {
        politicianRepository.deleteById(id);
    }
}
