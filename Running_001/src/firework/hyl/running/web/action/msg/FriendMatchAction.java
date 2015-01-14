package firework.hyl.running.web.action.msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.service.IMemberService;
import firework.hyl.running.service.IMessengerService;

@Controller("friendMatchAction")
@Scope("prototype")
public class FriendMatchAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IMemberService memberService;
	@Autowired
	private IMessengerService messengerService;
	private String cmd = "top10Mem";
	private List<Memberinfo> randomList;
	private String msg;

	private String age;
	private String provinceCity;
	private String gender;

	@Override
	public String execute() throws Exception {
		System.out.println("random....");
		try {
			if (this.cmd.equals("top10Mem")) {// 不是匹配用户
				this.randomList = this.memberService.findMemberinfoByNum(10);
			} else if ("match1".equals(cmd)) {
				this.randomList = this.memberService.findMemberinfoByNum(1);
			} else {// 匹配

				if ("unlimited".equals(gender) && "unlimited".equals(age)
						&& "unlimited".equals(provinceCity)) {
					this.randomList = this.memberService
							.findMemberinfoByNum(10);
				} else {
					this.randomList = this.messengerService.findFriends(age,
							gender, provinceCity);
				}
			}
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

	public List<Memberinfo> getRandomList() {
		return randomList;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
