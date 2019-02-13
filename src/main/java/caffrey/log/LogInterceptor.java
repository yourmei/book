package caffrey.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component(value="logger")
public class LogInterceptor {
	//private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	
	
	
	@Pointcut("@annotation(caffrey.annotation.Log)")
	public void controllerAspect() {}
	
	public LogInterceptor() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("LogInterceptor constructor");
	}

	//@Before("controllerAspect()")
	@Before(value="execution(* caffrey.test.Arithmetic.add(int, int))")
	public void beforeAdvice(JoinPoint joinPoint)
	{
		String method = joinPoint.getSignature().getName();
		System.out.println("Log: " + method);
	}
}
