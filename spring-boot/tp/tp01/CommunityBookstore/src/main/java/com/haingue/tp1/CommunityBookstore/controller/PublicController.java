package com.haingue.tp1.CommunityBookstore.controller;

import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/public")
public class PublicController {

    private final BookService bookService;

    public PublicController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/catalogue")
    public String bookListPage (Model model) {
        model.addAttribute("books", bookService.getAll(0, 50).content());
        return "public/book-catalogue";
    }

}
