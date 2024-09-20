package cristianorocchi.gestioneeventiprogettofinale.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 20, message = "Il nome deve essere compreso tra 2 e 20 caratteri")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 2, max = 20, message = "Il cognome deve essere compreso tra 2 e 20 caratteri")
        String cognome,

        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 20, message = "L'username deve essere compreso tra 3 e 20 caratteri")
        String username,

        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 6, max=30, message = "La password deve essere compresa tra 6 e 30 caratteri")
        String password


) {}
