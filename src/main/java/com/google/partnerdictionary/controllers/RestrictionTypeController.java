package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.restrictiontype.RestrictionTypeToRestrictionTypeForm;
import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.services.RestrictionService;
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
public class RestrictionTypeController {
  
  @Autowired
  private RestrictionTypeService restrictionTypeService;
  @Autowired
  private RestrictionService restrictionService;
  @Autowired
  private RestrictionTypeToRestrictionTypeForm restrictionTypeToRestrictionTypeForm;

  @RequestMapping({"/restrictiontype/list", "/restrictiontype"})
  public String getAllRestrictionTypes(Model model) {
    model.addAttribute("restrictionTypes", restrictionTypeService.getAll());
    model.addAttribute("restrictionTypeActive", "active");
    return "restrictiontype/list";
  }
  
  @RequestMapping("/restrictiontype/display/{restrictionTypeId}")
  public String getRestrictionType(@PathVariable String restrictionTypeId, Model model) {
    model.addAttribute("restrictionType", restrictionTypeService.getById(Integer.valueOf(restrictionTypeId)));
    return "restrictiontype/display";
  }
  
  @RequestMapping("restrictiontype/edit/{restrictionTypeId}")
  public String updateRestrictionType(@PathVariable String restrictionTypeId, Model model){
    RestrictionType restrictionType = restrictionTypeService.getById(Integer.valueOf(restrictionTypeId));
    RestrictionTypeForm restrictionTypeForm = restrictionTypeToRestrictionTypeForm.convert(restrictionType);

    model.addAttribute("restrictions", restrictionService.getAll());
    model.addAttribute("restrictionTypeForm", restrictionTypeForm);
    return "restrictiontype/form";
  }
  
  @RequestMapping("/restrictiontype/add")
  public String addRestrictionType(Model model) {
    model.addAttribute("restrictions", restrictionService.getAll());
    model.addAttribute("restrictionTypeForm", new RestrictionTypeForm());
    return "restrictiontype/form";
  }
  
  @RequestMapping(value = "/restrictiontype", method = RequestMethod.POST)
  public String saveOrUpdateRestrictionType(@Validated RestrictionTypeForm restrictionTypeForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "restrictiontype/form";
    }
  
    RestrictionType savedRestrictionType = restrictionTypeService.saveOrUpdateForm(restrictionTypeForm);
  
    return "redirect:/restrictiontype/display/" + savedRestrictionType.getRestrictionTypeId();
  }
  
  @RequestMapping("/restrictiontype/remove/{restrictionTypeId}")
  public String deleteRestrictionType(@PathVariable String restrictionTypeId) {
    restrictionTypeService.delete(Integer.valueOf(restrictionTypeId));
    return "redirect:/restrictiontype/list";
  }
}
