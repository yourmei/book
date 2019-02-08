package caffrey.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import caffrey.bean.Admin;
import caffrey.bean.ShoppingCarItem;
import caffrey.dao.AdminMapper;
import caffrey.dao.ShoppingCarItemMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTestDao {
	
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	ShoppingCarItemMapper shoppingcaritemmapper;
	
	@Test
	public void test()
	{
		List<Admin> adms = adminMapper.selectByExample(null);
		for (Admin admin : adms) {
			System.out.println(admin);
		}
		
		Integer vipId = 2;
		List<ShoppingCarItem> items = shoppingcaritemmapper.selectByVipId(vipId);
		for (ShoppingCarItem shoppingCarItem : items) {
			System.out.println(shoppingCarItem);
		}
	}
	
}
