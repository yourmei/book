package caffrey.test;

import org.springframework.stereotype.Component;

import caffrey.annotation.Log;
import caffrey.annotation.Login;

@Component
public class Arithmetic {
	
	@Login
	public int add(int i, int j)
	{
		return j+i;
	}
	
}
