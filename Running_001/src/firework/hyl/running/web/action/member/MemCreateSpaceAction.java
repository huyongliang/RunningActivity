package firework.hyl.running.web.action.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.util.FileUtils;
import firework.hyl.running.common.util.GloobalProperties;
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

	@Override
	public String execute() throws Exception {
		this.info = (Memberinfo) this.session
				.get(GloobalProperties.CURRENT_USER);
		// FileUtils.makeDir(this.filePathOnDisk);
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/UserHeader");
		System.out.println("real:" + realpath);
		this.buildSpaceEntity();
		String newName = uploadFile(realpath);

		this.space.setIcon(realpath + File.separator + newName);

		this.memberService.saveSpace(info, space);
		return super.execute();
	}

	protected String uploadFile(String realpath) throws FileNotFoundException,
			IOException {
		System.out.println("space:" + this.space);
		System.out.println(this.iconContentType);
		System.out.println(this.icon.getName());
		String newName = FileUtils.getTimeStamp()
				+ FileUtils.getExtName(iconFileName, true);
		System.out.println("enw:" + newName);

		byte[] buffer = new byte[2048];
		int length = 0;
		InputStream is = new FileInputStream(icon);

		OutputStream os = new FileOutputStream(new File(realpath, newName));
		while ((length = is.read(buffer)) != -1) {
			os.write(buffer, 0, length);
		}
		os.close();
		is.close();
		return newName;
	}

	protected void buildSpaceEntity() {
		this.space = new Memberspace();
		this.space.setCellphone(cellphone);
		this.space.setIcon(iconFileName);
		this.space.setOpinion(opinion);
		this.space.setRunhabit(runhabit);
		this.space.setRunplace(runplace);
		this.space.setRunstar(runstar);
		this.space.setRuntime(runtime);
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
