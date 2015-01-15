package firework.hyl.running.common.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

//用户信息
@Entity
@Table(name = "MEMBERINFO")
@DynamicUpdate(true)
@SequenceGenerator(name = "mem_info_seq", sequenceName = "SEQ_COMMON")
public class Memberinfo implements java.io.Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	// 用户的id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mem_info_seq")
	private Long id;
	// 当前用户的等级
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "gradeid")
	private Graderecord graderecord;
	// 用户的个人空间
	@OneToOne(mappedBy = "memberinfo", cascade = { CascadeType.ALL })
	private Memberspace memberSpace;
	// 用户昵称
	@Column(length = 20, nullable = false, unique = true)
	private String nickName;
	// 密码
	@Column(length = 50, nullable = false)
	private String passwd;
	// 性别
	@Column(length = 1, nullable = false)
	private String gender;
	// 年龄
	@Column(nullable = false)
	private Long age;
	// 电子邮件地址
	@Column(length = 100, nullable = false)
	private String email;
	// 所在省市
	@Column(length = 10)
	private String provinceCity;
	// 详细地址
	@Column(length = 200)
	private String address;
	// 电话
	@Column(length = 50)
	private String phone;
	// 找回密码的提示问题
	@Column(length = 200)
	private String passwdQuestion;
	// 找回密码的提示问题的答案
	@Column(length = 200)
	private String passwdAnswer;
	// 推荐人
	@Column(length = 20)
	private String recommender;
	// 积分(积米)
	@Column(columnDefinition = "INT default 0")
	private Long point = 0L;
	// 注册时间
	@Temporal(TemporalType.DATE)
	private Date registerdate;
	// 最后一次登录时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date latestdate;
	// 用户账户的状态 0:正常 1:注销/冻结
	@Column(columnDefinition = "number default 0")
	private Long status;
	// 用户是否在线 0:下线 1:在线
	@Column(columnDefinition = "number default 0")
	private Long isonline;

	public Memberinfo() {
	}

	public Memberinfo(Graderecord graderecord, String nickName, String passwd,
			String gender, Long age, String email, String provinceCity,
			String address, String phone, String passwdQuestion,
			String passwdAnswer, String recommender, Long point,
			Date registerdate, Date latestdate, Long status, Long isonline,
			Memberspace memberSpace) {
		this.graderecord = graderecord;
		this.nickName = nickName;
		this.passwd = passwd;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.provinceCity = provinceCity;
		this.address = address;
		this.phone = phone;
		this.passwdQuestion = passwdQuestion;
		this.passwdAnswer = passwdAnswer;
		this.recommender = recommender;
		this.point = point;
		this.registerdate = registerdate;
		this.latestdate = latestdate;
		this.status = status;
		this.isonline = isonline;
		this.memberSpace = memberSpace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Graderecord getGraderecord() {
		return graderecord;
	}

	public void setGraderecord(Graderecord graderecord) {
		this.graderecord = graderecord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvinceCity() {
		return provinceCity;
	}

	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPasswdQuestion() {
		return passwdQuestion;
	}

	public void setPasswdQuestion(String passwdQuestion) {
		this.passwdQuestion = passwdQuestion;
	}

	public String getPasswdAnswer() {
		return passwdAnswer;
	}

	public void setPasswdAnswer(String passwdAnswer) {
		this.passwdAnswer = passwdAnswer;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public Date getLatestdate() {
		return latestdate;
	}

	public void setLatestdate(Date latestdate) {
		this.latestdate = latestdate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getIsonline() {
		return isonline;
	}

	public void setIsonline(Long isonline) {
		this.isonline = isonline;
	}

	public Memberspace getMemberSpace() {
		return memberSpace;
	}

	public void setMemberSpace(Memberspace memberSpace) {
		this.memberSpace = memberSpace;
	}

	@Override
	public String toString() {
		return "Memberinfo [id=" + id + ", nickName=" + nickName + ", passwd="
				+ passwd + ", gender=" + gender + ", age=" + age + ", email="
				+ email + ", provinceCity=" + provinceCity + ", address="
				+ address + ", phone=" + phone + ", passwdQuestion="
				+ passwdQuestion + ", passwdAnswer=" + passwdAnswer
				+ ", recommender=" + recommender + ", point=" + point
				+ ", registerdate=" + registerdate + ", latestdate="
				+ latestdate + ", status=" + status + ", isonline=" + isonline
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickName == null) ? 0 : nickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Memberinfo other = (Memberinfo) obj;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	public String getCityString() {
		return firework.hyl.running.common.util.Util
				.getProvinceNameById(this.provinceCity);
	}
}