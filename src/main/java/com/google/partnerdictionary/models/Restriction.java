package com.google.partnerdictionary.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restriction")
public class Restriction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "restriction_id")
  private Integer restrictionId;
  private String restrictionName;

  public Restriction() {
  }

  public Restriction(String restrictionName) {
    super();
    this.restrictionName = restrictionName;
  }

  public Integer getRestrictionId() {
    return restrictionId;
  }

  public void setRestrictionId(Integer restrictionId) {
    this.restrictionId = restrictionId;
  }

  public String getRestrictionName() {
    return restrictionName;
  }

  public void setRestrictionName(String restrictionName) {
    this.restrictionName = restrictionName;
  }

  @Override
  public String toString() {
    return "Restriction{" +
        "restrictionId=" + restrictionId +
        ", restrictionName='" + restrictionName + '\'' +
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
    Restriction that = (Restriction) o;
    return Objects.equals(restrictionId, that.restrictionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restrictionId);
  }
}
