package firework.hyl.running.common.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Blackrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 记录黑名单的一个类
@Entity
@Table(name = "blackrecord")
@SequenceGenerator(name = "bla_rc_seq", sequenceName = "SEQ_COMMON")
public class Blackrecord implements java.io.Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bla_rc_seq")
	private Long id;
	// 用户名
	@Column(length = 20, nullable = false)
	private String selfname;
	// 用户所拉黑的其他会员的名字
	@Column(length = 20, nullable = false)
	private String blackname;

	// Constructors

	/** default constructor */
	public Blackrecord() {
	}

	/** full constructor */
	public Blackrecord(String selfname, String blackname) {
		this.selfname = selfname;
		this.blackname = blackname;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSelfname() {
		return this.selfname;
	}

	public void setSelfname(String selfname) {
		this.selfname = selfname;
	}

	public String getBlackname() {
		return this.blackname;
	}

	public void setBlackname(String blackname) {
		this.blackname = blackname;
	}

}