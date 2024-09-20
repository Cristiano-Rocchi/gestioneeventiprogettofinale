package cristianorocchi.gestioneeventiprogettofinale.controller;


import cristianorocchi.gestioneeventiprogettofinale.entities.Prenotazione;
import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;


    @GetMapping
    public List<Prenotazione> trovaTuttePrenotazioni() {
        return prenotazioneService.trovaTutte();
    }


    @PostMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('UTENTE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione prenotaEvento(@RequestAttribute Utente utente, @PathVariable Long eventoId) {
        return prenotazioneService.prenotaEvento(utente, eventoId);
    }


    @DeleteMapping("/{prenotazioneId}")
    @PreAuthorize("hasAuthority('UTENTE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaPrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.cancellaPrenotazione(prenotazioneId);
    }
}

