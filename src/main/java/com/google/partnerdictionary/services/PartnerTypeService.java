package com.google.partnerdictionary.services;

import com.google.partnerdictionary.converters.partnertype.PartnerTypeFormToPartnerType;
import com.google.partnerdictionary.forms.PartnerTypeForm;
import com.google.partnerdictionary.models.PartnerType;
import com.google.partnerdictionary.repositories.PartnerTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerTypeService implements MainService<PartnerType, PartnerTypeForm> {

  private PartnerTypeRepository partnerTypeRepository;
  private PartnerTypeFormToPartnerType partnerTypeFormToPartnerType;

  @Autowired
  public PartnerTypeService(
      PartnerTypeRepository partnerTypeRepository,
      PartnerTypeFormToPartnerType partnerTypeFormToPartnerType) {
    this.partnerTypeRepository = partnerTypeRepository;
    this.partnerTypeFormToPartnerType = partnerTypeFormToPartnerType;
  }

  @Override
  public List<PartnerType> getAll() {
    List<PartnerType> partnerTypes = new ArrayList<>();
    partnerTypeRepository.findAll().forEach(partnerTypes::add);
    return partnerTypes;
  }

  @Override
  public PartnerType getById(Integer id) {
    return partnerTypeRepository.findById(id).orElse(null);
  }

  @Override
  public PartnerType saveOrUpdate(PartnerType partnerType) {
    partnerTypeRepository.save(partnerType);
    return partnerType;
  }

  @Override
  public void delete(Integer id) {
    partnerTypeRepository.deleteById(id);
  }

  @Override
  public PartnerType saveOrUpdateForm(PartnerTypeForm partnerTypeForm) {
    PartnerType savedPartnerType = saveOrUpdate(partnerTypeFormToPartnerType.convert(partnerTypeForm));

    System.out.println("New Partner Type added with Id: " + savedPartnerType.getPartnerTypeId());
    return savedPartnerType;
  }

  public void saveAll(List<PartnerType> partnerTypes) {
    partnerTypeRepository.saveAll(partnerTypes);
  }
}
