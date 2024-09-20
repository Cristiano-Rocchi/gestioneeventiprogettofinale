package cristianorocchi.gestioneeventiprogettofinale.services;


import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //public Utente registraNuovoUtente(Utente nuovoUtente) {
        //utenteRepository.findByEmail(nuovoUtente.getEmail()).ifPresent(u -> {
          //  throw new BadRequestException("L'email " + nuovoUtente.getEmail() + " è già in uso.");
       // });

       // nuovoUtente.setPassword(passwordEncoder.encode(nuovoUtente.getPassword()));
       // return utenteRepository.save(nuovoUtente);
  // }

    public List<Utente> trovaTutti() {
        return utenteRepository.findAll();
    }

   // public Utente trovaPerId(Long id) {
       // return utenteRepository.findById(id)
        //        .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato"));
   // }

    public Utente aggiornaUtente(Long id, Utente utenteAggiornato) {
        Utente utenteEsistente = trovaPerId(id);
        utenteEsistente.setNome(utenteAggiornato.getNome());
        utenteEsistente.setCognome(utenteAggiornato.getCognome());
        utenteEsistente.setUsername(utenteAggiornato.getUsername());
        utenteEsistente.setEmail(utenteAggiornato.getEmail());
        return utenteRepository.save(utenteEsistente);
    }

    public void cancellaUtente(Long id) {
        Utente utente = trovaPerId(id);
        utenteRepository.delete(utente);
    }
}
