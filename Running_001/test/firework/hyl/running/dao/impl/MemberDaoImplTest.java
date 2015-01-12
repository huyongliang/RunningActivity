package firework.hyl.running.dao.impl;

import static org.junit.Assert.fail;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.dao.IMemberDao;

@SuppressWarnings("deprecation")
public class MemberDaoImplTest {
	private SessionFactory factory;
	private Session session;
	private Transaction transaction;
	private IMemberDao dao;
	private Graderecord graderecord = new Graderecord(10l, 1000l, "grade001",
			"no");
	private Memberspace memberspace = new Memberspace(null, "opnion", "3",
			"五道口", "no", "no", "123456789000", "");
	private Memberinfo baseMember = null;
	{

		this.baseMember = new Memberinfo(null, "tom", "123", "男", 0l,
				"fdsjk@jfkd.com", "北京", "五道口", "188222555545", "321 inverse",
				"123", "cat", 25l, new Date(), new Date(), 0L, 0L, null);
		// this.graderecord.getMemberinfos().add(baseMember);
		// this.memberspace.setMemberinfo(baseMember);
	}

	@Test
	public void testFindMemberinfoByName() throws DataAccessException {
		try {
			Memberinfo memberinfo = this.dao.findMemberinfoByName("tom");
			System.out.println(memberinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveOrUpdateMemberinfo() {
		try {
			this.baseMember.setId(250l);
			this.baseMember.setMemberSpace(memberspace);
			this.memberspace.setMemberinfo(baseMember);
			this.baseMember.setGraderecord(graderecord);
			this.graderecord.getMemberinfos().add(baseMember);
			this.dao.saveOrUpdateMemberinfo(baseMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindMemberinfoByNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMemberinfoOnline() throws DataAccessException {
		Long online = this.dao.findMemberinfoOnline();
		System.out.println(online);
	}

	@Test
	public void testFindMemberinfoLevel() throws DataAccessException {
		Graderecord graderecord2 = this.dao.findMemberinfoLevel(200l);
		System.out.println(graderecord2);
	}

	@Test
	public void testFindPointactionByPointAction() throws DataAccessException {
		Pointaction action = this.dao.findPointactionByPointAction("REGISTER");
		System.out.println(action);
		action = this.dao.findPointactionByPointAction("注册会员");
		System.out.println(action);
	}

	@Test
	public void testSaveOrUpdatePointrecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOrUpdateFriendFriendrecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOrUpdateFriendBlackrecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testListFriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testListBlack() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleBlackBlackrecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleBlackStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleBlackStringStringArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleFriendFriendrecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleFriendStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleleFriendStringStringArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindfriend() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBlack() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSpace() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelSpace() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindFriendRandom() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllMembers() throws DataAccessException {
		System.out.println(this.dao.findAllMembers());
	}

	@Before
	public void before() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		factory = configuration.buildSessionFactory(registry);
		session = factory.openSession();
		transaction = session.beginTransaction();
		this.dao = new MemberDaoImpl(session);
	}

	@After
	public void after() {
		transaction.commit();
		session.close();
		factory.close();
	}

}
