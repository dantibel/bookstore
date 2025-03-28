package fi.haagahelia.bookstore.web;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    private BookRepository bookRepo;
    private CategoryRepository categoryRepo;

    public BookController(BookRepository bookRepo, CategoryRepository categoryRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
    }
    
    @GetMapping("/")
    public String defaultEndpoint() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/index")
    public String showBookstore() {
        return "Welcome to the Bookstore!";
    }

    @GetMapping("/booklist")
    public String showBookList(Model model, Principal principal) {
        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("username", principal.getName());
        return "booklist";
    }
    
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepo.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(Book book, Model model) {
        bookRepo.<Book>save(book);
        model.addAttribute("books", bookRepo.findAll());
        return "redirect:/booklist";
    }

    @GetMapping("/deletebook/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Model model) {
        bookRepo.deleteById(bookId);
        model.addAttribute("books", bookRepo.findAll());
        return "redirect:/booklist";
    }

    @GetMapping("/editbook/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (!optionalBook.isPresent()) {
            System.out.println("Error: Boook #" + bookId + " not found. It may have been deleted.");
            return "redirect:/booklist";
        }
        model.addAttribute("book", optionalBook.get());
        model.addAttribute("categories", categoryRepo.findAll());
        return "addbook";
    }

}
