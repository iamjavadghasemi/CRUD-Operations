package ir.samanehgroup.task.services;

import ir.samanehgroup.task.models.Address;
import ir.samanehgroup.task.repositories.AddressRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address findByPostalCode(int postalCode) {
        Address found = addressRepository.findByPostalCode(postalCode);
        if(found != null) {
            return found;
        } else {
            throw new NoSuchElementException("There is no address with postal code = " + postalCode);
        }
    }

    public Address update(Address address) {
        Optional<Address> old = addressRepository.findById(address.getId());
        if(old.isPresent()) {
            old.get().setDescription(address.getDescription());
            old.get().setOwner(address.getOwner());
            return old.get();
        } else {
            throw new NoSuchElementException("There is no address with id = " + address.getId());
        }
    }

    public void delete(int postalCode) {
        Address deleted = addressRepository.findByPostalCode(postalCode);
        if(deleted != null) {
            if(deleted.getOwner() != null) {
                deleted.setOwner(null);
            }
            addressRepository.delete(deleted);
        } else {
            throw new NoSuchElementException("There is no address with postal code = " + postalCode);
        }
    }

}
