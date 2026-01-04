package com.haingue.tp1.CommunityBookstore.controller;

import com.haingue.tp1.CommunityBookstore.dto.BookSearch;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "views/public/book-catalogue";
    }

    @PostMapping("/books/catalogue/search")
    public String bookListPage (@ModelAttribute BookSearch searchedBook, Model model) {
        model.addAttribute("books", bookService.searchAll(searchedBook.searchedTitle(), searchedBook.searchedAuthor(), 0, 50).content());
        model.addAttribute("searchedTitle", searchedBook.searchedTitle());
        return "views/public/book-catalogue";
    }

}
