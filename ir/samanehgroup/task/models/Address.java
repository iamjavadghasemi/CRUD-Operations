package ir.samanehgroup.task.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private int postalCode;
    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Person owner;

    public String toString() {
        if(owner == null) {
            return "ID: " + id + " Postal Code: " + postalCode + " Description: " + description + " Owner: No one.";
        } else {
            if(owner.getFirstName() != null && owner.getLastName() != null) {
                return "ID: " + id + " Postal Code: " + postalCode + " Description: " + description + " Owner: " + owner.getFirstName() + " " + owner.getLastName();
            } else {
                return "ID: " + id + " Postal Code: " + postalCode + " Description: " + description + " Owner: The information is unavailable.";
            }
        }
    }

}
