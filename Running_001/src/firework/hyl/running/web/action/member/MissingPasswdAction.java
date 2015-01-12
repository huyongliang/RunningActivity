package firework.hyl.running.web.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.service.IMemberService;

@Controller("missingPasswdAction")
@Scope("prototype")
public class MissingPasswdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String passwdQuestion;
	private String passwdAnswer;
	private String isok;
	private String ret;
	@Autowired
	private IMemberService service;

	@Override
	public String execute() throws Exception {
		try {
			this.ret = this.service.getBackPasswd(userName, passwdQuestion,
					passwdAnswer);
			this.isok = "OK";
			return SUCCESS;
		} catch (Exception e) {
			this.isok = "NO";
			this.ret = "请再次尝试";
//			e.printStackTrace();
			return ERROR;
		}
	}

	public String getIsok() {
		return isok;
	}

	public String getRet() {
		return ret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswdQuestion() {
		return passwdQuestion;
	}

	public void setPasswdQuestion(String passwdQuestion) {
		this.passwdQuestion = passwdQuestion;
	}

	public String getPasswdAnswer() {
		return passwdAnswer;
	}

	public void setPasswdAnswer(String passwdAnswer) {
		this.passwdAnswer = passwdAnswer;
	}
}
