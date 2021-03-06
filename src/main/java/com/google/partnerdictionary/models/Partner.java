package com.google.partnerdictionary.models;

import java.util.Objects;
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
@Table(name = "partner")
public class Partner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "partner_id")
  private Integer partnerId;
  private String partnerCode;
  private String partnerName;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "partner_type_id", referencedColumnName = "partner_type_id")
  private PartnerType partnerType;

  public Partner() {
  }

  public Partner(String partnerCode, String partnerName, PartnerType partnerType) {
    super();
    this.partnerCode = partnerCode;
    this.partnerName = partnerName;
    this.partnerType = partnerType;
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

  public PartnerType getPartnerType() {
    return partnerType;
  }

  public void setPartnerType(PartnerType partnerType) {
    this.partnerType = partnerType;
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
