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
		Long timeSuccess = loginItemMapper.seleteLastLoginSuccessTimeAfterTime(1, time);
		//System.out.println(timeSuccess);
		
		Integer cntInteger;
		if(timeSuccess != null)
		{
			//has success ever
			cntInteger = loginItemMapper.selectCntForLoginFailAfterTime(1, timeSuccess);
			//System.out.println("has suceess!");
		}
		else {
			//never success
			cntInteger = loginItemMapper.selectCntForLoginFailAfterTime(1, time);
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
