package com.google.partnerdictionary.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "partner_restriction")
public class PartnerRestriction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "partner_restriction_id")
  private Integer partnerRestrictionId;
  private String partnerRestrictionValue;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="partner_id", referencedColumnName = "partner_id")
  private Partner partner;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="restriction_type_id", referencedColumnName = "restriction_type_id")
  private RestrictionType restrictionType;

  public PartnerRestriction() {
  }

  public PartnerRestriction(Integer partnerRestrictionId, String partnerRestrictionValue,
      Partner partner, RestrictionType restrictionType) {
    this.partnerRestrictionId = partnerRestrictionId;
    this.partnerRestrictionValue = partnerRestrictionValue;
    this.partner = partner;
    this.restrictionType = restrictionType;
  }

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

  @Override
  public String toString() {
    return "PartnerRestriction{" +
        "partnerRestrictionId=" + partnerRestrictionId +
        ", partnerRestrictionValue='" + partnerRestrictionValue + '\'' +
        ", partnerId=" + partner +
        ", restrictionTypeId=" + restrictionType +
        '}';
  }
}
