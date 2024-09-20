package cristianorocchi.gestioneeventiprogettofinale.services;


import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import cristianorocchi.gestioneeventiprogettofinale.entities.Prenotazione;
import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.BadRequestException;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.NotFoundException;
import cristianorocchi.gestioneeventiprogettofinale.repositories.EventoRepository;
import cristianorocchi.gestioneeventiprogettofinale.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;


    public List<Prenotazione> trovaTutte() {
        return prenotazioneRepository.findAll();
    }


    public Prenotazione prenotaEvento(Utente utente, Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato con ID: " + eventoId));


        if (evento.getPostiDisponibili() <= 0) {
            throw new BadRequestException("Non ci sono più posti disponibili per questo evento");
        }


        if (prenotazioneRepository.existsByUtenteIdAndEventoId(utente.getId(), eventoId)) {
            throw new BadRequestException("Hai già prenotato questo evento");
        }


        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);


        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazione.setDataPrenotazione(LocalDate.now().toString());

        return prenotazioneRepository.save(prenotazione);
    }


    public void cancellaPrenotazione(Long prenotazioneId) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata con ID: " + prenotazioneId));

        Evento evento = prenotazione.getEvento();
        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        eventoRepository.save(evento);

        prenotazioneRepository.delete(prenotazione);
    }
}
