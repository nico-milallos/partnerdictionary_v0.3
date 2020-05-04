package com.google.partnerdictionary.services;

import com.google.partnerdictionary.converters.restrictiontype.RestrictionTypeFormToRestrictionType;
import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.repositories.RestrictionTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestrictionTypeService implements MainService<RestrictionType, RestrictionTypeForm> {

  private RestrictionTypeRepository restrictionTypeRepository;
  private RestrictionTypeFormToRestrictionType restrictionTypeFormToRestrictionType;

  @Autowired
  public RestrictionTypeService(
      RestrictionTypeRepository restrictionTypeRepository,
      RestrictionTypeFormToRestrictionType restrictionTypeFormToRestrictionType) {
    this.restrictionTypeRepository = restrictionTypeRepository;
    this.restrictionTypeFormToRestrictionType = restrictionTypeFormToRestrictionType;
  }

  @Override
  public List<RestrictionType> getAll() {
    List<RestrictionType> restrictionTypes = new ArrayList<>();
    restrictionTypeRepository.findAll().forEach(restrictionTypes::add);
    return restrictionTypes;
  }

  @Override
  public RestrictionType getById(Integer id) {
    return restrictionTypeRepository.findById(id).orElse(null);
  }

  @Override
  public RestrictionType saveOrUpdate(RestrictionType restrictionType) {
    restrictionTypeRepository.save(restrictionType);
    return  restrictionType;
  }

  @Override
  public void delete(Integer id) {
    restrictionTypeRepository.deleteById(id);
  }

  @Override
  public RestrictionType saveOrUpdateForm(RestrictionTypeForm restrictionTypeForm) {
    RestrictionType savedRestrictionType = saveOrUpdate(restrictionTypeFormToRestrictionType.convert(restrictionTypeForm));

    System.out.println("New Restriction Type added with Id: " + savedRestrictionType.getRestrictionTypeId());
    return savedRestrictionType;
  }

  public void saveAll(List<RestrictionType> restrictionTypes) {
    restrictionTypeRepository.saveAll(restrictionTypes);
  }

  public RestrictionType getByRestrictionTypeName(String restrictionTypeName) {
    return restrictionTypeRepository.findByRestrictionTypeName(restrictionTypeName);
  }
}
