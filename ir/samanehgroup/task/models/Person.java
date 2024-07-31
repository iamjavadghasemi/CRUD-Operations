package ir.samanehgroup.task.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private int nationalID;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Address> addresses;

    @OneToOne
    private Person bestFriend;

    public String toString() {
        if(bestFriend != null) {
            return "ID: " + id + " National ID: " + nationalID + " Name: " + firstName + " " + lastName + " Best Friend: " + bestFriend.firstName + " " + bestFriend.lastName + " Addresses: " + showAddresses();
        } else {
            return "ID: " + id + " National ID: " + nationalID + " Name: " + firstName + " " + lastName + " Best Friend: No one. Addresses: " + showAddresses();
        }
    }

    public String showAddresses() {
        if(addresses != null) {
            if(addresses.size() != 0) {
                String info = "";
                int count = 1;
                for(Address i : addresses) {
                    info = info.concat(count + ". " + i.toString() + " ");
                    count++;
                }
                return info;
            }
        }
        return "There is no address.";
    }

}
