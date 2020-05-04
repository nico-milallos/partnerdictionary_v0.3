package com.google.partnerdictionary.repositories;

import com.google.partnerdictionary.models.Restriction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestrictionRepository extends CrudRepository<Restriction, Integer> {

  @Query(value="select * from restriction r where r.restriction_name like %:restrictionName% limit 1", nativeQuery = true)
  Restriction findByRestrictionName(@Param("restrictionName") String restrictionName);
}
