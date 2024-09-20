package cristianorocchi.gestioneeventiprogettofinale.services;


import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import cristianorocchi.gestioneeventiprogettofinale.entities.Prenotazione;
import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.NotFoundException;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewPrenotazioneDTO;
import cristianorocchi.gestioneeventiprogettofinale.repositories.EventoRepository;
import cristianorocchi.gestioneeventiprogettofinale.repositories.PrenotazioneRepository;
import cristianorocchi.gestioneeventiprogettofinale.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Page<Prenotazione> trovaTuttePageable(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione trovaPerId(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con ID " + id + " non trovata."));
    }

    public Prenotazione salvaDaDTO(NewPrenotazioneDTO newPrenotazioneDTO) {
        Utente utente = utenteRepository.findById(newPrenotazioneDTO.utenteId())
                .orElseThrow(() -> new NotFoundException("Utente con ID " + newPrenotazioneDTO.utenteId() + " non trovato."));

        Evento evento = eventoRepository.findById(newPrenotazioneDTO.eventoId())
                .orElseThrow(() -> new NotFoundException("Evento con ID " + newPrenotazioneDTO.eventoId() + " non trovato."));

        if (prenotazioneRepository.existsByUtenteIdAndEventoId(utente.getId(), evento.getId())) {
            throw new RuntimeException("Prenotazione già esistente per questo utente e evento.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazione.setDataPrenotazione(newPrenotazioneDTO.dataPrenotazione());


        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione aggiornaDaDTO(Long prenotazioneId, NewPrenotazioneDTO newPrenotazioneDTO) {
        Prenotazione prenotazione = trovaPerId(prenotazioneId);

        Utente utente = utenteRepository.findById(newPrenotazioneDTO.utenteId())
                .orElseThrow(() -> new NotFoundException("Utente con ID " + newPrenotazioneDTO.utenteId() + " non trovato."));

        Evento evento = eventoRepository.findById(newPrenotazioneDTO.eventoId())
                .orElseThrow(() -> new NotFoundException("Evento con ID " + newPrenotazioneDTO.eventoId() + " non trovato."));

        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazione.setDataPrenotazione(newPrenotazioneDTO.dataPrenotazione());


        return prenotazioneRepository.save(prenotazione);
    }

    public void cancella(Long id) {
        Prenotazione prenotazione = trovaPerId(id);
        prenotazioneRepository.delete(prenotazione);
    }
}
