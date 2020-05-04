package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.partnertype.PartnerTypeToPartnerTypeForm;
import com.google.partnerdictionary.forms.PartnerTypeForm;
import com.google.partnerdictionary.models.PartnerType;
import com.google.partnerdictionary.services.PartnerTypeService;
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
public class PartnerTypeController {

  @Autowired
  private PartnerTypeService partnerTypeService;
  @Autowired
  private PartnerTypeToPartnerTypeForm partnerTypeToPartnerTypeForm;
  @Autowired
  private ServletContext servletContext;

  @RequestMapping({"/partner_type/list", "/partner_type"})
  public String getAllPartnerTypes(Model model) {
    model.addAttribute("partnerTypes", partnerTypeService.getAll());
    model.addAttribute("partnerTypeActive", "active");
    return "partner_type/list";
  }

  @RequestMapping("/partner_type/display/{partnerTypeId}")
  public String getPartnerType(@PathVariable String partnerTypeId, Model model) {
    model.addAttribute("partnerType", partnerTypeService.getById(Integer.valueOf(partnerTypeId)));
    return "partner_type/display";
  }

  @RequestMapping("partner_type/edit/{partnerTypeId}")
  public String updatePartnerType(@PathVariable String partnerTypeId, Model model){
    PartnerType partner_type = partnerTypeService.getById(Integer.valueOf(partnerTypeId));
    PartnerTypeForm partnerTypeForm = partnerTypeToPartnerTypeForm.convert(partner_type);

    model.addAttribute("partnerTypeForm", partnerTypeForm);
    return "partner_type/form";
  }

  @RequestMapping("/partner_type/add")
  public String addPartnerType(Model model) {
    model.addAttribute("partnerTypeForm", new PartnerTypeForm());
    return "partner_type/form";
  }

  @RequestMapping(value = "/partner_type", method = RequestMethod.POST)
  public String saveOrUpdatePartnerType(@Validated PartnerTypeForm partnerTypeForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "partner_type/form";
    }

    PartnerType savedPartnerType = partnerTypeService.saveOrUpdateForm(partnerTypeForm);

    return "redirect:/partner_type/display/" + savedPartnerType.getPartnerTypeId();
  }

  @RequestMapping("/partner_type/remove/{partnerTypeId}")
  public String deleteRestriction(@PathVariable String partnerTypeId) {
    partnerTypeService.delete(Integer.valueOf(partnerTypeId));
    return "redirect:/partner_type/list";
  }

  @PostMapping("/partner_type/upload")
  public String singleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes, Model model) throws FileNotFoundException {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:/partner_type/list";
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
    fileSaver.uploadPartnerTypeFromFile(fileDirectory, partnerTypeService);
    model.addAttribute("partnerTypeActive", "active");

    return "redirect:/partner_type/list";
  }
}
