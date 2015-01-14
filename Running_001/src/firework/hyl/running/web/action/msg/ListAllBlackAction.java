package firework.hyl.running.web.action.msg;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

@Controller("listAllBlackAction")
@Scope("prototype")
public class ListAllBlackAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;
	@Autowired
	private IMemberService memberService;
	private Memberinfo memberinfo;
	private List<Memberinfo> blackFriends;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			this.blackFriends = this.memberService.listBlack(this.memberinfo
					.getNickName());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public List<Memberinfo> getBlackFriends() {
		return blackFriends;
	}

	public void setBlackFriends(List<Memberinfo> blackFriends) {
		this.blackFriends = blackFriends;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

}
