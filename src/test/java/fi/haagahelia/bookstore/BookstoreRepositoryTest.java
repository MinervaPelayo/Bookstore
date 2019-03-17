package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Watership Down", "Richard Adams", 1972, 9780743277709L, 17.09, crepository.findByName("Novel").get(0));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	@Test
	public void deleteBook() {
		Book book= brepository.findByYear(1998);
		brepository.deleteById(((Book) book).getId());
	}
	@Test
	public void searchBook(){
		Book book = brepository.findByYear(2016);
		assertThat(((Book) book).getPrice()).isEqualTo(15.41);
	}
	@Test
	public void createUser(){
		User user= new User("minerva", "$2a$04$7KsHcQjGaM99aZ6UaSa3O.rLB8huuz1A776Cq7.GCptZCqcftxKlC","user3@gmail.com", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser(){
		User user= urepository.findByUsername("user");
		urepository.deleteById(user.getId());
	}
	@Test
	public void searchUser(){
		User user = urepository.findByUsername("user");
		assertThat(user.getRole()).isEqualTo("USER");
	}
	
	@Test
	public void createCategory(){
		Category category= new Category("fiction");
		crepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCategory(){
		List<Category> category= crepository.findByName("Fantasy");
		crepository.deleteById((category.get(0).getCategoryid()));
	}
	@Test
	public void searchCategory(){
		List<Category> category=crepository.findByName("Novel");
		assertThat(category.get(0).getCategoryid()).isNotNull();
	}
	
}
