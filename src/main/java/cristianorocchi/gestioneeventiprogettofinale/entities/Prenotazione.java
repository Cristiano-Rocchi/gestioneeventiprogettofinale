package cristianorocchi.gestioneeventiprogettofinale.entities;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prenotazione", uniqueConstraints = {@UniqueConstraint(columnNames = {"utente_id", "evento_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String dataPrenotazione;
}

