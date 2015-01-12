package firework.hyl.running.common.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Graderecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 表示等级的一个类 积分增加 等级会升高
@Entity
@Table(name = "GRADERECORD")
@SequenceGenerator(name = "grade_rec_seq", sequenceName = "SEQ_COMMON")
public class Graderecord implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_rec_seq")
	private Long id;
	// 当前等级积分的最低值
	@Column(nullable=false)
	private Long minpoint;
	// 当前等级积分的最高值
	@Column(nullable=false)
	private Long maxpoint;
	// 当前等级的名字
	@Column(nullable=false,length=20)
	private String gradename;
	// 当前等级的图标路径
	@Column(nullable=false,length=50)
	private String iconpath;
	// 有哪些用户属于当前这个等级
	@OneToMany(mappedBy="graderecord")
	private Set<Memberinfo> memberinfos = new HashSet<>(0);

	// Constructors

	/** default constructor */
	public Graderecord() {
	}

	/** minimal constructor */
	public Graderecord(Long minpoint, Long maxpoint, String gradename,
			String iconpath) {
		this.minpoint = minpoint;
		this.maxpoint = maxpoint;
		this.gradename = gradename;
		this.iconpath = iconpath;
	}

	/** full constructor */
	public Graderecord(Long minpoint, Long maxpoint, String gradename,
			String iconpath, Set<Memberinfo> memberinfos) {
		this.minpoint = minpoint;
		this.maxpoint = maxpoint;
		this.gradename = gradename;
		this.iconpath = iconpath;
		this.memberinfos = memberinfos;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMinpoint() {
		return this.minpoint;
	}

	public void setMinpoint(Long minpoint) {
		this.minpoint = minpoint;
	}

	public Long getMaxpoint() {
		return this.maxpoint;
	}

	public void setMaxpoint(Long maxpoint) {
		this.maxpoint = maxpoint;
	}

	public String getGradename() {
		return this.gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getIconpath() {
		return this.iconpath;
	}

	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}

	public Set<Memberinfo> getMemberinfos() {
		return this.memberinfos;
	}

	public void setMemberinfos(Set<Memberinfo> memberinfos) {
		this.memberinfos = memberinfos;
	}

	@Override
	public String toString() {
		return "Graderecord [id=" + id + ", minpoint=" + minpoint
				+ ", maxpoint=" + maxpoint + ", gradename=" + gradename
				+ ", iconpath=" + iconpath + "]";
	}

}