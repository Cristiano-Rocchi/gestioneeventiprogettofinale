package cristianorocchi.gestioneeventiprogettofinale.repositories;


import cristianorocchi.gestioneeventiprogettofinale.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    //boolean existsByUtenteIdAndEventoId(Long utenteId, Long eventoId);
}

