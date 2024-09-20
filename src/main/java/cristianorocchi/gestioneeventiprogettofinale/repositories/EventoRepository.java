package cristianorocchi.gestioneeventiprogettofinale.repositories;


import cristianorocchi.gestioneeventiprogettofinale.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}

