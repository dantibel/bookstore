package fi.haagahelia.bookstore;

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
			categoryRepo.save(new Category("Documentary"));
			categoryRepo.save(new Category("Science Fiction"));
			categoryRepo.save(new Category("Autobiography"));
			categoryRepo.save(new Category("Satire"));
			bookRepo.save(new Book("Animal Farm",        "George Orwell",    1945, categoryRepo.findByName("Satire").get(),          "2212343-5",  30));
			bookRepo.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, categoryRepo.findByName("Autobiography").get(),   "1232323-21", 20));
			bookRepo.save(new Book("The Invincible",     "Stanislaw Lem",    1963, categoryRepo.findByName("Science Fiction").get(), "12345678-9", 20));
		};
	}

}
