package com.google.partnerdictionary.services;

import com.google.partnerdictionary.converters.restriction.RestrictionFormToRestriction;
import com.google.partnerdictionary.forms.RestrictionForm;
import com.google.partnerdictionary.models.Restriction;
import com.google.partnerdictionary.repositories.RestrictionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestrictionService implements MainService<Restriction, RestrictionForm> {

  private RestrictionRepository restrictionRepository;
  private RestrictionFormToRestriction restrictionFormToRestriction;

  @Autowired
  public RestrictionService(
      RestrictionRepository restrictionRepository,
      RestrictionFormToRestriction restrictionFormToRestriction) {
    this.restrictionRepository = restrictionRepository;
    this.restrictionFormToRestriction = restrictionFormToRestriction;
  }

  @Override
  public List<Restriction> getAll() {
    List<Restriction> restrictions = new ArrayList<>();
    restrictionRepository.findAll().forEach(restrictions::add);
    return restrictions;
  }

  @Override
  public Restriction getById(Integer id) {
    return restrictionRepository.findById(id).orElse(null);
  }

  @Override
  public Restriction saveOrUpdate(Restriction restriction) {
    restrictionRepository.save(restriction);
    return  restriction;
  }

  @Override
  public void delete(Integer id) {
    restrictionRepository.deleteById(id);
  }

  @Override
  public Restriction saveOrUpdateForm(RestrictionForm restrictionForm) {
    Restriction savedRestriction = saveOrUpdate(restrictionFormToRestriction.convert(restrictionForm));

    System.out.println("New Restriction added with Id: " + savedRestriction.getRestrictionId());
    return savedRestriction;
  }

  public void saveAll(List<Restriction> restrictions) {
    restrictionRepository.saveAll(restrictions);
  }
}
