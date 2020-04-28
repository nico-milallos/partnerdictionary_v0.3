package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.services.PartnerRestrictionService;
import com.google.partnerdictionary.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @Autowired
  private PartnerService partnerService;
  @Autowired
  private PartnerRestrictionService partnerRestrictionService;

  @RequestMapping("/")
  public String redirectToHome(Model model) {
    model.addAttribute("indexActive", "active");
    return "redirect:/index";
  }

  @RequestMapping({"/index"})
  public String goToHome(Model model) {
    model.addAttribute("indexActive", "active");
    return "index";
  }

  @RequestMapping({"/index/search"})
  public String goSearch(Model model, String keyword) {
    model.addAttribute("indexActive", "active");
    model.addAttribute( "keyword", keyword);
    if(keyword != null) {
      model.addAttribute("partners", partnerService.findByKeyword(keyword));
    } else {
      model.addAttribute("partners", partnerService.getAll());
    }
    return "index";
  }

  @RequestMapping({"/search/display/{partnerId}"})
  public String viewRestrictions(@PathVariable String partnerId, Model model) {
    model.addAttribute("indexActive", "active");
    model.addAttribute("partner", partnerService.getById(new Integer(partnerId)));
    model.addAttribute("cabinTypes", partnerRestrictionService.findCabinTypesByPartnerId(partnerId));
    model.addAttribute("linkTypes", partnerRestrictionService.findLinkTypesByPartnerId(partnerId));
    model.addAttribute("tripTypes", partnerRestrictionService.findTripTypesByPartnerId(partnerId));
    model.addAttribute("otherRestrictions", partnerRestrictionService.findOtherRestrictionsByPartnerId(partnerId));
    return "/search/display";
  }

}
