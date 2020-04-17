package com.google.partnerdictionary.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partner")
public class Partner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "partner_id")
  private Integer partnerId;
  private String partnerCode;
  private String partnerName;

  public Partner() {
  }

  public Partner(Integer partnerId, String partnerCode, String partnerName) {
    super();
    this.partnerCode = partnerCode;
    this.partnerName = partnerName;
  }

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

  @Override
  public String toString() {
    return "Partner{" +
        "partnerId=" + partnerId +
        ", partnerCode='" + partnerCode + '\'' +
        ", partnerName='" + partnerName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Partner partner = (Partner) o;
    return Objects.equals(partnerId, partner.partnerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partnerId);
  }
}
