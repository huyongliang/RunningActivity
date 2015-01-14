package firework.hyl.running.web.action.msg;

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

@Controller("addFriendAction")
@Scope("prototype")
public class AddFriendAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 7836931104426470129L;
	private Map<String, Object> session;
	@Autowired
	private IMemberService memberService;

	private String friendName;
	private Memberinfo me;

	private String addFriendMsg;

	public String getAddFriendMsg() {
		return addFriendMsg;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendName() {
		return friendName;
	}

	@Override
	public String execute() throws Exception {
		try {
			this.me = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			if(me.getNickName().equals(friendName))
				throw new MemberServiceException("你加自己为好友是什么意思？？？");
			this.memberService.saveOrUpdate(me.getNickName(), friendName);
		} catch (Exception e) {
			this.addFriendMsg = e.getMessage();
			return ERROR;
		}
		this.addFriendMsg = "成功添加好友【" + friendName + "】";
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

}
