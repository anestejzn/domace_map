package rs.ac.uns.ftn.siit.sw442019.graduate.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="regular_user")
@Setter
@Getter
@NoArgsConstructor
public class RegularUser extends User {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new LinkedList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", referencedColumnName = "id")
    private Household household;

    public RegularUser(
            String email, String password, String name, String surname,
            String salt, int failedAttempts, LocalDateTime lockedUntil,
            Role role) {
        super(email, password, name, surname, salt, failedAttempts, lockedUntil, role);
    }

}