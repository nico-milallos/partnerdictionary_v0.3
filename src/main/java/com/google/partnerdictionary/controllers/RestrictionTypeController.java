package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.restrictiontype.RestrictionTypeToRestrictionTypeForm;
import com.google.partnerdictionary.forms.RestrictionTypeForm;
import com.google.partnerdictionary.models.RestrictionType;
import com.google.partnerdictionary.services.RestrictionService;
import com.google.partnerdictionary.services.RestrictionTypeService;
import com.google.partnerdictionary.upload.FileSaver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RestrictionTypeController {

  @Autowired
  private RestrictionTypeService restrictionTypeService;
  @Autowired
  private RestrictionService restrictionService;
  @Autowired
  private RestrictionTypeToRestrictionTypeForm restrictionTypeToRestrictionTypeForm;
  @Autowired
  private ServletContext servletContext;

  @RequestMapping({"/restriction_type/list", "/restriction_type"})
  public String getAllRestrictionTypes(Model model) {
    model.addAttribute("restrictionTypes", restrictionTypeService.getAll());
    model.addAttribute("restrictionTypeActive", "active");
    return "restriction_type/list";
  }

  @RequestMapping("/restriction_type/display/{restrictionTypeId}")
  public String getRestrictionType(@PathVariable String restrictionTypeId, Model model) {
    model.addAttribute("restrictionType", restrictionTypeService.getById(Integer.valueOf(restrictionTypeId)));
    return "restriction_type/display";
  }

  @RequestMapping("restriction_type/edit/{restrictionTypeId}")
  public String updateRestrictionType(@PathVariable String restrictionTypeId, Model model){
    RestrictionType restrictionType = restrictionTypeService.getById(Integer.valueOf(restrictionTypeId));
    RestrictionTypeForm restrictionTypeForm = restrictionTypeToRestrictionTypeForm.convert(restrictionType);

    model.addAttribute("restrictions", restrictionService.getAll());
    model.addAttribute("restrictionTypeForm", restrictionTypeForm);
    return "restriction_type/form";
  }

  @RequestMapping("/restriction_type/add")
  public String addRestrictionType(Model model) {
    model.addAttribute("restrictions", restrictionService.getAll());
    model.addAttribute("restrictionTypeForm", new RestrictionTypeForm());
    return "restriction_type/form";
  }

  @RequestMapping(value = "/restriction_type", method = RequestMethod.POST)
  public String saveOrUpdateRestrictionType(@Validated RestrictionTypeForm restrictionTypeForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "restriction_type/form";
    }

    RestrictionType savedRestrictionType = restrictionTypeService.saveOrUpdateForm(restrictionTypeForm);

    return "redirect:/restriction_type/display/" + savedRestrictionType.getRestrictionTypeId();
  }

  @RequestMapping("/restriction_type/remove/{restrictionTypeId}")
  public String deleteRestrictionType(@PathVariable String restrictionTypeId) {
    restrictionTypeService.delete(Integer.valueOf(restrictionTypeId));
    return "redirect:/restriction_type/list";
  }

  @PostMapping("/restriction_type/upload")
  public String singleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes, Model model) throws FileNotFoundException {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:/restriction_type/list";
    }

    try {

      // Get the file and save it somewhere
      byte[] bytes = file.getBytes();
      Path path = Paths.get(servletContext.getRealPath("/") + file.getOriginalFilename());
      Files.write(path, bytes);

      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");

    } catch (IOException e) {
      e.printStackTrace();
    }

    String fileDirectory = servletContext.getRealPath("/") + file.getOriginalFilename();
    FileSaver fileSaver = new FileSaver();
    fileSaver.uploadRestrictionTypeFromFile(fileDirectory, restrictionTypeService, restrictionService);
    model.addAttribute("restrictionTypeActive", "active");

    return "redirect:/restriction_type/list";
  }
}
