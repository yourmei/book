package caffrey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import caffrey.bean.Msg;
import caffrey.bean.ShoppingCarItem;
import caffrey.service.ShoppingCarService;

@Controller
public class ShoppingCarController {

	@Autowired
	ShoppingCarService shoppingcarservice;
	
	@ResponseBody
	@RequestMapping(value="updateShoppingCarByItemId", method=RequestMethod.GET)
	public Msg updateShoppingCarByItemId(@RequestParam("ItemId") int ItemId, @RequestParam("number") int number)
	{
		System.out.println("updateShoppingCarByItemId " + "ItemId: " + ItemId + " number: " + number);
		shoppingcarservice.updateShoppingCarByItemId(ItemId, number);
		
		return Msg.Success();
	}
	
	@ResponseBody
	@RequestMapping(value="AddShoppingCar", method=RequestMethod.GET)
	public Msg addShoppingCar(@RequestParam("vipId") int vipId, @RequestParam("bookId") int bookId)
	{
		System.out.println("vipId: " + vipId + " bookId: " + bookId);
		shoppingcarservice.addShoppingItemById(vipId, bookId);
		
		return Msg.Success();
	}
	
	@RequestMapping(value="ShoppingCar", method=RequestMethod.GET)
	public String goToShoppingCar(HttpServletRequest request, ModelMap map)
	{
		String name; 
		int id;
		name = (String) request.getSession().getAttribute("name");
		id = (int) request.getSession().getAttribute("id");
		
		System.out.println("goToShoppingCar name: " + name + " id: " + id);
		
		map.addAttribute("name", name);
		map.addAttribute("id", id);
		
		return "shoppingcar";
	}
	
	@ResponseBody
	@RequestMapping(value="getShoppingCarItemByVipId", method=RequestMethod.GET)
	public Msg getShoppingCarItemByVipId(int id)
	{
		List<ShoppingCarItem> items = shoppingcarservice.getShoppingCarItemByVipId(id);
		
		Msg msg = Msg.Success();
		msg.addObjToList("shoppingitems", items);
		
		return msg;
	}
}
























