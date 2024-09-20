package cristianorocchi.gestioneeventiprogettofinale.controller;


import cristianorocchi.gestioneeventiprogettofinale.entities.Prenotazione;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewPrenotazioneDTO;
import cristianorocchi.gestioneeventiprogettofinale.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> trovaTuttePrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneService.trovaTuttePageable(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione creaPrenotazione(@Valid @RequestBody NewPrenotazioneDTO newPrenotazioneDTO) {
        return prenotazioneService.salvaDaDTO(newPrenotazioneDTO);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione trovaPrenotazionePerId(@PathVariable Long prenotazioneId) {
        return prenotazioneService.trovaPerId(prenotazioneId);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione aggiornaPrenotazione(
            @PathVariable Long prenotazioneId,
            @Valid @RequestBody NewPrenotazioneDTO newPrenotazioneDTO) {
        return prenotazioneService.aggiornaDaDTO(prenotazioneId, newPrenotazioneDTO);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaPrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.cancella(prenotazioneId);
    }
}
