package com.haingue.tp1.CommunityBookstore.controller;

import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.BorrowingDto;
import com.haingue.tp1.CommunityBookstore.service.BorrowingService;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/administrator")
public class AdministratorController {

    private final BorrowingService borrowingService;
    private final BookService bookService;

    public AdministratorController(BorrowingService borrowingService, BookService bookService) {
        this.borrowingService = borrowingService;
        this.bookService = bookService;
    }

    @GetMapping("/dashboard")
    public String administratorHomePage (Model model) {
        model.addAttribute("books", bookService.getAll(0, 50));
        model.addAttribute("borrowings", borrowingService.getBorrowings(0, 50));
        return "views/administrator/administrator-dashboard";
    }

    @PostMapping("/books/add")
    public String addNewBook (@ModelAttribute BookDto newBookDto, RedirectAttributes redirectAttributes) {
        try {
            bookService.create(newBookDto);
            redirectAttributes.addFlashAttribute("errorMessage", "Success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error to save book: "+e.getMessage());
        }
        return "redirect:/ui/administrator/dashboard";
    }

    @PostMapping("/books/remove")
    public String removeNewBook (@ModelAttribute BookDto bookDto, Model model) {
        bookService.delete(bookDto.isbn());
        return "redirect:/ui/administrator/dashboard";
    }

    @PostMapping("/books/return")
    public String removeNewBook (@ModelAttribute BorrowingDto borrowingBook, Model model) {
        borrowingService.returnBook(borrowingBook.uuid());
        return "redirect:/ui/administrator/dashboard";
    }

}
