package com.google.partnerdictionary.bootstrap;

import com.google.partnerdictionary.models.Partner;
import com.google.partnerdictionary.models.PartnerType;
import com.google.partnerdictionary.models.Restriction;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.repositories.RestrictionRepository;
import com.google.partnerdictionary.services.PartnerTypeService;
import com.google.partnerdictionary.services.RestrictionService;
import com.google.partnerdictionary.services.RestrictionTypeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final PartnerTypeService partnerTypeService;
  private final RestrictionService restrictionService;
  private final RestrictionTypeService restrictionTypeService;

  public BootStrapData(
      PartnerTypeService partnerTypeService,
      RestrictionService restrictionService,
      RestrictionTypeService restrictionTypeService) {
    this.partnerTypeService = partnerTypeService;
    this.restrictionService = restrictionService;
    this.restrictionTypeService = restrictionTypeService;
  }

  @Override
  public void run(String... args) throws Exception {
    List<PartnerType> partnerTypes = new ArrayList<>();
    partnerTypes.add(new PartnerType("Carrier"));
    partnerTypes.add(new PartnerType("OTA"));
    partnerTypeService.saveAll(partnerTypes);

    Restriction tripType = new Restriction("Trip Type");
    Restriction cabinType = new Restriction("Cabin Type");
    Restriction linkType = new Restriction("Link Type");

    List<Restriction> restrictions = new ArrayList<>();
    restrictions.add(tripType);
    restrictions.add(cabinType);
    restrictions.add(linkType);
    restrictionService.saveAll(restrictions);

    List<RestrictionType> tripTypes = new ArrayList<>();
    tripTypes.add(new RestrictionType(tripType,"One-Way"));
    tripTypes.add(new RestrictionType(tripType,"Round-Trip"));
    tripTypes.add(new RestrictionType(tripType,"Open-Jaw"));
    tripTypes.add(new RestrictionType(tripType,"Multi-City"));

    List<RestrictionType> cabinTypes = new ArrayList<>();
    cabinTypes.add(new RestrictionType(cabinType,"Economy"));
    cabinTypes.add(new RestrictionType(cabinType,"Premium-Economy"));
    cabinTypes.add(new RestrictionType(cabinType,"Business"));
    cabinTypes.add(new RestrictionType(cabinType,"First-Class"));

    List<RestrictionType> linkTypes = new ArrayList<>();
    linkTypes.add(new RestrictionType(linkType,"Site-Link"));
    linkTypes.add(new RestrictionType(linkType,"Shallow-Link"));
    linkTypes.add(new RestrictionType(linkType,"Deep-Link"));

    restrictionTypeService.saveAll(tripTypes);
    restrictionTypeService.saveAll(cabinTypes);
    restrictionTypeService.saveAll(linkTypes);
  }
}
