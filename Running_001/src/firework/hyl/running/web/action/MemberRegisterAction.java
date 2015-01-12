package firework.hyl.running.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.service.IMemberService;

@Controller("memberRegisterAction")
@Scope("prototype")
public class MemberRegisterAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IMemberService service;

	private String nickName;
	private String passwd;
	private String passwdre;
	private String email;
	private String passwdQuestion;
	private String passwdAnswer;
	private String gender;
	private String recommender;
	private String provinceCity;
	private long age;
	private String phone;
	private String address;
	private String authCode;
	private Map<String, Object> session;
	private String msg;

	@Override
	public String execute() throws Exception {
		System.out.println("sevice....");
		String rand = (String) session.get("authCode");
		System.out.println("session.get(authCode)====>"
				+ session.get("authCode"));
		System.out.println("====>rand=" + rand);

		if (!rand.equals(authCode)) {
			msg = "验证码不正确";
			return ERROR;
		}

		Memberinfo m = new Memberinfo();
		m.setNickName(nickName);
		m.setPasswd(passwd);
		m.setEmail(email);
		m.setPasswdQuestion(passwdQuestion);
		m.setPasswdAnswer(passwdAnswer);
		m.setGender(gender);
		m.setRecommender(recommender);
		m.setProvinceCity(provinceCity);
		m.setAge(age);
		m.setPhone(phone);
		m.setAddress(address);

		try {
			service.registerMemberinfo(m);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			return ERROR;
		}
		msg = "注册成功，请登录";
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public IMemberService getService() {
		return service;
	}

	public void setService(IMemberService service) {
		this.service = service;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdre() {
		return passwdre;
	}

	public void setPasswdre(String passwdre) {
		this.passwdre = passwdre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
