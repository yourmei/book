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
		//��ѯ�涨ʱ�������һ�ε�¼�ɹ���¼��ʱ��
		Long timeSuccess = loginItemMapper.seleteLastLoginSuccessTimeAfterTime(1, time);
		//System.out.println(timeSuccess);
		
		Integer cntInteger;
		//�������һ�γɹ���¼
		if(timeSuccess != null)
		{
			//has success ever
			cntInteger = loginItemMapper.selectCntForLoginFailAfterTime(vipId, timeSuccess);
			//System.out.println("has suceess!");
		}
		//���������һ�γɹ���¼
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
