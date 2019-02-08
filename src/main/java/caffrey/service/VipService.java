package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.Vip;
import caffrey.bean.VipExample;
import caffrey.bean.VipExample.Criteria;
import caffrey.dao.VipMapper;

@Service
public class VipService {

	@Autowired
	VipMapper vipMapper;

	public Integer checkLogin(String name, String password) {
		Integer id = null;
		VipExample vipExample = new VipExample();
		Criteria criteria = vipExample.createCriteria();
		criteria.andVipNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		
		List<Vip> vips = vipMapper.selectByExample(vipExample);
		
		if(vips.isEmpty() == false)
		{
			id = vips.get(0).getVipId();
			
			return id;
		}
		else
		{
			return 0;
		}
	}
	
}
