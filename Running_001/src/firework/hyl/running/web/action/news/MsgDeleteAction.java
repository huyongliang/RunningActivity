package firework.hyl.running.web.action.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.service.IMessengerService;

@Controller("msgDeleteAction")
@Scope("prototype")
public class MsgDeleteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String cmd;
	private String ID;
	@Autowired
	private IMessengerService messageService;

	@Override
	public String execute() throws Exception {
		String ids[] = this.ID.split(", ");
		Long id[] = new Long[ids.length];
		for (int i = 0; i < ids.length; i++)
			id[i] = Long.parseLong(ids[i]);
		if ("in".equals(cmd)) {// 接收&&删除
			this.messageService.delRecieveMessage(id);
			return "inbox";
		} else if ("out".equals(cmd)) {// 发送&&删除
			this.messageService.delSendMessage(id);
			return "outbox";
		}
		return "inbox";
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
