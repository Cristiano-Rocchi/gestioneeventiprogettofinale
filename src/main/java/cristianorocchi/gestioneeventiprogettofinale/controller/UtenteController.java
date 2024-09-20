package cristianorocchi.gestioneeventiprogettofinale.controller;

import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewUtenteDTO;
import cristianorocchi.gestioneeventiprogettofinale.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> trovaTuttiUtenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteService.trovaTuttiPageable(pageable);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente creaUtente(@Valid @RequestBody NewUtenteDTO newUtenteDTO) {
        Utente utente = mappaDTOaUtente(newUtenteDTO);
        return utenteService.salva(utente);
    }

    @GetMapping("/{utenteId}")
    public Utente trovaUtentePerId(@PathVariable Long utenteId) {
        return utenteService.trovaPerId(utenteId);
    }

    @PutMapping("/{utenteId}")
    public Utente aggiornaUtente(@PathVariable Long utenteId, @Valid @RequestBody NewUtenteDTO newUtenteDTO) {
        Utente utenteEsistente = utenteService.trovaPerId(utenteId);
        aggiornaUtenteDaDTO(utenteEsistente, newUtenteDTO);
        return utenteService.salva(utenteEsistente);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaUtente(@PathVariable Long utenteId) {
        utenteService.cancella(utenteId);
    }

    private Utente mappaDTOaUtente(NewUtenteDTO dto) {
        Utente utente = new Utente();
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setEmail(dto.email());
        utente.setUsername(dto.username());
        utente.setPassword(dto.password());
        return utente;
    }

    private void aggiornaUtenteDaDTO(Utente utente, NewUtenteDTO dto) {
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setEmail(dto.email());
        utente.setUsername(dto.username());
        utente.setPassword(dto.password());
    }
}
