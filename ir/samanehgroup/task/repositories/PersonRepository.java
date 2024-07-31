package ir.samanehgroup.task.repositories;

import ir.samanehgroup.task.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByNationalID(int nationalID);

}
