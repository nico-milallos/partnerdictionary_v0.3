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
@Table(name = "restriction_type")
public class RestrictionType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "restriction_type_id")
  private Integer restrictionTypeId;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "restriction_id", referencedColumnName = "restriction_id")
  private Restriction restriction;
  private String restrictionTypeName;

  public RestrictionType() {
  }
  public RestrictionType(Restriction restriction,
      String restrictionTypeName) {
    super();
    this.restriction = restriction;
    this.restrictionTypeName = restrictionTypeName;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestrictionType restrictionType = (RestrictionType) o;
    return Objects.equals(restrictionTypeId, restrictionType.restrictionTypeId);
  }

  @Override
  public String toString() {
    return "RestrictionType{" +
        "restrictionTypeId=" + restrictionTypeId +
        ", restrictionId=" + restriction +
        ", restrictionTypeName='" + restrictionTypeName + '\'' +
        '}';
  }
}
