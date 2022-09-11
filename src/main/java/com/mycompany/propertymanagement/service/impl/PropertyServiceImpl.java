package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe  = propertyConverter.convertDTOtoEntity(propertyDTO);

        pe = propertyRepository.save(pe);

        PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);

        return dto;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> listPE = (List<PropertyEntity>)propertyRepository.findAll();

        List<PropertyDTO> listPDTO = new ArrayList<>();
        for (PropertyEntity pe: listPE) {
            listPDTO.add(propertyConverter.convertEntityToDTO(pe));
        }
        return listPDTO;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPE = propertyRepository.findById(propertyId);

        PropertyDTO propertyDTO1 = null;
        if (optionalPE.isPresent()) {
            PropertyEntity pe = optionalPE.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());

            propertyRepository.save(pe);
            propertyDTO1 = propertyConverter.convertEntityToDTO(pe);
        }

        return propertyDTO1;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPE = propertyRepository.findById(propertyId);

        PropertyDTO propertyDTO1 = null;
        if (optionalPE.isPresent()) {
            PropertyEntity pe = optionalPE.get();

            pe.setDescription(propertyDTO.getDescription());


            propertyRepository.save(pe);
            propertyDTO1 = propertyConverter.convertEntityToDTO(pe);
        }

        return propertyDTO1;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPE = propertyRepository.findById(propertyId);

        PropertyDTO propertyDTO1 = null;
        if (optionalPE.isPresent()) {
            PropertyEntity pe = optionalPE.get();

            pe.setPrice(propertyDTO.getPrice());


            propertyRepository.save(pe);
            propertyDTO1 = propertyConverter.convertEntityToDTO(pe);
        }

        return propertyDTO1;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
//        return null;
    }
}
