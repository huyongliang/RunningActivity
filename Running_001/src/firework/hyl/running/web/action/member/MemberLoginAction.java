package firework.hyl.running.web.action.member;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
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
public class MemberLoginAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpServletResponse response;
	private HttpSession session = null;
	@Autowired
	private IMemberService service;

	private String username;
	private String password;
	private String authCode;
	private String msg = null;

	private String autoLogin;

	@Override
	public String execute() throws Exception {
		Memberinfo meminfo = null;
		try {
			System.out.println("auto:" + this.autoLogin);
			if (this.isThisTimeAutoLogin()) {// auto login action
				this.username = (String) this.session
						.getAttribute(GloobalProperties.COOKIE_USER_NAME);
				this.password = (String) this.session
						.getAttribute(GloobalProperties.COOKIE_USER_PASSWORD);
				meminfo = this.service.login(username, password);
				System.out.println("cookie登陆。。。");

			} else {// general login action
				if (this.validateCheckCode()) {
					meminfo = this.service.login(username, password);
					System.out.println("正常登陆。。。");
				} else {// check code error
					this.msg = "验证码不正确";
					System.out.println("验证码不正确...");
					return ERROR;
				}
			}
			System.out.println(meminfo);
			this.session.setAttribute(GloobalProperties.CURRENT_USER, meminfo);
			// next time auto login
			if (this.isNextTimeAutoLogin()) {
				this.updateClientCookie();
				System.out.println("下次自动。。。");
			} else {
				this.invalidateClientCookie();
			}
		} catch (MemberServiceException e) {
			this.msg = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	private void invalidateClientCookie() throws UnsupportedEncodingException {
		Cookie userNameCookie = new Cookie(GloobalProperties.COOKIE_USER_NAME,
				URLEncoder.encode(this.username, "UTF-8"));
		userNameCookie.setMaxAge(0);
		Cookie passwordCookie = new Cookie(
				GloobalProperties.COOKIE_USER_PASSWORD, URLEncoder.encode(
						this.password, "UTF-8"));
		passwordCookie.setMaxAge(0);

		this.response.addCookie(userNameCookie);
		this.response.addCookie(passwordCookie);
	}

	private void updateClientCookie() throws UnsupportedEncodingException {
		Cookie userNameCookie = new Cookie(GloobalProperties.COOKIE_USER_NAME,
				URLEncoder.encode(this.username, "UTF-8"));
		userNameCookie.setMaxAge(60 * 60 * 24 * 7);// 一周
		Cookie passwordCookie = new Cookie(
				GloobalProperties.COOKIE_USER_PASSWORD, URLEncoder.encode(
						this.password, "UTF-8"));
		passwordCookie.setMaxAge(60 * 60 * 24 * 7);

		this.response.addCookie(userNameCookie);
		this.response.addCookie(passwordCookie);
	}

	private boolean validateCheckCode() {
		String rand = (String) session.getAttribute("authCode");
		if (!rand.equals(this.authCode)) {
			this.msg = "验证码不正确";
			return false;
		}
		return true;
	}

	private boolean isThisTimeAutoLogin() {
		return this.authCode == null;
	}

	private boolean isNextTimeAutoLogin() {
		return this.autoLogin != null && this.autoLogin.equals("0");
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public String getMsg() {
		return msg;
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

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

}
