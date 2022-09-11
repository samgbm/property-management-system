package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class PropertyController {
    @Value("${pms.dummy}")
    private String dummy;
    @Autowired
    private PropertyService propertyService;

    @PostMapping(path = "/hello")
    public String sayHello(){
        return "Hello!";
    }

    @PostMapping(path = "/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        PropertyDTO pDTO = propertyService.saveProperty(propertyDTO);

        ResponseEntity<PropertyDTO> re = new ResponseEntity<PropertyDTO>(pDTO, HttpStatus.CREATED);

        return re;
    }

    @GetMapping(path = "/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){

        System.out.println(dummy);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();

        ResponseEntity<List<PropertyDTO>> re = new ResponseEntity<>(propertyList, HttpStatus.OK);

        return re;
    }

    @PutMapping(path = "/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO propertyDTO1 = propertyService.updateProperty(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(propertyDTO1, HttpStatus.CREATED);

        return re;
    }

    @PatchMapping(path = "/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO propertyDTO1 = propertyService.updatePropertyDescription(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(propertyDTO1, HttpStatus.OK);

        return re;
    }

    @PatchMapping(path = "/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId){
        PropertyDTO propertyDTO1 = propertyService.updatePropertyPrice(propertyDTO, propertyId);

        ResponseEntity<PropertyDTO> re = new ResponseEntity<>(propertyDTO1, HttpStatus.OK);

        return re;
    }

    @DeleteMapping(path = "/properties/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long propertyId) {

        String ba = "Se borro la propiedad con id: " + propertyId;

        propertyService.deleteProperty(propertyId);
        ResponseEntity<String> re = new ResponseEntity<>(ba, HttpStatus.NO_CONTENT);

        return re;
    }



}
