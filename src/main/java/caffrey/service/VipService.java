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
		
		//�˺Ŵ���
		if(vips.isEmpty() == false)
		{
			id = vips.get(0).getVipId();
			
			if(loginservice.checkIsVipIdLock(id) == true)
			{
				//����ס�ˣ����ü�¼��Ϣ
				loginMsg.setCode(-2);
				return loginMsg;
			}
			else 
			{
				//û�б�������֤�����Ƿ���ȷ
				passwIndb = vips.get(0).getPassword();
				if(passwIndb.equals(password) == true)
				{
					//������ȷ
					id = vips.get(0).getVipId();
					loginMsg.setCode(1);
					loginMsg.setId(id);
					loginMsg.setName(name);
				}
				else 
				{
					//�������
					loginMsg.setCode(0);
					loginMsg.setId(id);
				}
			}
		}
		
		//�˺Ų�����
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
