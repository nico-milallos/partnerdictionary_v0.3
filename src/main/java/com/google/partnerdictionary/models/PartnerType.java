package com.google.partnerdictionary.models;

import java.util.Objects;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "partner_type")
public class PartnerType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "partner_type_id")
  private Integer partnerTypeId;
  private String partnerTypeName;

  public PartnerType() {
  }

  public PartnerType(String partnerTypeName) {
    super();
    this.partnerTypeName = partnerTypeName;
  }

  public Integer getPartnerTypeId() {
    return partnerTypeId;
  }

  public void setPartnerTypeId(Integer partnerTypeId) {
    this.partnerTypeId = partnerTypeId;
  }

  public String getPartnerTypeName() {
    return partnerTypeName;
  }

  public void setPartnerTypeName(String partnerTypeName) {
    this.partnerTypeName = partnerTypeName;
  }

  @Override
  public String toString() {
    return "PartnerType{" +
        "partnerTypeId=" + partnerTypeId +
        ", partnerTypeName='" + partnerTypeName + '\'' +
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
    PartnerType that = (PartnerType) o;
    return Objects.equals(partnerTypeId, that.partnerTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partnerTypeId);
  }

  public Optional orElse(Optional object) {
    return object;
  }
}
