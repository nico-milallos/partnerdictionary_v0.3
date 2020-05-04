package com.google.partnerdictionary.repositories;

import com.google.partnerdictionary.models.Partner;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PartnerRepository extends CrudRepository<Partner, Integer> {

  @Query(value="select * from partner p where p.partner_code like %:keyword% or p.partner_name like %:keyword%", nativeQuery = true)
  List<Partner> findByKeyword(@Param("keyword") String keyword);

  @Query(value="select * from partner where partner_code like %:partnerCode% limit 1", nativeQuery = true)
  Partner findByPartnerCode(@Param("partnerCode") String partnerCode);
}
