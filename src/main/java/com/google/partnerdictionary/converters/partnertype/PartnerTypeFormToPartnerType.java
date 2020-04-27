package com.google.partnerdictionary.converters.partnertype;

import com.google.partnerdictionary.forms.PartnerTypeForm;
import com.google.partnerdictionary.models.PartnerType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PartnerTypeFormToPartnerType implements Converter<PartnerTypeForm, PartnerType> {

  @Override
  public PartnerType convert(PartnerTypeForm partnerTypeForm) {
    PartnerType partnerType = new PartnerType();
    if (partnerTypeForm.getPartnerTypeId() != null && !StringUtils.isEmpty(partnerTypeForm.getPartnerTypeId())) {
      partnerType.setPartnerTypeId(new Integer(partnerTypeForm.getPartnerTypeId()));
    }
    partnerType.setPartnerTypeName(partnerTypeForm.getPartnerTypeName());
    return partnerType;
  }
}
