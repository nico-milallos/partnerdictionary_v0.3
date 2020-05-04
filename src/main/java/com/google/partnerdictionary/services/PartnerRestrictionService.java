package com.google.partnerdictionary.services;

import com.google.partnerdictionary.converters.partnerrestriction.PartnerRestrictionFormToPartnerRestriction;
import com.google.partnerdictionary.forms.PartnerRestrictionForm;
import com.google.partnerdictionary.models.PartnerRestriction;
import com.google.partnerdictionary.repositories.PartnerRestrictionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerRestrictionService implements MainService<PartnerRestriction, PartnerRestrictionForm>{

  private PartnerRestrictionRepository partneRestrictionRepository;
  private PartnerRestrictionFormToPartnerRestriction partnerRestrictionFormToPartnerRestriction;

  @Autowired
  public PartnerRestrictionService(
      PartnerRestrictionRepository partneRestrictionRepository,
      PartnerRestrictionFormToPartnerRestriction partnerRestrictionFormToPartnerRestriction) {
    this.partneRestrictionRepository = partneRestrictionRepository;
    this.partnerRestrictionFormToPartnerRestriction = partnerRestrictionFormToPartnerRestriction;
  }

  @Override
  public List<PartnerRestriction> getAll() {
    List<PartnerRestriction> partnerRestrictions = new ArrayList<>();
    partneRestrictionRepository.findAll().forEach(partnerRestrictions::add);
    return partnerRestrictions;
  }

  @Override
  public PartnerRestriction getById(Integer id) {
    return partneRestrictionRepository.findById(id).orElse(null);
  }

  @Override
  public PartnerRestriction saveOrUpdate(PartnerRestriction partnerRestriction) {
    partneRestrictionRepository.save(partnerRestriction);
    return partnerRestriction;
  }

  @Override
  public void delete(Integer id) {
    partneRestrictionRepository.deleteById(id);
  }

  @Override
  public PartnerRestriction saveOrUpdateForm(PartnerRestrictionForm partnerRestrictionForm) {
    PartnerRestriction savedPartnerRestriction = saveOrUpdate(partnerRestrictionFormToPartnerRestriction.convert(partnerRestrictionForm));

    System.out.println("New Partner Restriction added with Id: " + savedPartnerRestriction.getPartnerRestrictionId());
    return savedPartnerRestriction;
  }

  public List<PartnerRestriction> findCabinTypesByPartnerId(String partnerId) {
    return partneRestrictionRepository.findCabinTypesByPartnerId(partnerId);
  }

  public List<PartnerRestriction> findLinkTypesByPartnerId(String partnerId) {
    return partneRestrictionRepository.findLinkTypesByPartnerId(partnerId);
  }

  public List<PartnerRestriction> findTripTypesByPartnerId(String partnerId) {
    return partneRestrictionRepository.findTripTypesByPartnerId(partnerId);
  }

  public List<PartnerRestriction> findOtherRestrictionsByPartnerId(String partnerId) {
    return partneRestrictionRepository.findOtherRestrictionsByPartnerId(partnerId);
  }

  public void saveAll(List<PartnerRestriction> partnerRestrictions) {
    partneRestrictionRepository.saveAll(partnerRestrictions);
  }
}
