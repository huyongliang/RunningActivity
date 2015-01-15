package firework.hyl.running.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findMemberinfoByNum(int number)
			throws DataAccessException {
		Query query = this.getSession().createQuery(
				"from Memberinfo m order by m.point desc");
		query.setMaxResults(number);
		query.setFirstResult(0);

		return query.list();
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
		this.getSession().saveOrUpdate(friend);
	}

	@Override
	public void saveOrUpdateFriend(Blackrecord friend)
			throws DataAccessException {
		this.getSession().saveOrUpdate(friend);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> listFriend(String selfname)
			throws DataAccessException {
		List<Friendrecord> rec = this.getSession()
				.createQuery("from Friendrecord f where f.selfname =?")
				.setString(0, selfname).list();
		if (rec == null || rec.size() == 0)
			return new ArrayList<>(0);
		StringBuilder hql = new StringBuilder(
				"from Memberinfo m where m.nickName in (");
		for (Friendrecord f : rec)
			hql.append("'").append(f.getFriendname()).append("'").append(",");
		hql.deleteCharAt(hql.length() - 1);
		hql.append(")");
		return this.getSession().createQuery(hql.toString()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> listBlack(String selfname)
			throws DataAccessException {
		List<Blackrecord> rec = this.getSession()
				.createQuery("from Blackrecord f where f.selfname =?")
				.setString(0, selfname).list();
		System.out.println("size:" + rec.size());
		if (rec == null || rec.size() == 0) {
			return new ArrayList<>(0);
		}
		StringBuilder hql = new StringBuilder(
				"from Memberinfo m where m.nickName in (");
		for (Blackrecord f : rec)
			hql.append("'").append(f.getBlackname()).append("'").append(",");
		hql.deleteCharAt(hql.length() - 1);
		hql.append(")");
		return this.getSession().createQuery(hql.toString()).list();
	}

	@Override
	public void deleleBlack(Blackrecord black) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleBlack(String selfName, String blackName)
			throws DataAccessException {
		this.getSession()
				.createQuery(
						"delete Blackrecord b where b.selfname=? and b.blackname =?")
				.setString(0, selfName).setString(1, blackName).executeUpdate();
	}

	@Override
	public void deleleBlack(String selfName, String[] blackNames)
			throws DataAccessException {
		for (String black : blackNames)
			this.deleleBlack(selfName, black);
	}

	@Override
	public void deleleFriend(Friendrecord friend) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleleFriend(String selfName, String friendName)
			throws DataAccessException {
		this.getSession()
				.createQuery(
						"delete Friendrecord f where f.selfname=? and f.friendname=?")
				.setString(0, selfName).setString(1, friendName)
				.executeUpdate();
	}

	@Override
	public void deleleFriend(String selfName, String[] friendNames)
			throws DataAccessException {
		for (String fn : friendNames)
			this.deleleFriend(selfName, fn);
	}

	@Override
	public Friendrecord findfriend(String selfName, String friendName)
			throws DataAccessException {
		return (Friendrecord) this
				.getSession()
				.createQuery(
						"from Friendrecord f where f.selfname=? and f.friendname=?")
				.setString(0, selfName).setString(1, friendName).uniqueResult();
	}

	@Override
	public Blackrecord findBlack(String selfName, String blackName)
			throws DataAccessException {
		return (Blackrecord) this
				.getSession()
				.createQuery(
						"from Blackrecord b where b.selfname=? and b.blackname=?")
				.setString(0, selfName).setString(1, blackName).uniqueResult();
	}

	@Override
	public Memberspace findSpace(Long id) throws DataAccessException {
		return (Memberspace) this.getSession().get(Memberspace.class, id);
	}

	@Override
	public void delSpace(Memberspace space) throws DataAccessException {
		this.getSession().createQuery("delete from Memberspace m where m.id=?")
				.setLong(0, space.getId()).executeUpdate();
	}

	@Override
	public Memberinfo findFriendRandom(int number) throws DataAccessException {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findAllMembers() throws DataAccessException {
		return this.getSession().createQuery("from Memberinfo").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findNMemberRandom(int memCount)
			throws DataAccessException {

		List<Memberinfo> ret = null;
		String sql = "select * from (select * from memberinfo order by dbms_random.value) where rownum<="
				+ memCount;
		ret = this.getSession().createSQLQuery(sql).addEntity(Memberinfo.class)
				.list();

		return ret;
	}
}
