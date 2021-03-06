package com.google.partnerdictionary.forms;

import com.google.partnerdictionary.models.PartnerType;

public class PartnerForm {
  private Integer partnerId;
  private String partnerCode;
  private String partnerName;
  private PartnerType partnerType;

  public Integer getPartnerId() {
    return partnerId;
  }

  public void setPartnerId(Integer partnerId) {
    this.partnerId = partnerId;
  }

  public String getPartnerCode() {
    return partnerCode;
  }

  public void setPartnerCode(String partnerCode) {
    this.partnerCode = partnerCode;
  }

  public String getPartnerName() {
    return partnerName;
  }

  public void setPartnerName(String partnerName) {
    this.partnerName = partnerName;
  }

  public PartnerType getPartnerType() {
    return partnerType;
  }

  public void setPartnerType(PartnerType partnerType) {
    this.partnerType = partnerType;
  }
}
