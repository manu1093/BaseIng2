package database.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Login implements Bean{
	private String nickname;
	private String ora_log;
	private String data_log;
	
	public void setOraLogin() {
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.ora_log = dateFormat.format(d);
    }
	public void setDataLogin() {
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("YY:MM:dd");
        this.data_log = dateFormat.format(d);
    }
	public String getOraLogin() {
        return ora_log;
    }
	public String getDataLogin() {
        return data_log;
    }
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname=nickname;
	}
  public HashMap<String, String> toSpecial() {
       HashMap <String,String>m=new HashMap<String,String>();
       m.put("data", data_log);
       m.put("nickname", nickname);
       m.put("ora_log", ora_log);
       return m;
    }
	
	
}
