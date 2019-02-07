package caffrey.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import caffrey.service.BookService;


@Controller
public class BookController {

	@Autowired
	BookService bookservice;
	
	@RequestMapping(value="listBook", method=RequestMethod.GET)
	public String listBook(@RequestParam(value="pn", defaultValue="1") int pageNumber, boolean isAdm, 
			String name, Map<String, Object> map, 
			HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("pageNumber: " + pageNumber);
		
		
		
		return "list";
	}
}
