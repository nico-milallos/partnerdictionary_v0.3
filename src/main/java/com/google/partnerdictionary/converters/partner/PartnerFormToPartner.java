package com.google.partnerdictionary.converters.partner;

import com.google.partnerdictionary.forms.PartnerForm;
import org.springframework.core.convert.converter.Converter;
import com.google.partnerdictionary.models.Partner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PartnerFormToPartner implements Converter<PartnerForm, Partner> {

  @Override
  public Partner convert(PartnerForm partnerForm) {
    Partner partner = new Partner();
    if (partnerForm.getPartnerId() != null  && !StringUtils.isEmpty(partnerForm.getPartnerId())) {
      partner.setPartnerId(new Integer(partnerForm.getPartnerId()));
    }
    partner.setPartnerCode(partnerForm.getPartnerCode());
    partner.setPartnerName(partnerForm.getPartnerName());
    partner.setPartnerType(partnerForm.getPartnerType());
    return partner;
  }
}
