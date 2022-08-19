package common;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//[교제 p.378]------------------------------------------------
@WebListener
public class LoginImpl implements HttpSessionBindingListener {
	private String id;
	private String name;
	public static int total_user =0;
	
    public LoginImpl() {
    }

    public LoginImpl(String id, String name) {
		this.id = id;
		this.name = name;
	}
    

    
    //아이디는 get만 넣는다.
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getTotal_user() {
		return total_user;
	}
	//리스너를 통해서 값을 변경할꺼니까 get만 넣는다.

	
	public void valueBound(HttpSessionBindingEvent event)  { //값을설정하면 여기 메소드 실행
		System.out.println("로그인");
		++total_user;
		System.out.println("현재 로그인 인원 : " + total_user);
    }

    public void valueUnbound(HttpSessionBindingEvent event)  { //해제하면 여기 매소드 실행 (session) 
    	System.out.println("로그아웃");
    	total_user--;
    	System.out.println("현재 로그인 인원 : " + total_user);
    }
	
}
