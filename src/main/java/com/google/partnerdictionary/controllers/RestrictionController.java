package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.restriction.RestrictionToRestrictionForm;
import com.google.partnerdictionary.forms.RestrictionForm;
import com.google.partnerdictionary.models.Restriction;
import com.google.partnerdictionary.services.RestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestrictionController {

  @Autowired
  private RestrictionService restrictionService;
  @Autowired
  private RestrictionToRestrictionForm restrictionToRestrictionForm;

  @RequestMapping({"/restriction/list", "/restriction"})
  public String getAllRestrictions(Model model) {
    model.addAttribute("restrictions", restrictionService.getAll());
    model.addAttribute("restrictionActive", "active");
    return "restriction/list";
  }

  @RequestMapping("/restriction/display/{restrictionId}")
  public String getRestriction(@PathVariable String restrictionId, Model model) {
    model.addAttribute("restriction", restrictionService.getById(Integer.valueOf(restrictionId)));
    return "restriction/display";
  }

  @RequestMapping("restriction/edit/{restrictionId}")
  public String updateRestriction(@PathVariable String restrictionId, Model model){
    Restriction restriction = restrictionService.getById(Integer.valueOf(restrictionId));
    RestrictionForm restrictionForm = restrictionToRestrictionForm.convert(restriction);

    model.addAttribute("restrictionForm", restrictionForm);
    return "restriction/form";
  }

  @RequestMapping("/restriction/add")
  public String addRestriction(Model model) {
    model.addAttribute("restrictionForm", new RestrictionForm());
    return "restriction/form";
  }

  @RequestMapping(value = "/restriction", method = RequestMethod.POST)
  public String saveOrUpdateRestriction(@Validated RestrictionForm restrictionForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "restriction/form";
    }

    Restriction savedRestriction = restrictionService.saveOrUpdateForm(restrictionForm);

    return "redirect:/restriction/display/" + savedRestriction.getRestrictionId();
  }

  @RequestMapping("/restriction/remove/{restrictionId}")
  public String deleteRestriction(@PathVariable String restrictionId) {
    restrictionService.delete(Integer.valueOf(restrictionId));
    return "redirect:/restriction/list";
  }
}
