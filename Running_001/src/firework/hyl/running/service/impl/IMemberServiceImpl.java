package firework.hyl.running.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firework.hyl.running.common.bean.Friendrecord;
import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.bean.Pointrecord;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.common.exception.MemberServiceException;
import firework.hyl.running.dao.IMemberDao;
import firework.hyl.running.service.IMemberService;

@Scope("prototype")
@Service("iMemberService")
public class IMemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDao dao;

	@Transactional
	@Override
	public void registerMemberinfo(Memberinfo memberinfo)
			throws MemberServiceException {
		try {
			Memberinfo temp = this.dao.findMemberinfoByName(memberinfo
					.getNickName());
			if (temp != null)
				throw new MemberServiceException("用户名 "
						+ memberinfo.getNickName() + " 已经存在");

			Graderecord grade = this.dao.findMemberinfoLevel(0l);
			memberinfo.setGraderecord(grade);
			memberinfo.setRegisterdate(new Date());
			memberinfo.setLatestdate(new Date());
			memberinfo.setIsonline(0l);
			memberinfo.setStatus(0l);

			this.dao.saveOrUpdateMemberinfo(memberinfo);

			this.addPoint("REGISTER", memberinfo, this.dao);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MemberServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Memberinfo findMemberinfoByName(String nickname)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Memberinfo login(String username, String passwd)
			throws MemberServiceException {
		try {
			Memberinfo ret = this.dao.findMemberinfoByName(username);
			if (ret != null && ret.getPasswd().equals(passwd))
				return ret;
		} catch (DataAccessException e) {
			throw new MemberServiceException(e.getMessage());
		}
		return null;
	}

	@Override
	public void logout(String nickname) throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Memberinfo> findMemberinfoByNum(int number)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findMemberinfoOnline() throws MemberServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Graderecord findMemberinfoLevel(Long point)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public String getBackPasswd(String nickname, String pwdQuestion,
			String pwdAnswer) throws MemberServiceException {
		try {
			Memberinfo memberinfo = this.dao.findMemberinfoByName(nickname);
			if (memberinfo == null)
				throw new MemberServiceException("你输入的用户名不存在");
			if (pwdQuestion.equals(memberinfo.getPasswdQuestion())
					&& pwdAnswer.equals(memberinfo.getPasswdAnswer()))
				return memberinfo.getPasswd();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Memberinfo saveOrUpDate(Memberinfo memberinfo, String oldPasswd)
			throws MemberServiceException {
		try {
			Memberinfo temp = this.dao.findMemberinfoByName(memberinfo
					.getNickName());
			if (temp == null)
				throw new MemberServiceException("用户不存在");
			if (temp.getPasswd().equals(oldPasswd)) {
				temp.setAddress(memberinfo.getAddress());
//				temp.setAge(memberinfo.getAge());
				temp.setEmail(memberinfo.getEmail());
				temp.setGender(memberinfo.getGender());
				temp.setPasswd(oldPasswd);
				temp.setPasswdAnswer(memberinfo.getPasswdAnswer());
				temp.setPasswdQuestion(memberinfo.getPasswdQuestion());
				temp.setPhone(memberinfo.getPhone());
				temp.setProvinceCity(memberinfo.getProvinceCity());
				this.dao.saveOrUpdateMemberinfo(temp);
				return temp;
			} else {
				throw new MemberServiceException("旧密码输入错误");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveOrUpdate(Memberinfo memberinfo)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(String selfname, String friendname)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Memberinfo> listFriend(String selfname)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveToBlack(String selfname, String blackname)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Memberinfo> listBlack(String selfname)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Friendrecord findFriend(String friend) throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isMemberspace(Long id) throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveToFriend(String selfName, String name_searching)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleBlack(String selfName, String blackName)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleBlack(String selfName, String[] blackNames)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(String selfName, String friendName)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(String selfName, String[] friendNames)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delSpace(Long id) throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Pointaction findPointactionByPointAction(String actionName)
			throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Pointrecord pointrecord) throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void memberinfoModify(String oldPwd, Memberinfo member)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveSpace(Memberinfo memberinfo, Memberspace ms)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Memberinfo matchFriend() throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memberinfo> findAllMembers() throws MemberServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	private void addPoint(String name, Memberinfo m, IMemberDao dao)
			throws Exception {
		Pointaction pointAction = dao.findPointactionByPointAction(name);
		Long point = pointAction.getPoint();
		m.setPoint(m.getPoint() + point);
		Long newPoint = m.getPoint();
		Graderecord oldGrade = m.getGraderecord();
		if (!(newPoint > oldGrade.getMinpoint() && newPoint <= oldGrade
				.getMaxpoint())) {
			Graderecord newGrade = dao.findMemberinfoLevel(newPoint);
			m.setGraderecord(newGrade);
		}
		dao.saveOrUpdateMemberinfo(m);

		Pointrecord record = new Pointrecord();
		record.setNickname(m.getNickName());
		record.setReceivedate(new Date());
		record.setPointaction(pointAction);
		dao.saveOrUpdatePointrecord(record);
	}
}
