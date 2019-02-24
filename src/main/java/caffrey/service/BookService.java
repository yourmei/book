package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.Book;
import caffrey.dao.BookMapper;
import caffrey.exception.NotEnoughStockException;

@Service
public class BookService {

	@Autowired
	BookMapper bookMapper;

	public List<Book> getAllBooks() {
		return bookMapper.selectByExample(null);
	}
	
	public Book getBookById(Integer id)
	{
		Book book = bookMapper.selectByPrimaryKey(id);
		return book;
	}
	
	public void updateBookStock(Integer bookId, Integer cnt, boolean isAdd)
	{
		int stock = bookMapper.selectByPrimaryKey(bookId).getStock();
		if(isAdd == false)
		{
			if(stock >= cnt)
			{
				stock -= cnt;
				bookMapper.updateStockByBookId(bookId, stock);
			}
			else
			{
				throw new NotEnoughStockException();
			}
		}
		else
		{
			stock += cnt;
			bookMapper.updateStockByBookId(bookId, stock);
		}
	}
}
