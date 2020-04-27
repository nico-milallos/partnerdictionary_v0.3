package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.partnerrestriction.PartnerRestrictionToPartnerRestrictionForm;
import com.google.partnerdictionary.forms.PartnerRestrictionForm;
import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.PartnerRestriction;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.services.PartnerRestrictionService;
import com.google.partnerdictionary.services.PartnerService;
import com.google.partnerdictionary.services.RestrictionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PartnerRestrictionController {

  @Autowired
  private PartnerRestrictionService partnerRestrictionService;
  @Autowired
  private PartnerService partnerService;
  @Autowired
  private RestrictionTypeService restrictionTypeService;
  @Autowired
  private PartnerRestrictionToPartnerRestrictionForm partnerRestrictionToPartnerRestrictionForm;

  @RequestMapping({"/partner_restriction/list","partner_restriction"})
  public String getAllPartnerRestrictions(Model model) {
    model.addAttribute("partnerRestrictions", partnerRestrictionService.getAll());
    model.addAttribute("partnerRestrictionActive", "active");
    return "partner_restriction/list";
  }

  @RequestMapping("/partner_restriction/display/{partnerRestrictionId}")
  public String getPartnerRestriction(@PathVariable Integer partnerRestrictionId, Model model) {
    model.addAttribute("partnerRestriction", partnerRestrictionService.getById(Integer.valueOf(partnerRestrictionId)));
    return "partner_restriction/display";
  }

  @RequestMapping("partner_restriction/edit/{partnerRestrictionId}")
  public String updatepartnerRestriction(@PathVariable String partnerRestrictionId, Model model) {
    PartnerRestriction partnerRestriction = partnerRestrictionService.getById(Integer.valueOf(partnerRestrictionId));
    PartnerRestrictionForm partnerRestrictionForm = partnerRestrictionToPartnerRestrictionForm.convert(partnerRestriction);

    model.addAttribute("partners", partnerService.getAll());
    model.addAttribute("restrictionTypes", restrictionTypeService.getAll());
    model.addAttribute("partnerRestrictionForm", partnerRestrictionForm);
    return "partner_restriction/form";
  }

  @RequestMapping("/partner_restriction/add")
  public String addRestrictionType(Model model) {
    model.addAttribute("partners", partnerService.getAll());
    model.addAttribute("restrictionTypes", restrictionTypeService.getAll());
    model.addAttribute("partnerRestrictionForm", new PartnerRestrictionForm());
    return "partner_restriction/form";
  }

  @RequestMapping(value = "/partner_restriction", method = RequestMethod.POST)
  public String saveOrUpdateRestrictionType(@Validated PartnerRestrictionForm partnerRestrictionForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "partner_restriction/form";
    }

    PartnerRestriction savedPartnerRestriction = partnerRestrictionService.saveOrUpdateForm(partnerRestrictionForm);

    return "redirect:/partner_restriction/display/" + savedPartnerRestriction.getPartnerRestrictionId();
  }

  @RequestMapping("/partner_restriction/remove/{partnerRestrictionId}")
  public String deleteRestrictionType(@PathVariable String partnerRestrictionId) {
    partnerRestrictionService.delete(Integer.valueOf(partnerRestrictionId));
    return "redirect:/partner_restriction/list";
  }
}
