package fi.haagahelia.bookstore;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner demo(BookRepository bookRepo, CategoryRepository categoryRepo) {
        return (args) -> {
			if (categoryRepo.findAll().isEmpty()) {
				List<Category> categories = Arrays.asList(
					new Category("Science Fiction"),
					new Category("Autobiography"),
					new Category("Fantasy"),
					new Category("Satire"),
					new Category("Epic"),
					new Category("Romance"),
					new Category("Novel")
				);

				for (Category category : categories) {
					categoryRepo.<Category>save(category);
				}
			}

			if (bookRepo.findAll().isEmpty()) {
				List<Book> books = Arrays.asList(
					new Book("Animal Farm",          "George Orwell",     1945, categoryRepo.findByName("Satire").get(),          "2212343-5",  22),
    				new Book("A Farewell to Arms",   "Ernest Hemingway",  1929, categoryRepo.findByName("Autobiography").get(),   "1232323-21", 20),
    				new Book("The Invincible",       "Stanislaw Lem",     1963, categoryRepo.findByName("Science Fiction").get(), "12345678-9", 23),
    				new Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, categoryRepo.findByName("Novel").get(),           "3869026-44", 25),
    				new Book("Oddysey",              "Homer",             1614, categoryRepo.findByName("Epic").get(),            "22222222-2", 19)
				);
				
				for (Book book : books) {
					bookRepo.<Book>save(book);
				}
			}
		};
	}

}
