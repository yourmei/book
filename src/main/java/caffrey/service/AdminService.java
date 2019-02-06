package caffrey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.AdminExample;
import caffrey.bean.AdminExample.Criteria;
import caffrey.dao.AdminMapper;

@Service
public class AdminService {

	@Autowired
	AdminMapper adminMapper;

	public boolean checkLogin(String name, String password) {
		
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdmNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		
		if(adminMapper.countByExample(example) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
