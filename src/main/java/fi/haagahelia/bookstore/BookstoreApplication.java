package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("A Tale of Two Cities", "Charles Dickens", 1998, 9780486406510L, 6.00));
			repository.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2005, 9780544003415L, 17.49));
			repository.save(new Book("The Little Prince", "Antoine de Saint-Exupery", 2013, 9788478886296L, 6.95));
			repository.save(new Book("The Da Vinci Code", "Dan Brown", 2009, 9780385504201L, 8.99));
			repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1991, 9788420674209L, 7.19));
			repository.save(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 2006, 9780241971826L, 11.39));
			repository.save(new Book("When Breath Becomes Air", "Paul Kalanithi", 2016, 9781784701994L, 15.41));

			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
