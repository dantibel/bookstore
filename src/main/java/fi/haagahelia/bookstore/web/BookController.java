package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/index")
    public String showBookstore() {
        return "Welcome to the Bookstore!";
    }

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepo.findAll());
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
        if (!bookRepo.save(book)) {
            System.err.println("Failed to save " + book);
        }
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

    // RESTful service methods

    @GetMapping(value="/books")
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepo.findAll();
    }    

    @GetMapping(value="/books/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepo.findById(bookId);
    }

}
