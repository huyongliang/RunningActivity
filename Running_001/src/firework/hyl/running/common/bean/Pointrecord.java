package firework.hyl.running.common.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Pointrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 用户所完成的 可以增加积分的动作/行为之后 的记录
@Entity
@Table(name = "POINTRECORD")
@SequenceGenerator(name = "po_rc_seq", sequenceName = "SEQ_COMMON")
public class Pointrecord implements java.io.Serializable {

	// Fields
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_rc_seq")
	private Long id;
	// 用户所完成的可以增加积分的动作/行为 例如:登录、注册、发文章等等

	@ManyToOne
	@JoinColumn(name = "pointactionid")
	private Pointaction pointaction;
	// 完成这个动作的用户的昵称
	@Column(length = 20, nullable = false)
	private String nickname;
	// 完成的时间点
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date receivedate;

	// Constructors

	/** default constructor */
	public Pointrecord() {
	}

	/** minimal constructor */
	public Pointrecord(String nickname, Date receivedate) {
		this.nickname = nickname;
		this.receivedate = receivedate;
	}

	/** full constructor */
	public Pointrecord(Pointaction pointaction, String nickname,
			Date receivedate) {
		this.pointaction = pointaction;
		this.nickname = nickname;
		this.receivedate = receivedate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pointaction getPointaction() {
		return this.pointaction;
	}

	public void setPointaction(Pointaction pointaction) {
		this.pointaction = pointaction;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getReceivedate() {
		return this.receivedate;
	}

	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}

}