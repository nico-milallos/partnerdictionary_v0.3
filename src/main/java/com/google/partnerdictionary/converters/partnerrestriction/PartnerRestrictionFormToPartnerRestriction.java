package com.google.partnerdictionary.converters.partnerrestriction;

import com.google.partnerdictionary.forms.PartnerRestrictionForm;
import com.google.partnerdictionary.models.PartnerRestriction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PartnerRestrictionFormToPartnerRestriction implements Converter<PartnerRestrictionForm, PartnerRestriction> {

  @Override
  public PartnerRestriction convert(PartnerRestrictionForm partnerRestrictionForm) {
    PartnerRestriction partnerRestriction = new PartnerRestriction();
    if (partnerRestrictionForm.getPartnerRestrictionId() != null && !StringUtils.isEmpty(partnerRestrictionForm.getPartnerRestrictionId())) {
      partnerRestriction.setPartnerRestrictionId(new Integer(partnerRestrictionForm.getPartnerRestrictionId()));
    }
    partnerRestriction.setPartner(partnerRestrictionForm.getPartner());
    partnerRestriction.setRestrictionType(partnerRestrictionForm.getRestrictionType());
    partnerRestriction.setPartnerRestrictionValue(partnerRestrictionForm.getPartnerRestrictionValue());
    return partnerRestriction;
  }
}
