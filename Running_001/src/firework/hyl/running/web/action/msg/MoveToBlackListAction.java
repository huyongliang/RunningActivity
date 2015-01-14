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

@Controller("moveToBlackListAction")
@Scope("prototype")
public class MoveToBlackListAction extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	@Autowired
	private IMemberService memberService;

	private Memberinfo memberinfo;

	private String toBakcListMsg;

	private String blackName;

	@Override
	public String execute() throws Exception {
		try {
			this.memberinfo = (Memberinfo) this.session
					.get(GloobalProperties.CURRENT_USER);

			if (this.memberinfo.getNickName().equals(blackName)) {
				throw new MemberServiceException("你把自己加黑是什么意思？？？");
			}
			this.memberService.moveToBlack(memberinfo.getNickName(), blackName);
		} catch (Exception e) {
			e.printStackTrace();
			this.toBakcListMsg = e.getMessage();
			return ERROR;
		}
		this.toBakcListMsg = "【" + this.blackName + "】已经移动到黑名单";
		return super.execute();
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String getToBakcListMsg() {
		return toBakcListMsg;
	}

	public void setToBakcListMsg(String toBakcListMsg) {
		this.toBakcListMsg = toBakcListMsg;
	}

	public String getBlackName() {
		return blackName;
	}

	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

}
