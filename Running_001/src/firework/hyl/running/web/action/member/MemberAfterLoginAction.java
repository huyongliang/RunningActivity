package firework.hyl.running.web.action.member;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("memberAfterLoginAction")
@Scope("prototype")
public class MemberAfterLoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		System.out.println("after login action...");
		return super.execute();
	}
}
