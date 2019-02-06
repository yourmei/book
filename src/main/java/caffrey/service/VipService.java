package caffrey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.VipExample;
import caffrey.bean.VipExample.Criteria;
import caffrey.dao.VipMapper;

@Service
public class VipService {

	@Autowired
	VipMapper vipMapper;

	public boolean checkLogin(String name, String password) {

		VipExample vipExample = new VipExample();
		Criteria criteria = vipExample.createCriteria();
		criteria.andVipNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		if(vipMapper.countByExample(vipExample) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
