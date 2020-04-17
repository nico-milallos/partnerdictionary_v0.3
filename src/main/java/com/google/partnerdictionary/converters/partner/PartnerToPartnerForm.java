package com.google.partnerdictionary.converters.partner;

import com.google.partnerdictionary.forms.PartnerForm;
import com.google.partnerdictionary.models.Partner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PartnerToPartnerForm implements Converter<Partner, PartnerForm> {

  @Override
  public PartnerForm convert(Partner partner) {
    PartnerForm partnerForm = new PartnerForm();
    partnerForm.setPartnerId(partner.getPartnerId());
    partnerForm.setPartnerCode(partner.getPartnerCode());
    partnerForm.setPartnerName(partner.getPartnerName());
    return partnerForm;
  }
}
