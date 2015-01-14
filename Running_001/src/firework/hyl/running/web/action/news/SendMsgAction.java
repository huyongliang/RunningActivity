package firework.hyl.running.web.action.news;

import java.util.Date;
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

@Controller("sendMsgAction")
@Scope("prototype")
public class SendMsgAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String msg;

	@Autowired
	private IMessengerService messengerService;

	private Memberinfo memberinfo;
	private Map<String, Object> session;

	private String content;
	private String receiver;
	private String title;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);
			Messagerecord message = new Messagerecord();
			message.setContent(content);
			message.setReceiver(receiver);
			message.setTitle(title);
			message.setSenddate(new Date());
			message.setSender(memberinfo.getNickName());
			message.setSenderstatus(0l);
			message.setStatus(0l);
			message.setReceiverstatus(0l);
			this.messengerService.saveMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			this.msg = "信息发送失败，稍后重试";
			return ERROR;
		}
		this.msg = "信息成功发送";
		return super.execute();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
