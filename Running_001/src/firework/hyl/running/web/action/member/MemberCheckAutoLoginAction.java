package firework.hyl.running.web.action.member;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.util.GloobalProperties;

@Controller("memberCheckAutoLoginAction")
@Scope("prototype")
public class MemberCheckAutoLoginAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpSession session = null;

	@Override
	public String execute() throws Exception {
		boolean isAuto = false;
		Cookie[] cookies = this.request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String k = cookie.getName();
				String v = URLDecoder.decode(cookie.getValue(),"UTF-8");
				if (k.equals(GloobalProperties.COOKIE_USER_NAME)) {
					this.session.setAttribute(
							GloobalProperties.COOKIE_USER_NAME, v);
					isAuto = true;
				}
				if (k.equals(GloobalProperties.COOKIE_USER_PASSWORD)) {
					this.session.setAttribute(
							GloobalProperties.COOKIE_USER_PASSWORD, v);
					isAuto = true;
				}
			}
		}
		System.out.println("validate...");
		if (isAuto)
			return SUCCESS;
		else
			return "general_login_action";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();

	}

}
