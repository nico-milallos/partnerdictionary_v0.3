package com.google.partnerdictionary.converters.restrictiontype;

import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.RestrictionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RestrictionTypeFormToRestrictionType implements Converter<RestrictionTypeForm, RestrictionType> {

  @Override
  public RestrictionType convert(RestrictionTypeForm restrictionTypeForm) {
    RestrictionType restrictionType = new RestrictionType();
    if (restrictionTypeForm.getRestrictionTypeId() != null && !StringUtils.isEmpty(restrictionTypeForm.getRestrictionTypeId())) {
      restrictionType.setRestrictionTypeId(new Integer(restrictionTypeForm.getRestrictionTypeId()));
    }
    restrictionType.setRestriction(restrictionTypeForm.getRestriction());
    restrictionType.setRestrictionTypeName(restrictionTypeForm.getRestrictionTypeName());
    return restrictionType;
  }
}
