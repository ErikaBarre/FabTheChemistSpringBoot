package fab.the.chemist.springbootweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class BooksConstroller {

	@GetMapping("/books")
	public List<Book> getAllBook(){
		return Arrays.asList(new Book(2L,"xxx","yyyy"));
		
	}
	
}
