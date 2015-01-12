package firework.hyl.running.web.action.member;

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

@Controller("memberModifyPersonInfoAction")
@Scope("prototype")
public class MemberModifyPersonInfoAction extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Memberinfo memberinfo;
	private String oldPasswd;
	private String newPasswd;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	@Autowired
	private IMemberService service;

	@Override
	public String execute() throws Exception {
		try {
			System.out.println("mem:" + this.memberinfo);
			this.memberinfo.setPasswd(oldPasswd);
			Memberinfo temp = this.service.saveOrUpDate(memberinfo, oldPasswd);
			this.session.put(GloobalProperties.CURRENT_USER, temp);
		} catch (MemberServiceException e) {
			this.msg = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Memberinfo getMemberinfo() {
		return memberinfo;
	}

	public void setMemberinfo(Memberinfo memberinfo) {
		this.memberinfo = memberinfo;
	}
}
