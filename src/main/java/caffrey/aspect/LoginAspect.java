package caffrey.aspect;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import caffrey.bean.LoginItem;
import caffrey.bean.LoginMsg;
import caffrey.dao.LoginItemMapper;

@Aspect
@Component
public class LoginAspect {

	@Autowired
	LoginItemMapper loginitemmapper;
	
	@Pointcut("@annotation(caffrey.annotation.Login)")
	public void aspectPointCut() {}
	
	
	@Around(value = "aspectPointCut()")
	public Object loginAroundActive(ProceedingJoinPoint pdJoinPoint)
	{
		Object returnObject = null;
		Integer code = 0;
		LoginMsg loginMsg = null;
		try {

			returnObject = pdJoinPoint.proceed();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginItem loginItem = new LoginItem();
		Date date = new Date();
		loginItem.setTime(date.getTime());
		loginMsg = (LoginMsg) returnObject;
		code = loginMsg.getCode();
		if(code == 0)
		{
			loginItem.setStatus(false);
		}
		else if(code == 1){
			loginItem.setStatus(true);
		}
		
		loginItem.setVipId(loginMsg.getId());
		
		if(code == 0)
		{
			loginitemmapper.insert(loginItem);
		}
		else if(code == 1){
			loginitemmapper.insert(loginItem);
		}
		return returnObject; 
	}
	
}
