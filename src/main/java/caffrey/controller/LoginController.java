package caffrey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value="login", method=RequestMethod.GET)
	public Msg login(String name, String password, boolean isAdmin)
	{
		Integer loginId = null;
		String id;
		System.out.println("login");
		System.out.println(name);
		System.out.println(password);
		System.out.println(isAdmin);
		if(isAdmin == true)
		{
			loginId = adminservice.checkLogin(name, password);
		}
		else
		{
			loginId = vipservice.checkLogin(name, password);
		}
		
		if(loginId != 0)
		{
			Msg msg = Msg.Success();
			msg.addObjToList("name", name);
			id = loginId.toString();
			System.out.println("ididid " + loginId.toString());
			msg.addObjToList("id", id);
			msg.addObjToList("isAdmin", isAdmin);
			return msg;
		}
		else
		{
			return Msg.Fail();
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
}




















































