package com.google.partnerdictionary.repositories;

import com.google.partnerdictionary.models.PartnerType;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PartnerTypeRepository extends CrudRepository<PartnerType, Integer> {
  @Query(value="select * from partner_type p where p.partner_type_name like %:partnerTypeName% limit 1", nativeQuery = true)
  PartnerType findByPartnerTypeName(@Param("partnerTypeName") String partnerTypeName);
}
