package firework.hyl.running.web.action.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.util.FileUtils;
import firework.hyl.running.common.util.GloobalProperties;
import firework.hyl.running.dao.impl.MemberDaoImpl;
import firework.hyl.running.service.IMemberService;

@Controller("memCreateSpaceAction")
@Scope("prototype")
public class MemCreateSpaceAction extends ActionSupport implements SessionAware {
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

	private String cmd;

	@Override
	public String execute() throws Exception {
		this.info = (Memberinfo) this.session
				.get(GloobalProperties.CURRENT_USER);
		String baseDir = GloobalProperties.get("user.header.dir");
		FileUtils.makeDir(baseDir);
		System.out.println("base:" + baseDir);
		String newName = FileUtils.getTimeStamp()
				+ FileUtils.getExtName(iconFileName, true);
		System.out.println("newName:" + newName);

		String diskPath = baseDir + newName;
		System.out.println("disk:" + diskPath);
		if (this.memberService.haveMemberSpace(info.getNickName())) {// update space
			this.memberService.delSpace(info.getMemberSpace().getId());
			System.out.println("update:");
		}
		System.out.println("create:");
		this.uploadHeader(diskPath);

		this.space = new Memberspace();
		this.space.setCellphone(cellphone);
		this.space.setIcon(diskPath);
		this.space.setMemberinfo(info);
		this.space.setOpinion(opinion);
		this.space.setRunhabit(runhabit);
		this.space.setRunplace(runplace);
		this.space.setRunstar(runstar);
		this.space.setRuntime(runtime);

		this.memberService.saveSpace(info, space);

		this.updateSession();
		return SUCCESS;
	}

	protected void uploadHeader(String diskPath) throws FileNotFoundException,
			IOException {
		InputStream in = new FileInputStream(icon);
		OutputStream out = new FileOutputStream(new File(diskPath));
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	private void updateSession() {
		this.info.setMemberSpace(space);
		session.put(GloobalProperties.CURRENT_USER, info);
		System.out.println(info.getMemberSpace().getIcon());
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

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
}
