package com.google.partnerdictionary.forms;

import com.google.partnerdictionary.models.Restriction;

public class RestrictionTypeForm {
  private Integer restrictionTypeId;
  private Restriction restriction;
  private String restrictionTypeName;

  public Integer getRestrictionTypeId() {
    return restrictionTypeId;
  }

  public void setRestrictionTypeId(Integer restrictionTypeId) {
    this.restrictionTypeId = restrictionTypeId;
  }

  public Restriction getRestriction() {
    return restriction;
  }

  public void setRestriction(Restriction restriction) {
    this.restriction = restriction;
  }

  public String getRestrictionTypeName() {
    return restrictionTypeName;
  }

  public void setRestrictionTypeName(String restrictionTypeName) {
    this.restrictionTypeName = restrictionTypeName;
  }
}
