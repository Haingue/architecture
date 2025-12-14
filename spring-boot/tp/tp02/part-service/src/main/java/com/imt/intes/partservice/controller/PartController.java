package com.imt.intes.partservice.controller;

import com.imt.intes.partservice.dto.PartDto;
import com.imt.intes.partservice.exception.PartServiceException;
import com.imt.intes.partservice.service.PartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/client/part")
public class PartController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String port;
    @Autowired
    private Environment environment;

    @Autowired
    private PartService partService;

    @GetMapping
    public String loadPartListPage (Authentication authentication, Model model) {
        LOGGER.info("Load part list page");
        model.addAttribute("partList", partService.loadAllParts());
        model.addAttribute("port", port);
        if (authentication != null) model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("environmentName", Arrays.toString(environment.getActiveProfiles()));
        return "client/part-list";
    }

    @PostMapping
    public String createPart (@ModelAttribute PartDto part, RedirectAttributes redirectAttributes) {
        LOGGER.info("Create new part: {}", part);
        try {
            partService.createPart(part);
        } catch (PartServiceException error) {
            redirectAttributes.addFlashAttribute("error", error);
        } catch (Exception systemError) {
            redirectAttributes.addFlashAttribute("systemError", systemError);
        }
        return "redirect:/client/part";
    }

    @PutMapping
    public String updatePart (@ModelAttribute PartDto part, RedirectAttributes redirectAttributes) {
        LOGGER.info("Update part: {}", part);
        try {
            partService.updatePart(part);
        } catch (PartServiceException error) {
            redirectAttributes.addFlashAttribute("error", error);
        } catch (Exception systemError) {
            redirectAttributes.addFlashAttribute("systemError", systemError);
        }
        return "redirect:/client/part";
    }

    @GetMapping("/{id}")
    public String deletePart (@PathVariable Long id, RedirectAttributes redirectAttributes) {
        LOGGER.info("Delete part: {}", id);
        try {
            partService.deletePart(id);
        } catch (PartServiceException error) {
            redirectAttributes.addFlashAttribute("error", error);
        } catch (Exception systemError) {
            redirectAttributes.addFlashAttribute("systemError", systemError);
        }
        return "redirect:/client/part";
    }
}
