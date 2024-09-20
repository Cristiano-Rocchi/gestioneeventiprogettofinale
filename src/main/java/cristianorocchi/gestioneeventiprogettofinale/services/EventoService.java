package cristianorocchi.gestioneeventiprogettofinale.services;



import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.BadRequestException;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.NotFoundException;
import cristianorocchi.gestioneeventiprogettofinale.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento creaEvento(Evento nuovoEvento) {
        if (nuovoEvento.getPostiTotali() <= 0) {
            throw new BadRequestException("Il numero di posti totali deve essere maggiore di zero.");
        }
        nuovoEvento.setPostiDisponibili(nuovoEvento.getPostiTotali());
        return eventoRepository.save(nuovoEvento);
    }

    public List<Evento> trovaTutti() {
        return eventoRepository.findAll();
    }

    public Evento trovaPerId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con id " + id + " non trovato"));
    }

    public Evento aggiornaEvento(Long id, Evento eventoAggiornato) {
        Evento eventoEsistente = trovaPerId(id);
        eventoEsistente.setTitolo(eventoAggiornato.getTitolo());
        eventoEsistente.setDescrizione(eventoAggiornato.getDescrizione());
        eventoEsistente.setData(eventoAggiornato.getData());
        eventoEsistente.setLuogo(eventoAggiornato.getLuogo());
        eventoEsistente.setPostiTotali(eventoAggiornato.getPostiTotali());
        eventoEsistente.setPostiDisponibili(eventoAggiornato.getPostiDisponibili());
        return eventoRepository.save(eventoEsistente);
    }

    public void cancellaEvento(Long id) {
        Evento evento = trovaPerId(id);
        eventoRepository.delete(evento);
    }
}

