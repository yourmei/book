package caffrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdmController {
	
	@RequestMapping("AdmControllertest")
	public String test()
	{
		System.out.println("testSuccess");
		
		return "testSuccess";
	}
	
}
