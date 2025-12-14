package com.haingue.tp1.CommunityBookstore.controller;

import com.haingue.tp1.CommunityBookstore.service.BorrowingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/administrator")
public class AdministratorController {

    private final BorrowingService borrowingService;

    public AdministratorController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/dashboard")
    public String administratorHomePage (Model model) {
        model.addAttribute("borrowings", borrowingService.getBorrowings(0, 50).content());
        return "administrator/administrator-dashboard";
    }

}
