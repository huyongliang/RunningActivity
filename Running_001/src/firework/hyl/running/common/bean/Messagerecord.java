package firework.hyl.running.common.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Messagerecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

// 记录用户之间所发送的短信信息
@Entity
@Table(name="messagerecord")
@SequenceGenerator(name = "msg_rc_seq", sequenceName = "SEQ_COMMON")
public class Messagerecord implements java.io.Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="msg_rc_seq")
	private Long id;
	// 发送人
	@Column(length=20,nullable=false)
	private String sender;
	// 接收人
	@Column(length=20,nullable=false)
	private String receiver;
	// 发送时间
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date senddate;
	// 信息主题
	
	@Column(length=100,nullable=false)
	private String title;
	// 信息内容
	@Column(length=300,nullable=false)
	private String content;
	// 发送状态: 0:发送完但是未删除 1:发送完并且已删除
	@Column(columnDefinition = "number default 0")
	private Long senderstatus;
	// 接收状态: 0:已经接收但未删除 1:接收而且已删除
	@Column(columnDefinition = "number default 0")
	private Long receiverstatus;
	// 信息阅读状态: 0:未读 1:已读
	@Column(columnDefinition = "number default 0")
	private Long status;

	// Constructors

	/** default constructor */
	public Messagerecord() {
	}

	/** minimal constructor */
	public Messagerecord(String sender, String receiver, Date senddate,
			String title, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.senddate = senddate;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Messagerecord(String sender, String receiver, Date senddate,
			String title, String content, Long senderstatus,
			Long receiverstatus, Long status) {
		this.sender = sender;
		this.receiver = receiver;
		this.senddate = senddate;
		this.title = title;
		this.content = content;
		this.senderstatus = senderstatus;
		this.receiverstatus = receiverstatus;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getSenddate() {
		return this.senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSenderstatus() {
		return this.senderstatus;
	}

	public void setSenderstatus(Long senderstatus) {
		this.senderstatus = senderstatus;
	}

	public Long getReceiverstatus() {
		return this.receiverstatus;
	}

	public void setReceiverstatus(Long receiverstatus) {
		this.receiverstatus = receiverstatus;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}