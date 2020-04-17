package com.google.partnerdictionary.converters.partnerrestriction;

import com.google.partnerdictionary.forms.PartnerRestrictionForm;
import com.google.partnerdictionary.models.PartnerRestriction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PartnerRestrictionToPartnerRestrictionForm implements
    Converter<PartnerRestriction, PartnerRestrictionForm> {

  @Override
  public PartnerRestrictionForm convert(PartnerRestriction partnerRestriction) {
    PartnerRestrictionForm partnerRestrictionForm = new PartnerRestrictionForm();
    partnerRestrictionForm.setPartnerRestrictionId(partnerRestriction.getPartnerRestrictionId());
    partnerRestrictionForm.setPartner(partnerRestriction.getPartner());
    partnerRestrictionForm.setRestrictionType(partnerRestriction.getRestrictionType());
    partnerRestrictionForm.setPartnerRestrictionValue(partnerRestriction.getPartnerRestrictionValue());
    return partnerRestrictionForm;
  }
}
