package ir.samanehgroup.task.services;

import ir.samanehgroup.task.models.*;
import ir.samanehgroup.task.repositories.PersonRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        if(person.getBestFriend() != null) {
            Optional<Person> friend = personRepository.findById(person.getBestFriend().getId());
            if(friend.isPresent()) {
                person.setBestFriend(friend.get());
                friend.get().setBestFriend(person);
            } else {
                throw new NoSuchElementException("There is no person with id = " + person.getBestFriend().getId());
            }
        }
        if(person.getAddresses() != null) {
            throw new IllegalArgumentException("Your input is wrong!");
        }
        return personRepository.save(person);
    }

    public Person findByNationalId(int nationalID) {
        Person found = personRepository.findByNationalID(nationalID);
        if(found != null) {
            return found;
        } else {
            throw new NoSuchElementException("There is no person with national id = " + nationalID);
        }
    }

    public Person update(Person person) {
        Optional<Person> old = personRepository.findById(person.getId());
        if(old.isPresent()) {
            old.get().setFirstName(person.getFirstName());
            old.get().setLastName(person.getLastName());
            if(person.getBestFriend() != null) {
                Optional<Person> friend = personRepository.findById(person.getBestFriend().getId());
                if(friend.isPresent()) {
                    old.get().setBestFriend(friend.get());
                    friend.get().setBestFriend(old.get());
                } else {
                    throw new NoSuchElementException("There is no person with id = " + person.getBestFriend().getId());
                }
            }
            return old.get();
        } else {
            throw new NoSuchElementException("There is no person with id = " + person.getId());
        }
    }

    public void delete(int nationalID) {
        Person deleted = personRepository.findByNationalID(nationalID);
        if(deleted != null) {
            List<Address> list = deleted.getAddresses();
            if(list != null) {
                for(Address i : list) {
                    i.setOwner(null);
                }
            }
            if(deleted.getBestFriend() != null) {
                deleted.getBestFriend().setBestFriend(null);
            }
            personRepository.delete(deleted);
        } else {
            throw new NoSuchElementException("There is no person with national id = " + nationalID);
        }
    }

}
