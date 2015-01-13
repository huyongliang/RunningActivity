package firework.hyl.running.service;

import java.util.List;

import firework.hyl.running.common.bean.Friendrecord;
import firework.hyl.running.common.bean.Graderecord;
import firework.hyl.running.common.bean.Memberinfo;
import firework.hyl.running.common.bean.Memberspace;
import firework.hyl.running.common.bean.Pointaction;
import firework.hyl.running.common.bean.Pointrecord;
import firework.hyl.running.common.exception.MemberServiceException;

public interface IMemberService {
	// 用户注册
	void registerMemberinfo(Memberinfo memberinfo) throws MemberServiceException;
	
	// 按照姓名查找用户
	Memberinfo findMemberinfoByName(String nickname) throws MemberServiceException;
	
	// 通过用户名登录
	Memberinfo login(String username, String passwd) throws MemberServiceException;
	
	// 登出
	void logout(String nickname) throws MemberServiceException;
	
	// 查找前几名用户
	List<Memberinfo> findMemberinfoByNum(int number) throws MemberServiceException;
	
	// 查找在线用户
	Long findMemberinfoOnline() throws MemberServiceException;
	
	// 按照积分查找等级
	Graderecord findMemberinfoLevel(Long point) throws MemberServiceException;
	
	// 通过提示问题和答案来获取密码
	String getBackPasswd(String nickname, String pwdQuestion, String pwdAnswer) throws MemberServiceException;
	
	// 保存或者更新用户
	Memberinfo saveOrUpDate(Memberinfo memberinfo, String oldPasswd) throws MemberServiceException;

	// 保存或修改用户信息
	void saveOrUpdate(Memberinfo memberinfo) throws MemberServiceException;
	
	// 保存或修改好友列表
	void saveOrUpdate(String selfname, String friendname) throws MemberServiceException;
	
	// 查找好友
	List<Memberinfo> listFriend(String selfname) throws MemberServiceException;
	
	// 转到黑名单
	void moveToBlack(String selfname, String blackname) throws MemberServiceException;
	
	// 获取黑名单人员
	List<Memberinfo> listBlack(String selfname) throws MemberServiceException;
	
	// 查找好友
	Friendrecord findFriend(String friend) throws MemberServiceException;
	
	// 判断是否有个人空间
	Boolean isMemberspace(Long id) throws MemberServiceException;
	
	boolean haveMemberSpace(String nickName) throws MemberServiceException;
	
	// 转到好友
	void moveToFriend(String selfName, String name_searching) throws MemberServiceException;
	
	// 删除黑名单
	void deleleBlack(String selfName, String blackName) throws MemberServiceException;
	
	// 删除黑名单 删除多个
	void deleleBlack(String selfName, String[] blackNames) throws MemberServiceException;
	
	// 删除好友
	void deleleFriend(String selfName, String friendName) throws MemberServiceException;
	
	//删除多个好友
	void deleleFriend(String selfName, String[] friendNames) throws MemberServiceException;
	
	// 删除空间
	void delSpace(Long id) throws MemberServiceException;

	// 按照名字查找积分动作
	Pointaction findPointactionByPointAction(String actionName)throws MemberServiceException;

	// 保存加积分的记录
	void save(Pointrecord pointrecord) throws MemberServiceException;
	
	//修改用户信息
	void memberinfoModify(String oldPwd,Memberinfo member)throws MemberServiceException;
	
	//保存个人空间
	void saveSpace(Memberinfo memberinfo,Memberspace ms) throws MemberServiceException;
	//随机匹配一个好友
	Memberinfo matchFriend()throws MemberServiceException;
	//查找所有用户
	List<Memberinfo> findAllMembers()throws MemberServiceException;
	
	
}
