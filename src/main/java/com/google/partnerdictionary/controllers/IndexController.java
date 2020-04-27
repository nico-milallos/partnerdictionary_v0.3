package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @Autowired
  PartnerService partnerService;

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
    if(keyword != null) {
      model.addAttribute("partners", partnerService.findByKeyword(keyword));
    } else {
      model.addAttribute("partners", partnerService.getAll());
    }
    return "index";
  }

}
