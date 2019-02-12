package caffrey.controller;

import java.util.List;

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
	public Msg purchase(@RequestParam("vipId") Integer vipId) 
	{
		try {
			shoppingcarservice.purchaseAllItems(vipId);
		} catch (Exception e) {
			// TODO: handle exception
			return Msg.Fail();
		}
		
		return Msg.Success();
	}
}
