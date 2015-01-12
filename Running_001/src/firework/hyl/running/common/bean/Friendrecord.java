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
 * Friendrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

//记录好友的一个类
@Entity
@Table(name="friendrecord")
@SequenceGenerator(name = "fr_ac_seq", sequenceName = "SEQ_COMMON")
public class Friendrecord implements java.io.Serializable {

	// Fields

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="fr_ac_seq")
	private Long id;
	//用户名字
	
	@Column(length=20,nullable=false)
	private String selfname;
	//用户的好友名字
	@Column(length=20,nullable=false)
	private String friendname;

	// Constructors

	/** default constructor */
	public Friendrecord() {
	}

	/** full constructor */
	public Friendrecord(String selfname, String friendname) {
		this.selfname = selfname;
		this.friendname = friendname;
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

	public String getFriendname() {
		return this.friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

}