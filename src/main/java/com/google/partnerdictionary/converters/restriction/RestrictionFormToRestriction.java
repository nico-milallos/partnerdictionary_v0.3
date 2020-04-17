package com.google.partnerdictionary.converters.restriction;

import com.google.partnerdictionary.forms.RestrictionForm;
import com.google.partnerdictionary.models.Restriction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RestrictionFormToRestriction implements Converter<RestrictionForm, Restriction> {

  @Override
  public Restriction convert(RestrictionForm restrictionForm) {
    Restriction restriction = new Restriction();
    if (restrictionForm.getRestrictionId() != null && !StringUtils.isEmpty(restrictionForm.getRestrictionId())) {
      restriction.setRestrictionId(new Integer(restrictionForm.getRestrictionId()));
    }
    restriction.setRestrictionName(restrictionForm.getRestrictionName());
    return restriction;
  }
}
