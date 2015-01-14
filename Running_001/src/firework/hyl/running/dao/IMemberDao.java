package firework.hyl.running.dao;

import java.util.List;

import firework.hyl.running.common.bean.Blackrecord;
import firework.hyl.running.common.bean.Friendrecord;
import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.bean.Pointrecord;
import firework.hyl.running.common.exception.DataAccessException;

public interface IMemberDao {

	/**
	 * 随机查找 memCount 条记录
	 * 
	 * @param memCount
	 * @return
	 */
	List<Memberinfo> findNMemberRandom(int memCount) throws DataAccessException;

	// 按姓名查找用户
	Memberinfo findMemberinfoByName(String name) throws DataAccessException;

	// 保存或更新用户
	void saveOrUpdateMemberinfo(Memberinfo memberinfo)
			throws DataAccessException;

	// 查找前几名用户
	List<Memberinfo> findMemberinfoByNum(int number) throws DataAccessException;

	// 查找在线用户数量
	Long findMemberinfoOnline() throws DataAccessException;

	// 按照积分查找等级
	Graderecord findMemberinfoLevel(Long point) throws DataAccessException;

	// 按照积分动作查找Pointaction类
	Pointaction findPointactionByPointAction(String pointAction)
			throws DataAccessException;

	// 保存积分记录
	void saveOrUpdatePointrecord(Pointrecord pointrecord)
			throws DataAccessException;

	// 保存好友信息
	void saveOrUpdateFriend(Friendrecord friend) throws DataAccessException;

	// 保存黑名单会员
	void saveOrUpdateFriend(Blackrecord friend) throws DataAccessException;

	// 查找本人的所有好友
	List<Memberinfo> listFriend(String selfname) throws DataAccessException;

	// 查找本人的所有黑名单
	List<Memberinfo> listBlack(String selfname) throws DataAccessException;

	// 删除黑名单会员通过Blackrecord对象
	void deleleBlack(Blackrecord black) throws DataAccessException;

	// 删除黑名单会员 根据自己的名字和黑名单中的名字
	void deleleBlack(String selfName, String blackName)
			throws DataAccessException;

	// 删除黑名单会员 删除多个黑名单记录
	void deleleBlack(String selfName, String[] blackNames)
			throws DataAccessException;

	// 删除好友通过Friendrecord对象
	void deleleFriend(Friendrecord friend) throws DataAccessException;

	// 删除好友通过自己的名字和好友的名字
	void deleleFriend(String selfName, String friendName)
			throws DataAccessException;

	// 删除好友 删除多个好友
	void deleleFriend(String selfName, String[] friendNames)
			throws DataAccessException;

	// 查找好友
	Friendrecord findfriend(String selfName, String friendName)
			throws DataAccessException;

	// 查找黑名单
	Blackrecord findBlack(String selfName, String blackName)
			throws DataAccessException;

	// 查找个人空间
	Memberspace findSpace(Long id) throws DataAccessException;

	// 删除个人空间
	void delSpace(Memberspace space) throws DataAccessException;

	// 随机查找一名好友
	Memberinfo findFriendRandom(int number) throws DataAccessException;

	// 查找所有用户
	List<Memberinfo> findAllMembers() throws DataAccessException;

}
