package caffrey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.dao.BookMapper;

@Service
public class BookService {

	@Autowired
	BookMapper bookMapper;
	
}
