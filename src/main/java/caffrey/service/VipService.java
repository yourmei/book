package caffrey.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.annotation.Login;
import caffrey.bean.LoginMsg;
import caffrey.bean.Vip;
import caffrey.bean.VipExample;
import caffrey.bean.VipExample.Criteria;
import caffrey.dao.VipMapper;
import caffrey.exception.BalanceNoEnoughException;

@Service
public class VipService {

	@Autowired
	VipMapper vipMapper;

	@Autowired
	LoginService loginservice;
	
	@Login
	public LoginMsg checkLogin(String name, String password) {
		LoginMsg loginMsg = new LoginMsg();
		Integer id = null;
		String passwIndb = null;
		VipExample vipExample = new VipExample();
		Criteria criteria = vipExample.createCriteria();
		criteria.andVipNameEqualTo(name);
		
		List<Vip> vips = vipMapper.selectByExample(vipExample);
		
		//账号存在
		if(vips.isEmpty() == false)
		{
			id = vips.get(0).getVipId();
			
			if(loginservice.checkIsVipIdLock(id) == true)
			{
				//被锁住了，不用记录信息
				loginMsg.setCode(-2);
				return loginMsg;
			}
			else 
			{
				//没有被锁，验证密码是否正确
				passwIndb = vips.get(0).getPassword();
				if(passwIndb.equals(password) == true)
				{
					//密码正确
					id = vips.get(0).getVipId();
					loginMsg.setCode(1);
					loginMsg.setId(id);
					loginMsg.setName(name);
				}
				else 
				{
					//密码错误
					loginMsg.setCode(0);
					loginMsg.setId(id);
				}
			}
		}
		
		//账号不存在
		return loginMsg;
	}
	
	public void updateVipMoney(Integer vipId, Integer money, boolean isPurchase)
	{
		int balance = vipMapper.selectByPrimaryKey(vipId).getBalance();
		if(isPurchase == true)
		{
			if(balance < money)
			{
				throw new BalanceNoEnoughException();
			}
			else
			{
				balance -= money;
				vipMapper.updateBalanceByVipId(vipId, balance);
			}
		}
		else
		{
			vipMapper.updateBalanceByVipId(vipId, balance + money);
		}
	}
}
