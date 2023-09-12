package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="privilege")
@Getter
@Setter
@NoArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="privilege_name")
    private String privilegeName;

    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}
