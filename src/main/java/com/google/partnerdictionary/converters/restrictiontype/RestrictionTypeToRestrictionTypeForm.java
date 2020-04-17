package com.google.partnerdictionary.converters.restrictiontype;

import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.RestrictionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RestrictionTypeToRestrictionTypeForm implements
    Converter<RestrictionType, RestrictionTypeForm> {

  @Override
  public RestrictionTypeForm convert(RestrictionType restrictionType) {
    RestrictionTypeForm restrictionTypeForm = new RestrictionTypeForm();
    restrictionTypeForm.setRestrictionTypeId(restrictionType.getRestrictionTypeId());
    restrictionTypeForm.setRestriction(restrictionType.getRestriction());
    restrictionTypeForm.setRestrictionTypeName(restrictionType.getRestrictionTypeName());
    return restrictionTypeForm;
  }
}
