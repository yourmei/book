package caffrey.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {

	int opCode;					//100:success 200:fail
	String message;
	Map<String, Object> list = new HashMap<String, Object>();

	public int getOpCode() {
		return opCode;
	}

	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getList() {
		return list;
	}

	public void setList(Map<String, Object> list) {
		this.list = list;
	}

	public void addObjToList(String name, Object obj)
	{
		list.put(name, obj);
	}
	
	public static Msg Success()
	{
		Msg msg = new Msg();
		msg.setOpCode(100);
		
		return msg;
	}
	
	public static Msg Fail()
	{
		Msg msg = new Msg();
		msg.setOpCode(200);
		
		return msg;
	}
}
