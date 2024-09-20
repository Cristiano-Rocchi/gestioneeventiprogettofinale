package cristianorocchi.gestioneeventiprogettofinale.payloads;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewEventoDTO(
        @NotEmpty(message = "Il titolo dell'evento è obbligatorio")
        @Size(min = 3, max = 20, message = "Il titolo deve essere compreso tra 3 e 20 caratteri")
        String titolo,

        @NotEmpty(message = "La descrizione dell'evento è obbligatoria")
        @Size(min = 5, max = 50, message = "La descrizione deve essere compresa tra 5 e 50 caratteri")
        String descrizione,

        @NotEmpty(message = "La data dell'evento è obbligatoria")
        String data,

        @NotEmpty(message = "Il luogo dell'evento è obbligatorio")
        String luogo,

        @NotNull(message = "Il numero di posti totali è obbligatorio")
        Integer postiTotali
) {}
