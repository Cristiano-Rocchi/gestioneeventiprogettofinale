package cristianorocchi.gestioneeventiprogettofinale.services;

import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.BadRequestException;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.NotFoundException;
import cristianorocchi.gestioneeventiprogettofinale.payloads.NewUtenteDTO;
import cristianorocchi.gestioneeventiprogettofinale.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Utente> trovaTuttiPageable(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }

    public Utente trovaPerId(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + id + " non trovato."));
    }

    public Utente salva(Utente utente) {
        // Verifica se esiste un utente con la stessa email
        Optional<Utente> utenteEsistente = utenteRepository.findByEmail(utente.getEmail());
        if (utenteEsistente.isPresent()) {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso.");
        }

        // Criptografa la password prima di salvarla
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        return utenteRepository.save(utente);
    }

    public void cancella(Long id) {
        Utente utente = trovaPerId(id);
        utenteRepository.delete(utente);
    }

    public Utente aggiornaDaDTO(Long utenteId, NewUtenteDTO newUtenteDTO) {
        Utente utenteEsistente = trovaPerId(utenteId);
        utenteEsistente.setNome(newUtenteDTO.nome());
        utenteEsistente.setCognome(newUtenteDTO.cognome());
        utenteEsistente.setEmail(newUtenteDTO.email());
        utenteEsistente.setUsername(newUtenteDTO.username());
        utenteEsistente.setPassword(passwordEncoder.encode(newUtenteDTO.password()));

        return utenteRepository.save(utenteEsistente);
    }

    public Utente trovaPerEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Dipendente con email " + email + " non trovato"));
    }
}
