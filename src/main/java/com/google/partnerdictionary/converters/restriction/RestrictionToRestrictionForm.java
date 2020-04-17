package com.google.partnerdictionary.converters.restriction;

import com.google.partnerdictionary.forms.RestrictionForm;
import com.google.partnerdictionary.models.Restriction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RestrictionToRestrictionForm implements Converter<Restriction, RestrictionForm> {

  @Override
  public RestrictionForm convert(Restriction restriction) {
    RestrictionForm restrictionForm = new RestrictionForm();
    restrictionForm.setRestrictionId(restriction.getRestrictionId());
    restrictionForm.setRestrictionName(restriction.getRestrictionName());
    return restrictionForm;
  }
}
