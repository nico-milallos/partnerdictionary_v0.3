package com.google.partnerdictionary.repositories;

import com.google.partnerdictionary.models.PartnerRestriction;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PartnerRestrictionRepository extends CrudRepository<PartnerRestriction, Integer> {

  @Query(value="select * from partner_restriction pr where pr.partner_id = :partnerId and pr.restriction_type_id in"
      + "(select restriction_type_id from restriction_type rt where rt.restriction_id = "
      + "(select restriction_id from restriction r where r.restriction_name like \"%Cabin Type%\"));", nativeQuery = true)
  List<PartnerRestriction> findCabinTypesByPartnerId(@Param("partnerId") String partnerId);

  @Query(value="select * from partner_restriction pr where pr.partner_id = :partnerId and pr.restriction_type_id in"
      + "(select restriction_type_id from restriction_type rt where rt.restriction_id = "
      + "(select restriction_id from restriction r where r.restriction_name like \"%Link Type%\"));", nativeQuery = true)
  List<PartnerRestriction> findLinkTypesByPartnerId(@Param("partnerId") String partnerId);

  @Query(value="select * from partner_restriction pr where pr.partner_id = :partnerId and pr.restriction_type_id in"
      + "(select restriction_type_id from restriction_type rt where rt.restriction_id = "
      + "(select restriction_id from restriction r where r.restriction_name like \"%Trip Type%\"));", nativeQuery = true)
  List<PartnerRestriction> findTripTypesByPartnerId(@Param("partnerId") String partnerId);

  @Query(value="select * from partner_restriction pr where pr.partner_id = :partnerId and pr.restriction_type_id in"
      + "(select rt.restriction_type_id from restriction_type rt where rt.restriction_id in "
      + "(select restriction_id from restriction r where r.restriction_name not in ('Cabin Type', 'Link Type', 'Trip Type')))"
      + "order by pr.restriction_type_id asc;", nativeQuery = true)
  List<PartnerRestriction> findOtherRestrictionsByPartnerId(@Param("partnerId") String partnerId);
}
