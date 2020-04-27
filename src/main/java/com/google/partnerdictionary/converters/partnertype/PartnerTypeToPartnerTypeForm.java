package com.google.partnerdictionary.converters.partnertype;

import com.google.partnerdictionary.forms.PartnerTypeForm;
import com.google.partnerdictionary.models.PartnerType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PartnerTypeToPartnerTypeForm implements Converter<PartnerType, PartnerTypeForm> {

  @Override
  public PartnerTypeForm convert(PartnerType partnerType) {
    PartnerTypeForm partnerTypeForm = new PartnerTypeForm();
    partnerTypeForm.setPartnerTypeId(partnerType.getPartnerTypeId());
    partnerTypeForm.setPartnerTypeName(partnerType.getPartnerTypeName());
    return partnerTypeForm;
  }
}
