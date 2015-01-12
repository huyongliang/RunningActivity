package firework.hyl.running.common.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Memberspace entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 用户所对应的个人空间
@Entity
@Table(name = "MEMBERSPACE")
@DynamicUpdate(true)
@SequenceGenerator(name = "mem_sp_seq", sequenceName = "SEQ_COMMON")
public class Memberspace implements java.io.Serializable {

	// Fields

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_sp_seq")
	private Long id;
	// 当前这个个人空间对应的用户

	@OneToOne
	@JoinColumn(name = "memberid")
	private Memberinfo memberinfo;
	// 个人对跑步的看法
	@Column(length = 200)
	private String opinion;
	// 跑步时间
	@Column(length = 20)
	private String runtime;
	// 跑步地点
	@Column(length = 20)
	private String runplace;
	// 喜欢的跑步明星
	@Column(length = 50)
	private String runstar;
	// 跑步的习惯
	@Column(length = 50)
	private String runhabit;
	// 正在使用的手机类型/号码
	@Column(length = 50)
	private String cellphone;
	// 个人空间中的头像路径
	@Column(length = 200)
	private String icon;

	// Constructors

	/** default constructor */
	public Memberspace() {
	}

	/** full constructor */
	public Memberspace(Memberinfo memberinfo, String opinion, String runtime,
			String runplace, String runstar, String runhabit, String cellphone,
			String icon) {
		this.memberinfo = memberinfo;
		this.opinion = opinion;
		this.runtime = runtime;
		this.runplace = runplace;
		this.runstar = runstar;
		this.runhabit = runhabit;
		this.cellphone = cellphone;
		this.icon = icon;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Memberinfo getMemberinfo() {
		return this.memberinfo;
	}

	public void setMemberinfo(Memberinfo memberinfo) {
		this.memberinfo = memberinfo;
	}

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getRuntime() {
		return this.runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getRunplace() {
		return this.runplace;
	}

	public void setRunplace(String runplace) {
		this.runplace = runplace;
	}

	public String getRunstar() {
		return this.runstar;
	}

	public void setRunstar(String runstar) {
		this.runstar = runstar;
	}

	public String getRunhabit() {
		return this.runhabit;
	}

	public void setRunhabit(String runhabit) {
		this.runhabit = runhabit;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Memberspace [id=" + id + ", opinion=" + opinion + ", runtime="
				+ runtime + ", runplace=" + runplace + ", runstar=" + runstar
				+ ", runhabit=" + runhabit + ", cellphone=" + cellphone
				+ ", icon=" + icon + "]";
	}

}