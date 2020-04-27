package com.google.partnerdictionary.services;

import com.google.partnerdictionary.converters.partner.PartnerFormToPartner;
import com.google.partnerdictionary.forms.PartnerForm;
import com.google.partnerdictionary.models.Partner;
import com.google.partnerdictionary.repositories.PartnerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService implements MainService<Partner, PartnerForm> {

  private PartnerRepository partnerRepository;
  private PartnerFormToPartner partnerFormToPartner;

  @Autowired
  public PartnerService(PartnerRepository partnerRepository,
      PartnerFormToPartner partnerFormToPartner) {
    this.partnerRepository = partnerRepository;
    this.partnerFormToPartner = partnerFormToPartner;
  }

  public List<Partner> findByKeyword(String keyword) {
    return partnerRepository.findByKeyword(keyword);
  }

  @Override
  public List<Partner> getAll() {
    List<Partner> partners = new ArrayList<>();
    partnerRepository.findAll().forEach(partners::add);
    return partners;
  }

  @Override
  public Partner getById(Integer id) {
    return partnerRepository.findById(id).orElse(null);
  }

  @Override
  public Partner saveOrUpdate(Partner partner) {
    partnerRepository.save(partner);
    return partner;
  }

  @Override
  public void delete(Integer id) {
    partnerRepository.deleteById(id);
  }

  @Override
  public Partner saveOrUpdateForm(PartnerForm partnerForm) {
    Partner savedPartner = saveOrUpdate(partnerFormToPartner.convert(partnerForm));

    System.out.println("New Partner added with Id: " + savedPartner.getPartnerId());
    return savedPartner;
  }
}
