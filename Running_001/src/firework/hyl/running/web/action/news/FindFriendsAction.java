package firework.hyl.running.web.action.news;

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

@Controller("findFriendsAction")
@Scope("prototype")
public class FindFriendsAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Memberinfo memberinfo;
	private Map<String, Object> session;

	@Autowired
	private IMemberService memberService;
	private List<Memberinfo> friendsList;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			this.friendsList = this.memberService.listFriend(this.memberinfo
					.getNickName());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public List<Memberinfo> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<Memberinfo> friendsList) {
		this.friendsList = friendsList;
	}
}
