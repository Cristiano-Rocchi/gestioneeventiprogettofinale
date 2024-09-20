package cristianorocchi.gestioneeventiprogettofinale.services;


import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.BadRequestException;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.NotFoundException;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewEventoDTO;
import cristianorocchi.gestioneeventiprogettofinale.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Page<Evento> trovaTuttiPageable(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    public Evento trovaPerId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con ID " + id + " non trovato."));
    }

    public Evento salva(Evento evento) {

        if (evento.getTitolo() == null || evento.getTitolo().isEmpty()) {
            throw new BadRequestException("Il titolo è obbligatorio.");
        }
        if (evento.getData() == null || evento.getData().isEmpty()) {
            throw new BadRequestException("La data è obbligatoria.");
        }

        return eventoRepository.save(evento);
    }

    public Evento aggiornaDaDTO(Long eventoId, NewEventoDTO newEventoDTO) {
        Evento eventoEsistente = trovaPerId(eventoId);
        eventoEsistente.setTitolo(newEventoDTO.titolo());
        eventoEsistente.setDescrizione(newEventoDTO.descrizione());
        eventoEsistente.setData(newEventoDTO.data());
        eventoEsistente.setLuogo(newEventoDTO.luogo());
        eventoEsistente.setPostiTotali(newEventoDTO.postiTotali());
        eventoEsistente.setPostiDisponibili(newEventoDTO.postiTotali());

        return eventoRepository.save(eventoEsistente);
    }

    public void cancella(Long id) {
        Evento evento = trovaPerId(id);
        eventoRepository.delete(evento);
    }
}
