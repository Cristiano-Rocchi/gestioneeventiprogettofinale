package cristianorocchi.gestioneeventiprogettofinale.services;


import cristianorocchi.gestioneeventiprogettofinale.entities.Utente;
import cristianorocchi.gestioneeventiprogettofinale.exceptions.UnauthorizedException;
import cristianorocchi.gestioneeventiprogettofinale.payloads.UserLoginDTO;
import cristianorocchi.gestioneeventiprogettofinale.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String checkCredentialsAndGenerateToken(UserLoginDTO loginDTO) {
        Utente utente = utenteService.trovaPerEmail(loginDTO.email());

        if (passwordEncoder.matches(loginDTO.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }
}

