package caffrey.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import caffrey.bean.Msg;
import caffrey.dao.BookMapper;
import caffrey.dao.ShoppingCarItemMapper;
import caffrey.dao.VipMapper;
import caffrey.service.ShoppingCarService;

@Controller
public class OperationController {

	@Autowired
	VipMapper vipMapper;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	ShoppingCarItemMapper shoppingcaritemmapper;
	
	@Autowired
	ShoppingCarService shoppingcarservice;
	
	@ResponseBody
	@RequestMapping(value="purchase", method=RequestMethod.GET)
	public Msg purchase(HttpServletRequest request, HttpServletResponse response, @RequestParam("vipId") Integer vipId, String items) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		System.out.println("vipId: " + vipId + " items: " + items);
		if((session.getAttribute("isLogin") == null) || (boolean)session.getAttribute("isLogin") != true)
		{
			//没有登录
			System.out.println("not login");
			System.out.println(request.getRequestURI());
			session.setAttribute("pageAfterLogin", "ShoppingCar");
			//request.getRequestDispatcher("WEB-INF/views/loginPage.jsp").forward(request,response); 
			Msg msg = Msg.Fail();
			String url = "loginPage";
			msg.setOpCode(300);
			msg.addObjToList("url", url);
			
			return msg;
		}
		else
		{
			//已经登录
			Integer idInSession = (Integer) request.getSession().getAttribute("id");
			
			String[] item_str = items.split("-");
			for (String string : item_str) {
				System.out.println(string);
			}
			
			System.out.println("idInSession " + idInSession);
			
			if(item_str.length == 1)
			{
				System.out.println("cnt: 1 " + item_str.length);
				try {
					System.out.println("item: " + Integer.parseInt(item_str[0]) );
					shoppingcarservice.purchAnItem(vipId, Integer.parseInt(item_str[0]));
				} catch (Exception e) {
					// TODO: handle exception
					return Msg.Fail();
				}
			}
			else
			{
				System.out.println("cnt: " + item_str.length);
				try {
					shoppingcarservice.purchItemsBatch(vipId, item_str);
				} catch (Exception e) {
					// TODO: handle exception
					return Msg.Fail();
				}
			}
		}
		
		return Msg.Success();
	}
}


