package com.google.partnerdictionary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

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
}
