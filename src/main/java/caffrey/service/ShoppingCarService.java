package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.ShoppingCarItem;
import caffrey.bean.ShoppingCarItemExample;
import caffrey.bean.ShoppingCarItemExample.Criteria;
import caffrey.dao.ShoppingCarItemMapper;

@Service
public class ShoppingCarService {

	@Autowired
	ShoppingCarItemMapper shoppingCarItemMapper;

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
	
	
}
