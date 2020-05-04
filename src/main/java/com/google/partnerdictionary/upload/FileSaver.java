package com.google.partnerdictionary.upload;

import com.google.partnerdictionary.models.Partner;
import com.google.partnerdictionary.models.PartnerRestriction;
import com.google.partnerdictionary.models.PartnerType;
import com.google.partnerdictionary.models.Restriction;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.services.PartnerRestrictionService;
import com.google.partnerdictionary.services.PartnerService;
import com.google.partnerdictionary.services.PartnerTypeService;
import com.google.partnerdictionary.services.RestrictionService;
import com.google.partnerdictionary.services.RestrictionTypeService;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileSaver {

  public FileSaver() {
  }

  public void uploadPartnerFromFile(String fileDirectory, PartnerService partnerService, PartnerTypeService partnerTypeService) throws FileNotFoundException {
    CSVFileReader fileReader = new CSVFileReader(fileDirectory);
    List<List<String>> parsedData = fileReader.getData();
    List<Partner> partners = new ArrayList<>();
    for(List<String> list : parsedData) {
      PartnerType partnerType = partnerTypeService.getByName(list.get(2));
      System.out.println("New Partner Added:\n\t[partner_code = " + list.get(0) +
          ", partner_name = " + list.get(1) +
          ", partner_type_name = " + partnerType.getPartnerTypeName() + "]");
      partners.add(new Partner(list.get(0), list.get(1), partnerType));
    }
    partnerService.saveAll(partners);
  }

  public void uploadPartnerRestrictionFromFile(String fileDirectory, PartnerService partnerService, PartnerRestrictionService partnerRestrictionService, RestrictionTypeService restrictionTypeService) throws FileNotFoundException {
    CSVFileReader fileReader = new CSVFileReader(fileDirectory);
    List<List<String>> parsedData = fileReader.getData();
    List<PartnerRestriction> partnerRestrictionss = new ArrayList<>();
    for(List<String> list : parsedData) {
      Partner partner = partnerService.getByPartnerCode(list.get(1));
      RestrictionType restrictionType = restrictionTypeService.getByRestrictionTypeName(list.get(2));
      System.out.println("New Partner Restriction Added:\n\t[partner_restriction_value = " + list.get(0) +
              ", partner_name = " + partner.getPartnerName() +
              ", restriction_type_name = " + restrictionType.getRestrictionTypeName() + "]");
      partnerRestrictionss.add(new PartnerRestriction(list.get(0), partner, restrictionType));
    }
    partnerRestrictionService.saveAll(partnerRestrictionss);
  }

  public void uploadPartnerTypeFromFile(String fileDirectory, PartnerTypeService partnerTypeService) throws FileNotFoundException {
    CSVFileReader fileReader = new CSVFileReader(fileDirectory);
    List<List<String>> parsedData = fileReader.getData();
    List<PartnerType> partnerTypes = new ArrayList<>();
    for(List<String> list : parsedData) {
      System.out.println("New Partner Type Added:\n\t[partner_type_name = " + list.get(0) + "]");
      partnerTypes.add(new PartnerType(list.get(0)));
    }
    partnerTypeService.saveAll(partnerTypes);
  }

  public void uploadRestrictionFromFile(String fileDirectory, RestrictionService restrictionService) throws FileNotFoundException {
    CSVFileReader fileReader = new CSVFileReader(fileDirectory);
    List<List<String>> parsedData = fileReader.getData();
    List<Restriction> restrictions = new ArrayList<>();
    for(List<String> list : parsedData) {
      System.out.println("New Restriction Added:\n\t[restriction_name = " + list.get(0) + "]");
      restrictions.add(new Restriction(list.get(0)));
    }
    restrictionService.saveAll(restrictions);
  }

  public void uploadRestrictionTypeFromFile(String fileDirectory, RestrictionTypeService restrictionTypeService, RestrictionService restrictionService) throws FileNotFoundException {
    CSVFileReader fileReader = new CSVFileReader(fileDirectory);
    List<List<String>> parsedData = fileReader.getData();
    List<RestrictionType> restrictionTypes = new ArrayList<>();
    for(List<String> list : parsedData) {
      Restriction restriction = restrictionService.getByName(list.get(0));
      System.out.println("New Restriction Type Added:\n\t[restriction_name = " + restriction.getRestrictionName()+ ", " + "restriction_type_name = " + list.get(1) + "]");
      restrictionTypes.add(new RestrictionType(restriction, list.get(1)));
    }
    restrictionTypeService.saveAll(restrictionTypes);
  }
}
