package firework.hyl.running.web.action.member;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

@Controller("memGoToSpaceAction")
@Scope("prototype")
public class MemGoToSpaceAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	private Memberinfo info;
	@Autowired
	private IMemberService memberService;

	@Override
	public String execute() throws Exception {
		this.info = (Memberinfo) this.session
				.get(GloobalProperties.CURRENT_USER);
		if (this.memberService.haveMemberSpace(info.getNickName())) {
			return "goto_space";
		}
		
		return "no_space";
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
