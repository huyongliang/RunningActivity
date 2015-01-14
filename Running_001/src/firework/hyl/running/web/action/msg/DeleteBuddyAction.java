package firework.hyl.running.web.action.msg;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

@Controller("deleteBuddyAction")
@Scope("prototype")
public class DeleteBuddyAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String nickName;

	private Memberinfo memberinfo;

	private String deleteMsg;

	@Autowired
	private IMemberService memberService;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			String names[] = this.nickName.split(", ");

			this.memberService.deleleFriend(memberinfo.getNickName(), names);
		} catch (Exception e) {
			e.printStackTrace();
			this.deleteMsg = e.getMessage();
			return ERROR;
		}
		this.deleteMsg = "删除成功";
		return SUCCESS;
	}

	public String getDeleteMsg() {
		return deleteMsg;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
