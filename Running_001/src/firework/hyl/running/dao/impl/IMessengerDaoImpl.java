package firework.hyl.running.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Messagerecord;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.dao.IMessengerDao;

@Repository("iMessengerDao")
@Scope("prototype")
public class IMessengerDaoImpl implements IMessengerDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Integer findNewMessageNum(String nickname)
			throws DataAccessException {
		Object result = this
				.getSession()
				.createQuery(
						"select count (id) from Messagerecord  where receiver=? and status=0")
				.setString(0, nickname).uniqueResult();
		return result == null ? 0 : (Integer) result;
	}

	@Override
	public Integer findMemberinfoNum() throws DataAccessException {
		Object result = this.getSession()
				.createQuery("select count (id) from Memberinfo")
				.uniqueResult();
		return result == null ? 0 : (Integer) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findFriends(Long age, String gender, String city)
			throws DataAccessException {
		return this
				.getSession()
				.createQuery(
						"from Memberinfo m where m.age =? or m.gender=? or m.provinceCity=?")
				.setLong(0, age).setString(1, gender).setString(2, city).list();
	}

	@Override
	public void saveMessage(Messagerecord message) throws DataAccessException {
		this.getSession().save(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messagerecord> listSendMessage(String senderName)
			throws DataAccessException {
		return this.getSession()
				.createQuery("from Messagerecord m where m.sender=?")
				.setString(0, senderName).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName)
			throws DataAccessException {
		return this.getSession()
				.createQuery("from Messagerecord m where m.receiver=?")
				.setString(0, recieveName).list();
	}

	@Override
	public Messagerecord findMessage(Long id) throws DataAccessException {
		return (Messagerecord) this.getSession().get(Messagerecord.class, id);
	}

	@Override
	public void deleteRecieveMessage(Long id) throws DataAccessException {
		Messagerecord msg = this.findMessage(id);
		this.getSession().delete(msg);
	}

	@Override
	public void deleteRecieveMessage(Long[] id) throws DataAccessException {
		for (Long i : id)
			this.deleteRecieveMessage(i);
	}

	@Override
	public void deleteSendMessage(Long id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSendMessage(Long[] id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

}
