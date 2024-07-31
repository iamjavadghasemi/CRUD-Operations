package ir.samanehgroup.task.repositories;

import ir.samanehgroup.task.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByPostalCode(int postalCode);

}
