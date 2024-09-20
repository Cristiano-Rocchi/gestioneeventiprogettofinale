package cristianorocchi.gestioneeventiprogettofinale.payloads;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewPrenotazioneDTO(

        @NotNull(message = "ID dell'utente è obbligatorio")
        Long utenteId,

        @NotNull(message = "ID dell'evento è obbligatorio")
        Long eventoId,

        @NotEmpty(message = "La data di prenotazione è obbligatoria")
        String dataPrenotazione,

        @Size(max = 200, message = "Le note possono avere un massimo di 200 caratteri")
        String note
) {}
