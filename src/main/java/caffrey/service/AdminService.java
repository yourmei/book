package caffrey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.annotation.Login;
import caffrey.bean.Admin;
import caffrey.bean.AdminExample;
import caffrey.bean.AdminExample.Criteria;
import caffrey.bean.LoginMsg;
import caffrey.dao.AdminMapper;

@Service
public class AdminService {

	@Autowired
	AdminMapper adminMapper;

	public LoginMsg checkLogin(String name, String password) {
		LoginMsg loginMsg = new LoginMsg();
		String passwIndb;
		Integer returnInteger = 0;
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAdmNameEqualTo(name);
		
		List<Admin> admins = adminMapper.selectByExample(example);
		
		if(admins.isEmpty() == false)
		{
			passwIndb = admins.get(0).getPassword();
			if(password.equals(passwIndb) == true){
				returnInteger = admins.get(0).getAdmId();
				loginMsg.setCode(1);
				loginMsg.setId(returnInteger);
				loginMsg.setName(name);
			}
			else {
				loginMsg.setCode(0);
				loginMsg.setId(admins.get(0).getAdmId());
			}
		}
		
		return loginMsg;
	}
}
