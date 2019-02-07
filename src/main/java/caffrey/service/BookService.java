package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.Book;
import caffrey.dao.BookMapper;

@Service
public class BookService {

	@Autowired
	BookMapper bookMapper;

	public List<Book> getAllBooks() {
		return bookMapper.selectByExample(null);
	}
	
}
