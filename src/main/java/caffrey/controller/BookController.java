package caffrey.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import caffrey.bean.Book;
import caffrey.bean.Msg;
import caffrey.service.BookService;


@Controller
public class BookController {

	@Autowired
	BookService bookservice;
	
	@ResponseBody
	@RequestMapping(value="listBook", method=RequestMethod.GET)
	public Msg listBook(@RequestParam(value="pn", defaultValue="1") int pageNumber, boolean isAdm, 
			String name, 
			HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("pageNumber: " + pageNumber);
		
		PageHelper.startPage(pageNumber, 5);
		List<Book> books = bookservice.getAllBooks();
		for (Book b : books) {
			System.out.println(b);
		}
		PageInfo<Book> pageinfo = new PageInfo<>(books, 5);
		
		Msg msg = Msg.Success();
		msg.addObjToList("book", pageinfo);
		return msg;
	}
	
	@RequestMapping(value="bookPage", method=RequestMethod.GET)
	public String bookPage() {
		
		return "list";
	}
	
	@RequestMapping(value="checkBook", method=RequestMethod.GET)
	public String bookPage(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(request.getSession().getAttribute("isLogin"));
		
		return "list";
	}
}
































