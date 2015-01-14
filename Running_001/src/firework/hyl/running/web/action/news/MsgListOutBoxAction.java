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

@Controller("msgListOutBoxAction")
@Scope("prototype")
public class MsgListOutBoxAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String msg;

	@Autowired
	private IMessengerService messengerService;

	private Memberinfo memberinfo;
	private Map<String, Object> session;

	private List<Messagerecord> outbox;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);

			this.outbox = this.messengerService.listSendMessage(memberinfo
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Messagerecord> getOutbox() {
		return outbox;
	}

	public void setOutbox(List<Messagerecord> outbox) {
		this.outbox = outbox;
	}
}
