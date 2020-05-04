package com.google.partnerdictionary.repositories;

import com.google.partnerdictionary.models.RestrictionType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestrictionTypeRepository extends CrudRepository<RestrictionType, Integer> {

  @Query(value="select * from restriction_type r where r.restriction_type_name like %:restrictionTypeName% limit 1", nativeQuery = true)
  RestrictionType findByRestrictionTypeName(@Param("restrictionTypeName") String restrictionTypeName);
}
