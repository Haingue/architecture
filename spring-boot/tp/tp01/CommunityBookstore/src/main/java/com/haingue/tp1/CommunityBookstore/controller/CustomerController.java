package com.haingue.tp1.CommunityBookstore.controller;


import com.haingue.tp1.CommunityBookstore.dto.BookDto;
import com.haingue.tp1.CommunityBookstore.dto.BorrowingRequestDto;
import com.haingue.tp1.CommunityBookstore.dto.ReturnRequestDto;
import com.haingue.tp1.CommunityBookstore.service.BorrowingService;
import com.haingue.tp1.CommunityBookstore.service.crud.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/ui/customer")
public class CustomerController {

    private final BookService bookService;
    private final BorrowingService borrowingService;

    public CustomerController(BookService bookService, BorrowingService borrowingService) {
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }

    @GetMapping("/books")
    private String showAllCustomerBooks(@RequestParam(required = false) UUID customerId, Model model) {
        model.addAttribute("borrowingBooks", borrowingService.getUserBorrowings(customerId, 0, 10));
        return "views/customer/borrowed-books";
    }

    @PostMapping("/books/return")
    public String bookCatalogue (@ModelAttribute ReturnRequestDto returnRequestDto, Model model) {
        borrowingService.returnBook(returnRequestDto.bookIsbn(), returnRequestDto.customerUuid());
        return "redirect:/ui/public/books/catalogue";
    }

    @PostMapping("/books/borrowing")
    public String borrowingBookPage (@ModelAttribute BorrowingRequestDto borrowingRequestDto, Model model) {
        borrowingService.borrowBook(borrowingRequestDto.bookIsbn(), borrowingRequestDto.customerUuid());
        return "redirect:/ui/public/books/catalogue";
    }
}
