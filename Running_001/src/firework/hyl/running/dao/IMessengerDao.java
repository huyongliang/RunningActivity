package firework.hyl.running.dao;

import java.util.List;

import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Messagerecord;
import firework.hyl.running.common.exception.DataAccessException;

public interface IMessengerDao {
	// 查找新邮件数量
	Long findNewMessageNum(String nickname) throws DataAccessException;

	// 查找会员个数
	Long findMemberinfoNum() throws DataAccessException;

	// 通过年龄，性别，城市查找朋友
	List<Memberinfo> findFriends(String age, String gender, String city)
			throws DataAccessException;

	// 保存短信
	void saveMessage(Messagerecord message) throws DataAccessException;

	// 获取已发的邮件
	List<Messagerecord> listSendMessage(String senderName)
			throws DataAccessException;

	// 获取已收的邮件
	List<Messagerecord> listRecieveMessage(String recieveName)
			throws DataAccessException;

	// 查看邮件
	Messagerecord findMessage(Long id) throws DataAccessException;

	// 删除收到的邮件
	void deleteRecieveMessage(Long id) throws DataAccessException;

	// 删除收到的邮件 删除多个
	void deleteRecieveMessage(Long[] id) throws DataAccessException;

	// 删除已发的邮件
	void deleteSendMessage(Long id) throws DataAccessException;

	// 删除已发的邮件 删除多个
	void deleteSendMessage(Long[] id) throws DataAccessException;
}
