package firework.hyl.running.web.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

@Controller("logoutAction")
@Scope("prototype")
public class LogoutAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request = null;
	private HttpSession session = null;

	@Autowired
	private IMemberService memberService;

	@Override
	public String execute() throws Exception {
		Memberinfo memberinfo = (Memberinfo) this.session
				.getAttribute(GloobalProperties.CURRENT_USER);
		this.memberService.logout(memberinfo.getNickName());
		this.session.invalidate();
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();
	}

}
