package caffrey.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {
	
	@Test
	public void testDate() throws ParseException {
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyy-mmm-ddd hh");
		
		Date d1 = sFormat.parse("2017-12-21 17");
		Date d2 = sFormat.parse("2017-12-11 17");
		
		long day1 = d1.getTime();
		System.out.println(day1);
		long day = (d1.getTime() - d2.getTime())/(60*60*24*1000);
		
		System.out.println(day);
	}
	
}
