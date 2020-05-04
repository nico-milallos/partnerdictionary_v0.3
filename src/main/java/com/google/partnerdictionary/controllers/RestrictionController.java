package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.restriction.RestrictionToRestrictionForm;
import com.google.partnerdictionary.forms.RestrictionForm;
import com.google.partnerdictionary.models.Restriction;
import com.google.partnerdictionary.services.RestrictionService;
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
public class RestrictionController {

  @Autowired
  private RestrictionService restrictionService;
  @Autowired
  private RestrictionToRestrictionForm restrictionToRestrictionForm;
  @Autowired
  private ServletContext servletContext;

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

  @PostMapping("/restriction/upload")
  public String singleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes, Model model) throws FileNotFoundException {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:/restriction/list";
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
    fileSaver.uploadRestrictionFromFile(fileDirectory, restrictionService);
    model.addAttribute("restrictionActive", "active");

    return "redirect:/restriction/list";
  }
}
