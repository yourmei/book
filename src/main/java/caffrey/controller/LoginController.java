package caffrey.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.HttpResource;

import caffrey.annotation.Log;
import caffrey.annotation.Login;
import caffrey.bean.LoginMsg;
import caffrey.bean.Msg;
import caffrey.dao.AdminMapper;
import caffrey.dao.VipMapper;
import caffrey.service.AdminService;
import caffrey.service.VipService;

@Controller
public class LoginController {

	@Autowired
	AdminService adminservice;
	
	@Autowired 
	VipService vipservice;
	
	@ResponseBody
	@RequestMapping(value="logOut", method=RequestMethod.GET)
	public Msg logOut(HttpServletRequest request)
	{
		System.out.println("logOut");
		request.getSession().setAttribute("isLogin", false);
		
		return Msg.Success();
	}
	
	@ResponseBody
	@RequestMapping(value="login", method=RequestMethod.GET)
	public Msg login(HttpServletRequest request, String name, String password, boolean isAdmin)
	{
		LoginMsg loginMsg = null;

		if(isAdmin == true)
		{
			loginMsg = adminservice.checkLogin(name, password);
		}
		else
		{
			loginMsg = vipservice.checkLogin(name, password);
		}
		
		
		if(loginMsg.getCode() == 1)
		{
			Msg msg = Msg.Success();
			msg.addObjToList("name", name);
			msg.addObjToList("id", loginMsg.getId());
			msg.addObjToList("isAdmin", isAdmin);
			
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("id", loginMsg.getId());
			session.setAttribute("name", name);
			return msg;
		}
		else
		{
			Msg msg = Msg.Fail();
			
			String message;
			if(loginMsg.getCode() == -2)
			{
				message = "’À∫≈±ªÀ¯";
			}
			else
			{
				message = "√‹¬Î¥ÌŒÛ";
			}
			
			msg.setMessage(message);
			return msg;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="signin", method=RequestMethod.POST)
	public Msg signIn(String name, String password, String email)
	{
		System.out.println("signin");
		System.out.println(name);
		System.out.println(password);
		System.out.println(email);
		
		return Msg.Success(); 
	}
	
	@RequestMapping(value="loginPage", method=RequestMethod.GET)
	public String loginPage()
	{
		return "loginPage";
	}
}




















































