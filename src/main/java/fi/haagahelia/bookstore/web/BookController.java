package fi.haagahelia.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@Controller
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/index")
    public String showBookstore() {
        return "Welcome to the Bookstore!";
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(Book book, Model model) {
        repository.save(book);
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @GetMapping("/deletebook/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Model model) {
        repository.deleteById(bookId);
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
}
