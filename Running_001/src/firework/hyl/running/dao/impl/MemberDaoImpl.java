package firework.hyl.running.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import firework.hyl.running.common.bean.Blackrecord;
import firework.hyl.running.common.bean.Friendrecord;
import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.bean.Pointrecord;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.dao.IMemberDao;

@Repository("iMemberDao")
@Scope("prototype")
public class MemberDaoImpl implements IMemberDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
		// return this.session;
	}

	private Session session;

	public MemberDaoImpl() {
	}

	public MemberDaoImpl(Session session) {
		super();
		this.session = session;
	}

	@Override
	public Memberinfo findMemberinfoByName(String name)
			throws DataAccessException {
		String hql = "from Memberinfo m where m.nickName = ?";
		return (Memberinfo) this.getSession().createQuery(hql)
				.setString(0, name).uniqueResult();
	}

	@Override
	public void saveOrUpdateMemberinfo(Memberinfo memberinfo)
			throws DataAccessException {
		this.getSession().saveOrUpdate(memberinfo);
	}

	@Override
	public List<Memberinfo> findMemberinfoByNum(int number)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findMemberinfoOnline() throws DataAccessException {
		Object uniqueResult = this
				.getSession()
				.createQuery(
						"select count(m.id) from Memberinfo m where m.isonline=1")
				.uniqueResult();
		return uniqueResult == null ? 0L : (Long) uniqueResult;
	}

	@Override
	public Graderecord findMemberinfoLevel(Long point)
			throws DataAccessException {
		String hql = "from Graderecord g where g.minpoint<=? and g.maxpoint>?";
		return (Graderecord) this.getSession().createQuery(hql)
				.setLong(0, point).setLong(1, point).uniqueResult();
	}

	@Override
	public Pointaction findPointactionByPointAction(String pointAction)
			throws DataAccessException {
		String hql = "from Pointaction p where p.description=? or p.actionname=?";
		return (Pointaction) this.getSession().createQuery(hql)
				.setString(0, pointAction).setString(1, pointAction)
				.uniqueResult();
	}

	@Override
	public void saveOrUpdatePointrecord(Pointrecord pointrecord)
			throws DataAccessException {
		this.getSession().saveOrUpdate(pointrecord);
	}

	@Override
	public void saveOrUpdateFriend(Friendrecord friend)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateFriend(Blackrecord friend)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Memberinfo> listFriend(String selfname)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memberinfo> listBlack(String selfname)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleleBlack(Blackrecord black) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleBlack(String selfName, String blackName)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleBlack(String selfName, String[] blackNames)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(Friendrecord friend) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(String selfName, String friendName)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(String selfName, String[] friendNames)
			throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Friendrecord findfriend(String selfName, String friendName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blackrecord findBlack(String selfName, String blackName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Memberspace findSpace(Long id) throws DataAccessException {

		return (Memberspace) this.getSession().get(Memberspace.class, id);
	}

	@Override
	public void delSpace(Memberspace space) throws DataAccessException {
		this.getSession().delete(space);
	}

	@Override
	public Memberinfo findFriendRandom() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findAllMembers() throws DataAccessException {
		return this.getSession().createQuery("from Memberinfo").list();
	}

}
