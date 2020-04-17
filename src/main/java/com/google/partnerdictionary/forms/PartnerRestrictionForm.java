package com.google.partnerdictionary.forms;

import com.google.partnerdictionary.models.Partner;
import com.google.partnerdictionary.models.RestrictionType;

public class PartnerRestrictionForm {
  private Integer partnerRestrictionId;
  private Partner partner;
  private RestrictionType restrictionType;
  private String partnerRestrictionValue;

  public Integer getPartnerRestrictionId() {
    return partnerRestrictionId;
  }

  public void setPartnerRestrictionId(Integer partnerRestrictionId) {
    this.partnerRestrictionId = partnerRestrictionId;
  }

  public Partner getPartner() {
    return partner;
  }

  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  public RestrictionType getRestrictionType() {
    return restrictionType;
  }

  public void setRestrictionType(RestrictionType restrictionType) {
    this.restrictionType = restrictionType;
  }

  public String getPartnerRestrictionValue() {
    return partnerRestrictionValue;
  }

  public void setPartnerRestrictionValue(String partnerRestrictionValue) {
    this.partnerRestrictionValue = partnerRestrictionValue;
  }
}
