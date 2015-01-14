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
	public Long findNewMessageNum(String nickname) throws DataAccessException {
		Object result = this
				.getSession()
				.createQuery(
						"select count (id) from Messagerecord  where receiver=? and status=0")
				.setString(0, nickname).uniqueResult();
		return result == null ? 0 : (Long) result;
	}

	@Override
	public Long findMemberinfoNum() throws DataAccessException {
		Object result = this.getSession()
				.createQuery("select count (id) from Memberinfo")
				.uniqueResult();
		return result == null ? 0 : (Long) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Memberinfo> findFriends(String age, String gender, String city)
			throws DataAccessException {
		boolean ageAdded = false;
		boolean genderAdded = false;
		boolean cityAdded = false;
		String hql = "from Memberinfo m where ";
		if (city != null) {
			hql = hql + " m.provinceCity=" + city;
			cityAdded = true;
		}

		if (gender != null) {
			if (cityAdded)
				hql += " and m.gender=" + gender;
			else
				hql += "m.gender=" + gender;
			genderAdded = true;
		}
		if (age != null) {
			long minAge = Long.parseLong(age.trim()) * 10;
			long maxAge = minAge + 9;
			if (genderAdded || cityAdded) {
				hql += " and (m.age>" + minAge + " and m.age<" + maxAge + ")";
			} else {
				hql += "  (m.age>" + minAge + " and m.age<" + maxAge + ")";
			}
			ageAdded = true;
		}
		System.out.println("hql:" + hql);
		if (!ageAdded && !genderAdded && !cityAdded)
			hql = "from Memberinfo";

		System.out.println("match:" + hql);
		return this.getSession().createQuery(hql).list();

	}

	@Override
	public void saveMessage(Messagerecord message) throws DataAccessException {
		this.getSession().save(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messagerecord> listSendMessage(String senderName)
			throws DataAccessException {
		return this
				.getSession()
				.createQuery(
						"from Messagerecord m where m.sender=? and m.senderstatus=0")
				.setString(0, senderName).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName)
			throws DataAccessException {
		return this
				.getSession()
				.createQuery(
						"from Messagerecord m where m.receiver=? and m.receiverstatus=0")
				.setString(0, recieveName).list();
	}

	@Override
	public Messagerecord findMessage(Long id) throws DataAccessException {
		return (Messagerecord) this.getSession().get(Messagerecord.class, id);
	}

	@Override
	public void deleteRecieveMessage(Long id) throws DataAccessException {
		Messagerecord msg = this.findMessage(id);
		msg.setReceiverstatus(1l);
	}

	@Override
	public void deleteRecieveMessage(Long[] id) throws DataAccessException {
		for (Long i : id)
			this.deleteRecieveMessage(i);
	}

	@Override
	public void deleteSendMessage(Long id) throws DataAccessException {
		Messagerecord msg = this.findMessage(id);
		msg.setSenderstatus(1l);
	}

	@Override
	public void deleteSendMessage(Long[] id) throws DataAccessException {
		for (long i : id)
			this.deleteSendMessage(i);
	}

}
