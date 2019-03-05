package caffrey.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import caffrey.dao.LoginItemMapper;

@Service
public class LoginService {

	@Autowired
	LoginItemMapper loginItemMapper;
	
	public boolean checkIsVipIdLock(Integer vipId)
	{
		Date nowDate = new Date();
		Long time = nowDate.getTime() - 1000*60*1;//20 min
		//查询规定时间内最后一次登录成功记录的时间
		Long timeSuccess = loginItemMapper.seleteLastLoginSuccessTimeAfterTime(1, time);
		//System.out.println(timeSuccess);
		
		Integer cntInteger;
		//存在最后一次成功记录
		if(timeSuccess != null)
		{
			//has success ever
			cntInteger = loginItemMapper.selectCntForLoginFailAfterTime(vipId, timeSuccess);
			//System.out.println("has suceess!");
		}
		//不存在最后一次成功记录
		else {
			//never success
			cntInteger = loginItemMapper.selectCntForLoginFailAfterTime(vipId, time);
			//System.out.println("never success!");
		}
		
		//System.out.println(cntInteger);
		if(cntInteger >= 5)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
}
