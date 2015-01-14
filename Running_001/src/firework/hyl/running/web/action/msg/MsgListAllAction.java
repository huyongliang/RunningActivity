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

@Controller("msgListAllAction")
@Scope("prototype")
public class MsgListAllAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IMemberService memberService;

	private Memberinfo memberinfo;
	private String msg;

	public String getMsg() {
		return msg;
	}

	private Map<String, Object> session;

	private List<Memberinfo> friendList;

	public List<Memberinfo> getFriendList() {
		return friendList;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("friend list ...exe...");
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);

			this.friendList = this.memberService.listFriend(this.memberinfo
					.getNickName());

			System.out.println("list all friends...");
		} catch (Exception e) {
			e.printStackTrace();
			this.msg = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setFriendList(List<Memberinfo> friendList) {
		this.friendList = friendList;
	}
}
