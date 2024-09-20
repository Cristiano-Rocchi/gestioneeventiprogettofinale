package cristianorocchi.gestioneeventiprogettofinale.controller;


import cristianorocchi.gestioneeventiprogettofinale.payloads.UserLoginDTO;
import cristianorocchi.gestioneeventiprogettofinale.payloads.UserLoginRespDTO;
import cristianorocchi.gestioneeventiprogettofinale.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginRespDTO login(@RequestBody UserLoginDTO loginDTO) {
        return new UserLoginRespDTO(authService.checkCredentialsAndGenerateToken(loginDTO));
    }
}

