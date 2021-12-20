package kea.edu.eksamensprojekt.restcontrollers;

import kea.edu.eksamensprojekt.models.Party;
import kea.edu.eksamensprojekt.models.Politician;
import kea.edu.eksamensprojekt.repositories.PartyRepository;
import kea.edu.eksamensprojekt.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Parties {

    @Autowired
    PoliticianRepository politicianRepository;

    @Autowired
    PartyRepository partyRepository;

    @GetMapping("/parties")
    public List<Party> getParties() {
        return partyRepository.findAll();
    }

    @GetMapping("/parties/{id}")
    public Party getPartyById(@PathVariable Long id) {
        return partyRepository.findById(id).get();
    }

    @GetMapping("parties/politicians/{id}")
    public List<Politician> getPoliticiansByPartyId(@PathVariable Long id) {
        Party party = partyRepository.findById(id).get();
        return politicianRepository.findAllByParty(party);
    }

    @PostMapping("/parties")
    public Party addParty(@RequestBody Party newParty) {
        return partyRepository.save(newParty);
    }

    @PatchMapping("/parties/{id}")
    public String patchPartyById(@PathVariable Long id, @RequestBody Party partyToUpdateWith) {
        return partyRepository.findById(id).map(foundParty -> {
            if (partyToUpdateWith.getPartyName() != null) foundParty.setPartyName(partyToUpdateWith.getPartyName());

            partyRepository.save(foundParty);
            return "Party was updated";
        }).orElse("Could not find party");
    }

    @DeleteMapping("/parties/{id}")
    public void deletePartyById(@PathVariable Long id) {
        partyRepository.deleteById(id);
    }
}
