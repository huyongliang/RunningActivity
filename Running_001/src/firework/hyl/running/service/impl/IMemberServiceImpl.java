package firework.hyl.running.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firework.hyl.running.common.bean.Blackrecord;
import firework.hyl.running.common.bean.Friendrecord;
import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.bean.Pointrecord;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.common.exception.MemberServiceException;
import firework.hyl.running.common.util.DateUtil;
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
			if (memberinfo.getRecommender() != null) {
				Memberinfo in = this.dao.findMemberinfoByName(memberinfo
						.getRecommender());
				if (in == null)
					throw new MemberServiceException("推荐人【"
							+ memberinfo.getRecommender() + "】不存在");
				this.addPoint("RECOMMEND", in, dao);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MemberServiceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public Memberinfo findMemberinfoByName(String nickname)
			throws MemberServiceException {
		try {
			return this.dao.findMemberinfoByName(nickname);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Memberinfo login(String username, String passwd)
			throws MemberServiceException {
		Memberinfo ret = null;
		try {
			ret = this.dao.findMemberinfoByName(username);
			if (ret == null) {
				throw new MemberServiceException("用户不存在");
			}
			if (!ret.getPasswd().equals(passwd))
				throw new MemberServiceException("密码不正确");
			// 不是同一天登陆多次
			if (!DateUtil.isSameDay(new Date(), ret.getLatestdate())) {
				this.addPoint("LOGIN", ret, dao);
			}
			ret.setLatestdate(new Date());
			ret.setIsonline(1L);
			this.dao.saveOrUpdateMemberinfo(ret);
		} catch (DataAccessException e) {
			throw new MemberServiceException(e.getMessage());
		} catch (Exception e) {
			throw new MemberServiceException(e.getMessage());
		}
		return ret;
	}

	@Transactional
	@Override
	public void logout(String nickname) throws MemberServiceException {
		try {
			Memberinfo memberinfo = this.dao.findMemberinfoByName(nickname);
			memberinfo.setIsonline(0l);
			this.dao.saveOrUpdateMemberinfo(memberinfo);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public List<Memberinfo> findMemberinfoByNum(int number)
			throws MemberServiceException {
		try {
			return this.dao.findMemberinfoByNum(number);
			// return this.dao.findMemberinfoByNum(number);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return new ArrayList<>(0);
	}

	@Transactional
	@Override
	public Long findMemberinfoOnline() throws MemberServiceException {

		try {
			return this.dao.findMemberinfoOnline();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 1l;
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
				// temp.setAge(memberinfo.getAge());
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
		return memberinfo;
	}

	@Override
	public void saveOrUpdate(Memberinfo memberinfo)
			throws MemberServiceException {
		// TODO Auto-generated method stub

	}

	@Transactional
	@Override
	public void saveOrUpdate(String selfname, String friendname)
			throws MemberServiceException {
		try {
			Memberinfo friend = this.dao.findMemberinfoByName(friendname);
			if (friend == null)
				throw new MemberServiceException("该用户不存在");
			Blackrecord f = this.dao.findBlack(selfname, friendname);
			if (f != null)
				throw new MemberServiceException("【" + friendname + "】在你的黑名单中");
			Friendrecord friendrecord = this.dao.findfriend(selfname,
					friendname);
			if (friendrecord != null)
				throw new MemberServiceException("用户【"
						+ friendrecord.getFriendname() + "】已经是你的好友");
			Friendrecord friendrecord2 = new Friendrecord(selfname, friendname);
			this.dao.saveOrUpdateFriend(friendrecord2);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public List<Memberinfo> listFriend(String selfname)
			throws MemberServiceException {
		List<Memberinfo> list = null;
		try {
			list = this.dao.listFriend(selfname);
		} catch (DataAccessException e) {
			throw new MemberServiceException(e.getMessage());
		}
		return list;
	}

	@Transactional
	@Override
	public void moveToBlack(String selfname, String blackname)
			throws MemberServiceException {
		try {
			this.dao.deleleFriend(selfname, blackname);
			Blackrecord blackrecord = this.dao.findBlack(selfname, blackname);
			if (blackrecord != null)
				throw new MemberServiceException("【" + blackname + "】已经在黑名单中");
			blackrecord = new Blackrecord(selfname, blackname);
			this.dao.saveOrUpdateFriend(blackrecord);
		} catch (DataAccessException e) {
			throw new MemberServiceException("稍后重试");
		}
	}

	@Transactional
	@Override
	public void moveToFriend(String selfName, String name_searching)
			throws MemberServiceException {
		try {
			this.dao.deleleBlack(selfName, name_searching);

			Friendrecord friendrecord = this.dao.findfriend(selfName,
					name_searching);
			if (friendrecord != null)
				throw new MemberServiceException("【" + name_searching
						+ "】已经在好友列表中");
			friendrecord = new Friendrecord(selfName, name_searching);
			this.dao.saveOrUpdateFriend(friendrecord);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public List<Memberinfo> listBlack(String selfname)
			throws MemberServiceException {
		try {
			return this.dao.listBlack(selfname);
		} catch (DataAccessException e) {
			throw new MemberServiceException("稍后重试");
		}
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

	@Transactional
	@Override
	public void deleleBlack(String selfName, String blackName)
			throws MemberServiceException {
		try {
			this.dao.deleleBlack(selfName, blackName);
		} catch (DataAccessException e) {
			throw new MemberServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void deleleBlack(String selfName, String[] blackNames)
			throws MemberServiceException {
		try {
			this.dao.deleleBlack(selfName, blackNames);
		} catch (DataAccessException e) {
			throw new MemberServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void deleleFriend(String selfName, String friendName)
			throws MemberServiceException {
		try {
			this.dao.deleleFriend(selfName, friendName);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MemberServiceException(e.getMessage());
		}

	}

	@Transactional
	@Override
	public void deleleFriend(String selfName, String[] friendNames)
			throws MemberServiceException {
		try {
			this.dao.deleleFriend(selfName, friendNames);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MemberServiceException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void delSpace(Long id) throws MemberServiceException {
		Memberspace memberspace = new Memberspace();
		memberspace.setId(id);
		try {
			this.dao.delSpace(memberspace);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
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

	@Transactional
	@Override
	public void saveSpace(Memberinfo memberinfo, Memberspace ms)
			throws MemberServiceException {
		try {
			Memberinfo temp = this.dao.findMemberinfoByName(memberinfo
					.getNickName());
			// temp.setPoint(temp.get);
			this.addPoint("CREATEPERSONALSPACE", temp, dao);
			ms.setMemberinfo(temp);
			temp.setMemberSpace(ms);
			this.dao.saveOrUpdateMemberinfo(temp);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Transactional
	@Override
	public boolean haveMemberSpace(String nickName)
			throws MemberServiceException {
		try {
			return this.dao.findMemberinfoByName(nickName).getMemberSpace() != null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Transactional
	@Override
	public Memberspace findMemberSapceByUserId(Long userId) {
		return this.dao.findMemberSapceByUserId(userId);
	}
}
