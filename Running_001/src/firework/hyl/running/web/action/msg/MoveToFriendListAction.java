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

@Controller("moveToFriendListAction")
@Scope("prototype")
public class MoveToFriendListAction extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMemberService memberService;

	private Map<String, Object> session;
	private Memberinfo memberinfo;
	private String moveToBlackMsg;
	private String blackName;

	public String getMoveToBlackMsg() {
		return moveToBlackMsg;
	}

	public void setMoveToBlackMsg(String moveToBlackMsg) {
		this.moveToBlackMsg = moveToBlackMsg;
	}

	public String getBlackName() {
		return blackName;
	}

	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			this.memberService.moveToFriend(this.memberinfo.getNickName(),
					blackName);
		} catch (Exception e) {
			e.printStackTrace();
			this.moveToBlackMsg = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
