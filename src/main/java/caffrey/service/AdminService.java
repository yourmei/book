package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.bean.Admin;
import caffrey.bean.AdminExample;
import caffrey.bean.AdminExample.Criteria;
import caffrey.dao.AdminMapper;

@Service
public class AdminService {

	@Autowired
	AdminMapper adminMapper;

	public Integer checkLogin(String name, String password) {
		Integer id;
		
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdmNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		
		List<Admin> admins = adminMapper.selectByExample(example);
		
		if(admins.isEmpty() == false)
		{
			System.out.println(admins.get(0));
			id = admins.get(0).getAdmId();
			return id;
		}
		else
		{
			return 0;
		}
		
	}
}
