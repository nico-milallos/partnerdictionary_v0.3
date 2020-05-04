package com.google.partnerdictionary.controllers;

import com.google.partnerdictionary.converters.partner.PartnerToPartnerForm;
import com.google.partnerdictionary.forms.PartnerForm;
import com.google.partnerdictionary.services.PartnerService;
import com.google.partnerdictionary.models.Partner;
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
public class PartnerController {

  @Autowired
  private PartnerService partnerService;
  @Autowired
  private PartnerTypeService partnerTypeService;
  @Autowired
  private PartnerToPartnerForm partnerToPartnerForm;
  @Autowired
  private ServletContext servletContext;

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

    model.addAttribute("partnerTypes", partnerTypeService.getAll());
    model.addAttribute("partnerForm", partnerForm);
    return "partner/form";
  }

  @RequestMapping("/partner/add")
  public String addPartner(Model model) {
    model.addAttribute("partnerTypes", partnerTypeService.getAll());
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

  @PostMapping("/partner/upload")
  public String singleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes, Model model) throws FileNotFoundException {

    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:/partner/list";
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
    fileSaver.uploadPartnerFromFile(fileDirectory, partnerService, partnerTypeService);
    model.addAttribute("partnerActive", "active");

    return "redirect:/partner/list";
  }
}
