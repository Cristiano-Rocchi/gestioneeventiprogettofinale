package cristianorocchi.gestioneeventiprogettofinale.entities;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private int postiTotali;

    @Column(nullable = false)
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id", nullable = false)
    private Utente organizzatore;
}

