package firework.hyl.running.common.util;

import firework.hyl.running.dao.IMemberDao;
import firework.hyl.running.dao.IMessengerDao;
import firework.hyl.running.service.IMemberService;
import firework.hyl.running.service.IMessengerService;

public class BeanFactory {
	public static String MEMBERDAO = "memberDao";
	public static String MEMBERSERVICE = "memberService";
	public static String MESSENGERDAO = "messengerDao";
	public static String MESSENGERSERVICE = "messengerService";

	private static IMemberDao memberDao;
	private static IMemberService memberService;
	private static IMessengerDao messengerDao;
	private static IMessengerService messengerService;

	public static Object getBean(String beanName) {
		if (beanName.equals(MEMBERDAO)) {
			memberDao = getMemberDao();
			return memberDao;
		}
		if (beanName.equals(MEMBERSERVICE)) {
			memberService = getMemberService();
			return memberService;
		}
		if (beanName.equals(MESSENGERDAO)) {
			messengerDao = getMessengerDao();
			return messengerDao;
		}
		if (beanName.equals(MESSENGERSERVICE)) {
			messengerService = getMessengerService();
			return messengerService;
		}

		return null;
	}

	synchronized private static IMemberDao getMemberDao() {

		return memberDao;
	}

	synchronized private static IMemberService getMemberService() {
		return memberService;
	}

	synchronized private static IMessengerDao getMessengerDao() {
		return messengerDao;
	}

	synchronized private static IMessengerService getMessengerService() {
		return messengerService;
	}
}
