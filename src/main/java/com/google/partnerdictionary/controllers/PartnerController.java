package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.partner.PartnerToPartnerForm;
import com.google.partnerdictionary.forms.PartnerForm;
import com.google.partnerdictionary.services.PartnerService;
import com.google.partnerdictionary.models.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PartnerController {

  @Autowired
  private PartnerService partnerService;
  @Autowired
  private PartnerToPartnerForm partnerToPartnerForm;

  @RequestMapping({"/partner/list", "/partner"})
  public String getAllPartners(Model model) {
    model.addAttribute("partners", partnerService.getAll());
    model.addAttribute("partnerActive", "active");
    return "partner/list";
  }

  @RequestMapping("/partner/display/{partnerId}")
  public String getPartner(@PathVariable String partnerId, Model model) {
    model.addAttribute("partner", partnerService.getById(Integer.valueOf(partnerId)));
    return "partner/display";
  }

  @RequestMapping("partner/edit/{partnerId}")
  public String updatePartner(@PathVariable String partnerId, Model model){
    Partner partner = partnerService.getById(Integer.valueOf(partnerId));
    PartnerForm partnerForm = partnerToPartnerForm.convert(partner);

    model.addAttribute("partnerForm", partnerForm);
    return "partner/form";
  }

  @RequestMapping("/partner/add")
  public String addPartner(Model model) {
    model.addAttribute("partnerForm", new PartnerForm());
    return "partner/form";
  }

  @RequestMapping(value = "/partner", method = RequestMethod.POST)
  public String saveOrUpdatePartner(@Validated PartnerForm partnerForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "partner/form";
    }

    Partner savedPartner = partnerService.saveOrUpdateForm(partnerForm);

    return "redirect:/partner/display/" + savedPartner.getPartnerId();
  }

  @RequestMapping("/partner/remove/{partnerId}")
  public String deletePartner(@PathVariable String partnerId) {
    partnerService.delete(Integer.valueOf(partnerId));
    return "redirect:/partner/list";
  }
}
