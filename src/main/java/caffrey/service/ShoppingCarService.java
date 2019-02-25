package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caffrey.bean.ShoppingCarItem;
import caffrey.bean.ShoppingCarItemExample;
import caffrey.bean.ShoppingCarItemExample.Criteria;
import caffrey.dao.BookMapper;
import caffrey.dao.ShoppingCarItemMapper;
import caffrey.dao.VipMapper;

@Service
public class ShoppingCarService {

	@Autowired
	ShoppingCarItemMapper shoppingCarItemMapper;

	@Autowired
	VipMapper vipMapper;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	VipService vipService;
	
	@Autowired
	BookService bookService;
	
	
	public void addShoppingItemById(int vipId, int bookId) {
		// TODO Auto-generated method stub
		
		ShoppingCarItemExample example = new ShoppingCarItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andVipIdEqualTo(vipId);
		criteria.andBookIdEqualTo(bookId);
		List<ShoppingCarItem> shoppingcaritems = shoppingCarItemMapper.selectByExample(example);
		for (ShoppingCarItem shoppingCarItem2 : shoppingcaritems) {
			System.out.println(shoppingCarItem2);
		}
		
		if((shoppingcaritems.isEmpty()) || (shoppingcaritems.get(0).getStatus() == true))
		{
			ShoppingCarItem record = new ShoppingCarItem();
			record.setBookId(bookId);
			record.setVipId(vipId);
			record.setNumber(1);
			record.setStatus(false);
			shoppingCarItemMapper.insert(record);
		}
		else
		{
			ShoppingCarItem record = shoppingcaritems.get(0);
			record.setNumber(record.getNumber()+1);
			shoppingCarItemMapper.updateByPrimaryKey(record);
		}
	}

	public List<ShoppingCarItem> getShoppingCarItemByVipId(int id) {
		
		List<ShoppingCarItem> items = shoppingCarItemMapper.selectByVipId(id);
		
		return items;
	}

	public void updateShoppingCarByItemId(int item_id, int cnt) {
		// TODO Auto-generated method stub
		shoppingCarItemMapper.updateItemNumberByItem_id(item_id, cnt);
	}

	public List<ShoppingCarItem> getShoppingCarItemByBookId(Integer bookId) {
		
		ShoppingCarItemExample example = new ShoppingCarItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andBookIdEqualTo(bookId);
		return shoppingCarItemMapper.selectByExample(example);
		
	}
	
	public void mergeCarItemsWtithVipid(Integer VipId, List<ShoppingCarItem> items)
	{
		List<ShoppingCarItem> itemsInVip = shoppingCarItemMapper.selectByVipId(VipId);
		
		
	}
	
	@Transactional
	public void purchAnItem(Integer vipId, Integer itemId) {
		// TODO Auto-generated method stub
		ShoppingCarItem shoppingCarItem = shoppingCarItemMapper.selectByPrimaryKey(itemId);
		System.out.println(shoppingCarItem);
		if(shoppingCarItem.getStatus() == false)
		{
			int totalPrice;
			int cnt;
			
			cnt = shoppingCarItem.getNumber();
			totalPrice = cnt * shoppingCarItem.getPrice();
			
			System.out.println("step 1");
			vipService.updateVipMoney(vipId, totalPrice, true);
			System.out.println("step 2");
			bookService.updateBookStock(shoppingCarItem.getBookId(), cnt, false);
			System.out.println("step 3");
			shoppingCarItemMapper.updateItemStatusByItem_id(shoppingCarItem.getItemId());
		}
	}
	
	@Transactional
	public void purchItemsBatch(Integer vipId, String[] itemId_str)
	{
		for (String itemstr : itemId_str) {
			Integer itemId = Integer.parseInt(itemstr);
			ShoppingCarItem shoppingCarItem = shoppingCarItemMapper.selectByPrimaryKey(itemId);
			
			System.out.println(shoppingCarItem);
			
			if(shoppingCarItem.getStatus() == false)
			{
				int totalPrice;
				int cnt;
				
				cnt = shoppingCarItem.getNumber();
				totalPrice = cnt * shoppingCarItem.getPrice();
				
				vipService.updateVipMoney(vipId, totalPrice, true);
				bookService.updateBookStock(shoppingCarItem.getBookId(), cnt, false);
				shoppingCarItemMapper.updateItemStatusByItem_id(shoppingCarItem.getItemId());
			}
		}
	}
	
	
	@Transactional
	public void purchaseAllItems(Integer vipId) {
		// TODO Auto-generated method stub

		List<ShoppingCarItem> items = shoppingCarItemMapper.selectByVipId(vipId);
		for (ShoppingCarItem shoppingCarItem : items)
		{
			if(shoppingCarItem.getStatus() == false)
			{
				int totalPrice;
				int cnt;
				
				cnt = shoppingCarItem.getNumber();
				totalPrice = cnt * shoppingCarItem.getPrice();
				
				vipService.updateVipMoney(vipId, totalPrice, true);
				bookService.updateBookStock(shoppingCarItem.getBookId(), cnt, false);
				shoppingCarItemMapper.updateItemStatusByItem_id(shoppingCarItem.getItemId());
			}
		}
	}
	
	
}
