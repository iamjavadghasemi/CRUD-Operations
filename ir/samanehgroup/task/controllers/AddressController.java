package ir.samanehgroup.task.controllers;

import ir.samanehgroup.task.models.Address;
import ir.samanehgroup.task.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/Address")
    public ResponseEntity<?> create(@RequestBody Address address) {
        Address done = addressService.create(address);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Address is created successfully!");
        response.put("Address", done.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Address/{postalCode}")
    public ResponseEntity<?> find(@PathVariable int postalCode) {
        Address done = addressService.findByPostalCode(postalCode);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Address is found successfully!");
        response.put("Address", done.toString());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/Address")
    public ResponseEntity<?> update(@RequestBody Address address) {
        Address done = addressService.update(address);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Address is updated successfully!");
        response.put("Address", done.toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/Address/{postalCode}")
    public ResponseEntity<?> delete(@PathVariable int postalCode) {
        addressService.delete(postalCode);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Address is deleted successfully!");
        return ResponseEntity.ok(response);
    }

}
