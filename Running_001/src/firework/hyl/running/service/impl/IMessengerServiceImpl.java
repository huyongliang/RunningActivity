package firework.hyl.running.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Messagerecord;
import firework.hyl.running.common.exception.DataAccessException;
import firework.hyl.running.common.exception.MessengerServiceException;
import firework.hyl.running.dao.IMessengerDao;
import firework.hyl.running.service.IMessengerService;

@Scope("prototype")
@Service("iMessengerService")
public class IMessengerServiceImpl implements IMessengerService {

	@Autowired
	private IMessengerDao dao;

	@Transactional
	@Override
	public Long findNewMessageNum(String nickname)
			throws MessengerServiceException {
		try {
			return this.dao.findNewMessageNum(nickname);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	@Override
	public Memberinfo findOneMemberinfo() throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Memberinfo> findFriends(Long age, String gender, String city)
			throws MessengerServiceException {
		try {
			return this.dao.findFriends(age, gender, city);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Transactional
	@Override
	public void saveMessage(Messagerecord message)
			throws MessengerServiceException {
		try {
			this.dao.saveMessage(message);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public List<Messagerecord> listSendMessage(String senderName)
			throws MessengerServiceException {
		try {
			return this.dao.listSendMessage(senderName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return new ArrayList<>(0);
	}

	@Transactional
	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName)
			throws MessengerServiceException {
		try {
			return this.dao.listRecieveMessage(recieveName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return new ArrayList<>(0);
	}

	@Transactional
	@Override
	public Messagerecord findMessage(Long id) throws MessengerServiceException {
		try {
			Messagerecord messagerecord = this.dao.findMessage(id);
			if (messagerecord == null)
				throw new MessengerServiceException("消息不存在");
			return messagerecord;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Transactional
	@Override
	public void delRecieveMessage(Long id) throws MessengerServiceException {
		try {
			this.dao.deleteRecieveMessage(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void delRecieveMessage(Long[] id) throws MessengerServiceException {
		try {
			this.dao.deleteRecieveMessage(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void delSendMessage(Long id) throws MessengerServiceException {
		try {
			this.dao.deleteSendMessage(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void delSendMessage(Long[] id) throws MessengerServiceException {
		try {
			this.dao.deleteSendMessage(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
