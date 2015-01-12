package firework.hyl.running.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.exception.MemberServiceException;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

@Controller("memberLoginAction")
@Scope("prototype")
public class MemberLoginAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMemberService service;
	private Map<String, Object> session;

	private String username;
	private String password;
	private String authCode;
	private String msg = "登陆成功";

	private String autoLogin;

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	@Override
	public String execute() throws Exception {
		Memberinfo memberinfo = null;
		String rand = (String) session.get("authCode");
		if (!rand.equals(this.authCode)) {
			this.msg = "验证码不正确";
			return ERROR;
		}
		try {
			memberinfo = this.service.login(username, password);
			if (memberinfo == null) {
				System.out.println("22222");
				this.msg = "用户名或密码不正确";
				return ERROR;
			}
		} catch (MemberServiceException e) {
			e.printStackTrace();
			this.msg = e.getMessage();
			return ERROR;
		}
		this.session.put(GloobalProperties.CURRENT_USER, memberinfo);
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
