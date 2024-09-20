package cristianorocchi.gestioneeventiprogettofinale.controller;


import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewEventoDTO;
import cristianorocchi.gestioneeventiprogettofinale.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public Page<Evento> trovaTuttiEventi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoService.trovaTuttiPageable(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento creaEvento(@Valid @RequestBody NewEventoDTO newEventoDTO) {
        Evento evento = mappaDTOaEvento(newEventoDTO);
        return eventoService.salva(evento);
    }

    @GetMapping("/{eventoId}")
    public Evento trovaEventoPerId(@PathVariable Long eventoId) {
        return eventoService.trovaPerId(eventoId);
    }

    @PutMapping("/{eventoId}")
    public Evento aggiornaEvento(@PathVariable Long eventoId, @Valid @RequestBody NewEventoDTO newEventoDTO) {
        Evento eventoEsistente = eventoService.trovaPerId(eventoId);
        aggiornaEventoDaDTO(eventoEsistente, newEventoDTO);
        return eventoService.salva(eventoEsistente);
    }

    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaEvento(@PathVariable Long eventoId) {
        eventoService.cancella(eventoId);
    }

    private Evento mappaDTOaEvento(NewEventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitolo(dto.titolo());
        evento.setDescrizione(dto.descrizione());
        evento.setData(dto.data());
        evento.setLuogo(dto.luogo());
        evento.setPostiTotali(dto.postiTotali());
        evento.setPostiDisponibili(dto.postiTotali());
        return evento;
    }

    private void aggiornaEventoDaDTO(Evento evento, NewEventoDTO dto) {
        evento.setTitolo(dto.titolo());
        evento.setDescrizione(dto.descrizione());
        evento.setData(dto.data());
        evento.setLuogo(dto.luogo());
        evento.setPostiTotali(dto.postiTotali());
        evento.setPostiDisponibili(dto.postiTotali());
    }
}
