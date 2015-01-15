package firework.hyl.running.web.action.member;

import java.io.File;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.service.IMemberService;

public class ModifySpaceAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IMemberService memberService;
	private Map<String, Object> session;
	private String opinion;
	private String runtime;
	private String runhabit;
	private String runstar;
	private String cellphone;
	private String runplace;

	private File icon;
	private String iconFileName;
	private String iconContentType;

	private Memberspace space;
	private Memberinfo info;

	@Override
	public String execute() throws Exception {
		this.info = (Memberinfo) this.session
				.get(GloobalProperties.CURRENT_USER);

		this.memberService.delSpace(info.getMemberSpace().getId());
		return SUCCESS;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getRunhabit() {
		return runhabit;
	}

	public void setRunhabit(String runhabit) {
		this.runhabit = runhabit;
	}

	public String getRunstar() {
		return runstar;
	}

	public void setRunstar(String runstar) {
		this.runstar = runstar;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getRunplace() {
		return runplace;
	}

	public void setRunplace(String runplace) {
		this.runplace = runplace;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getIconContentType() {
		return iconContentType;
	}

	public void setIconContentType(String iconContentType) {
		this.iconContentType = iconContentType;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
