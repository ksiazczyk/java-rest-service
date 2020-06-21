package com.grzegorz.ksiazczyk.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    @GetMapping("/devices")
    public ResponseEntity<Object> getAllDevices() {
        return ResponseEntity.ok(deviceRepository.findAll());
    }

    @GetMapping("/devices/{id}")
    public ResponseEntity<Object> getDevice(@PathVariable("id") Long id) {
        Optional<Device> optionalDevice = deviceRepository.findById(id);
        return optionalDevice.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/devices")
    public ResponseEntity<Object> createDevice(@RequestBody Device device) {
        Device savedDevice = deviceRepository.save(device);
        return ResponseEntity.ok(savedDevice);
    }

}
