package firework.hyl.running.web.action.member;

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
import firework.hyl.running.service.IMessengerService;

@Controller("memberAfterLoginAction")
@Scope("prototype")
public class MemberAfterLoginAction extends ActionSupport implements
		SessionAware {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IMessengerService messengerService;
	@Autowired
	private IMemberService memberService;
	private List<Memberinfo> top10Members;
	private Long newMessageNum;
	private Long onlineUserNumber;
	private Map<String, Object> session;

	@Override
	public String execute() throws Exception {
		this.top10Members = this.memberService.findMemberinfoByNum(10);
		System.out.println("m:" + this.top10Members);
		Memberinfo memberinfo = (Memberinfo) this.session
				.get(GloobalProperties.CURRENT_USER);
		this.session.put(GloobalProperties.CURRENT_USER, memberinfo);
		String nickname = memberinfo.getNickName();
		this.newMessageNum = this.messengerService.findNewMessageNum(nickname);
		this.onlineUserNumber = this.memberService.findMemberinfoOnline();
		return SUCCESS;
	}

	public List<Memberinfo> getTop10Members() {
		return top10Members;
	}

	public void setTop10Members(List<Memberinfo> top10Members) {
		this.top10Members = top10Members;
	}

	public Long getNewMessageNum() {
		return newMessageNum;
	}

	public void setNewMessageNum(Long newMessageNum) {
		this.newMessageNum = newMessageNum;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Long getOnlineUserNumber() {
		return onlineUserNumber;
	}

	public void setOnlineUserNumber(Long onlineUserNumber) {
		this.onlineUserNumber = onlineUserNumber;
	}
}
