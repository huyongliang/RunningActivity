package firework.hyl.running.web.action.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Messagerecord;
import firework.hyl.running.service.IMessengerService;

@Controller("msgViewAction")
@Scope("prototype")
public class MsgViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Long ID;

	private Messagerecord messagerecord;

	@Autowired
	private IMessengerService messageService;
	private String msg;

	@Override
	public String execute() throws Exception {
		try {
			this.messagerecord = this.messageService.readMsg(ID);
		} catch (Exception e) {
			this.msg = e.getMessage();
			e.printStackTrace();
			return ERROR;
		}

		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Messagerecord getMessagerecord() {
		return messagerecord;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

}
