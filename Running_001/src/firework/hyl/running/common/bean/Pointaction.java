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
import javax.persistence.Transient;

/**
 * Pointaction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 代表哪些 可以加分的动作/行为 的类
@Entity
@Table(name = "pointaction")
@SequenceGenerator(name = "po_ac_seq", sequenceName = "SEQ_COMMON")
public class Pointaction implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_ac_seq")
	private Long id;
	// 当前这个可以加分的动作/行为的名字 例如:登录 注册等
	@Column(length = 20)
	private String actionname;
	// 完成这个动作可以给用户加多少分
	@Column(nullable = false)
	private Long point;
	// 对这个动作描述
	@Column(length = 200)
	private String description;
	// 有哪些动作记录中是引用了当前这个动作
	@OneToMany(mappedBy = "pointaction")
	private Set<Pointrecord> pointrecords = new HashSet<>(0);

	// Constructors

	/** default constructor */
	public Pointaction() {
	}

	/** minimal constructor */
	public Pointaction(Long point) {
		this.point = point;
	}

	/** full constructor */
	public Pointaction(String actionname, Long point, String description,
			Set<Pointrecord> pointrecords) {
		this.actionname = actionname;
		this.point = point;
		this.description = description;
		this.pointrecords = pointrecords;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public Long getPoint() {
		return this.point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Pointrecord> getPointrecords() {
		return this.pointrecords;
	}

	public void setPointrecords(Set<Pointrecord> pointrecords) {
		this.pointrecords = pointrecords;
	}

	@Override
	public String toString() {
		return "Pointaction [id=" + id + ", actionname=" + actionname
				+ ", point=" + point + ", description=" + description + "]";
	}

}