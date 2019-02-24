package caffrey.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import caffrey.bean.Book;
import caffrey.bean.Msg;
import caffrey.bean.ShoppingCarItem;
import caffrey.service.BookService;
import caffrey.service.ShoppingCarService;

@Controller
public class ShoppingCarController {

	@Autowired
	ShoppingCarService shoppingcarservice;
	
	@Autowired
	BookService bookservice;
	
	@ResponseBody
	@RequestMapping(value="updateShoppingCarByItemId", method=RequestMethod.GET)
	public Msg updateShoppingCarByItemId(@RequestParam("ItemId") int ItemId, @RequestParam("number") int number)
	{
		System.out.println("updateShoppingCarByItemId " + "ItemId: " + ItemId + " number: " + number);
		shoppingcarservice.updateShoppingCarByItemId(ItemId, number);
		
		return Msg.Success();
	}
	
	/**
	 * @param request
	 * @param bookId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="AddShoppingCar", method=RequestMethod.GET)
	public Msg addShoppingCar(HttpServletRequest request, @RequestParam("bookId") int bookId)
	{
		HttpSession session = request.getSession();
		
		if((session.getAttribute("isLogin") == null) || (boolean)session.getAttribute("isLogin") != true)
		{
		//��δ��¼
			if(session.getAttribute("cart") == null)
			{
				//û�й��ﳵ
				Map<Integer, ShoppingCarItem> cart = new HashMap<>();
				ShoppingCarItem shoppingCarItem = new ShoppingCarItem();
				shoppingCarItem.setBookId(bookId);
				shoppingCarItem.setNumber(1);
				Book book = bookservice.getBookById(bookId);
				shoppingCarItem.setBookAuthor(book.getBookAuthor());
				shoppingCarItem.setBookName(book.getBookName());
				shoppingCarItem.setPrice(book.getPrice());
				shoppingCarItem.setStatus(false);
				cart.put(bookId, shoppingCarItem);
				session.setAttribute("cart", cart);
				System.out.println(cart);
			}
			else
			{
				//�й��ﳵ
				Map<Integer, ShoppingCarItem> cart = (Map<Integer, ShoppingCarItem>) session.getAttribute("cart");
				if(cart.containsKey(bookId))
				{
					//�и�ѡ��
					ShoppingCarItem shoppingCarItem = cart.get(bookId);
					shoppingCarItem.setNumber(shoppingCarItem.getNumber()+1);
					System.out.println(cart);
				}
				else
				{
					//û�и�ѡ��
					ShoppingCarItem shoppingCarItem = new ShoppingCarItem();
					shoppingCarItem.setBookId(bookId);
					shoppingCarItem.setNumber(1);
					Book book = bookservice.getBookById(bookId);
					shoppingCarItem.setBookAuthor(book.getBookAuthor());
					shoppingCarItem.setBookName(book.getBookName());
					shoppingCarItem.setPrice(book.getPrice());
					shoppingCarItem.setStatus(false);
					cart.put(bookId, shoppingCarItem);
					System.out.println(cart);
				}
				//System.out.println(cart);
			}
			
		}
		else
		{
		//�Ѿ���¼
			int vipIdInReq = Integer.parseInt(request.getParameter("vipId"));
			System.out.println("vipIdInReq: " + vipIdInReq + " bookId: " + bookId);
			shoppingcarservice.addShoppingItemById(vipIdInReq, bookId);
		}
		
		
		return Msg.Success();
	}
	
	@RequestMapping(value="ShoppingCar", method=RequestMethod.GET)
	public String goToShoppingCar(HttpServletRequest request, ModelMap map)
	{
		HttpSession session = request.getSession();
		if((session.getAttribute("isLogin") == null) || (boolean)session.getAttribute("isLogin") != true)
		{
			
		}
		else
		{
			String name; 
			int id;
			name = (String) request.getSession().getAttribute("name");
			id = (int) request.getSession().getAttribute("id");
			
			System.out.println("goToShoppingCar name: " + name + " id: " + id);
			
			map.addAttribute("name", name);
			map.addAttribute("id", id);
		}
		
		return "shoppingcar";
	}
	
	@ResponseBody
	@RequestMapping(value="getShoppingCarItemByVipId", method=RequestMethod.GET)
	public Msg getShoppingCarItemByVipId(HttpServletRequest request, int id)
	{
		List<ShoppingCarItem> items;
		System.out.println(id);
		if(id == 0)
		{
			Map<Integer, ShoppingCarItem> cart = (Map<Integer, ShoppingCarItem>) request.getSession().getAttribute("cart");
			System.out.println(cart);
			items = new LinkedList<>();
			for(Integer index : cart.keySet())
			{
				ShoppingCarItem item = cart.get(index);
				System.out.println("�ο͹��ﳵ��" + item);
				items.add(item);
			}
		}
		else
		{
			boolean hasSame = false;
			//�ο͹��ﳵ
			Map<Integer, ShoppingCarItem> cartForCust = (Map<Integer, ShoppingCarItem>) request.getSession().getAttribute("cart");
			//vip���ﳵ
			items = shoppingcarservice.getShoppingCarItemByVipId(id);
			System.out.println("�ο͹��ﳵ");
			System.out.println(cartForCust);
			System.out.println("vip���ﳵ");
			System.out.println(items);
			//items = new LinkedList<>();
			if(cartForCust != null)
			{
				for(Integer index : cartForCust.keySet())
				{
					hasSame = false;
					//��ȡ�ο͹��ﳵitem
					ShoppingCarItem item = cartForCust.get(index);
					System.out.println("vipid: " + id + " �ο͹��ﳵ��" + item);
					//items.add(item);
					for (ShoppingCarItem shoppingCarItem : items) {
						System.out.println("�Ƚϣ� " + item.getBookId() + shoppingCarItem.getBookId() + shoppingCarItem.getStatus());
						if(item.getBookId() == shoppingCarItem.getBookId() && shoppingCarItem.getStatus() == false && shoppingCarItem.getNumber() != 0)
						{
							//����ͬ�����ο͹��ﳵ�Ḳ��vip���ﳵ
							System.out.println("����" + item);
							shoppingCarItem.setNumber(item.getNumber());
							shoppingcarservice.updateShoppingCarByItemId(shoppingCarItem.getItemId(), item.getNumber());
							hasSame = true;
							break;
						}
					}
					System.out.println("hasSame : " + hasSame);
					if(hasSame == false)
					{
						//û����ͬ��������ο͹��ﳵ
						System.out.println("����µģ� " + item);
						shoppingcarservice.addShoppingItemById(id, item.getBookId());
						List<ShoppingCarItem> tmpCart = shoppingcarservice.getShoppingCarItemByBookId(item.getBookId());
						shoppingcarservice.updateShoppingCarByItemId(tmpCart.get(0).getItemId(), item.getNumber());
						item.setItemId(tmpCart.get(0).getItemId());
						items.add(item);
					}

				}
				
				request.getSession().removeAttribute("cart");
			}
		}
		
		System.out.println("before return");
		System.out.println(items);
		Msg msg = Msg.Success();
		msg.addObjToList("shoppingitems", items);
		
		return msg;
	}
}
























