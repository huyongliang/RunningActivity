package firework.hyl.running.web.action.news;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Messagerecord;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMessengerService;

@Controller("messageListAllAction")
@Scope("prototype")
public class MsgListAllAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Memberinfo memberinfo;
	private Map<String, Object> session;
	@Autowired
	private IMessengerService messageService;

	private List<Messagerecord> inbox;
	private String msg;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			this.inbox = this.messageService.listRecieveMessage(memberinfo
					.getNickName());
		} catch (Exception e) {
			e.printStackTrace();
			this.msg = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public List<Messagerecord> getInbox() {
		return inbox;
	}

	public void setInbox(List<Messagerecord> inbox) {
		this.inbox = inbox;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
