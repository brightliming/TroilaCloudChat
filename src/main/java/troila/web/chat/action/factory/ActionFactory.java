package troila.web.chat.action.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import troila.web.chat.action.IAction;
import troila.web.chat.action.impl.ActionProxy;

public class ActionFactory {
	private static Logger logger = LoggerFactory.getLogger(ActionFactory.class);
	
	private final static String ACTION_PKG = "troila.web.chat.action.impl.";
	
	private final static String ACTION_CLASS_PREFIX = "Action";
	
	public static IAction getAction(String code) {
		try {
			IAction action = (IAction)Class.forName(ACTION_PKG+ACTION_CLASS_PREFIX+code).newInstance();
			return new ActionProxy(action);
		} catch (Exception e) {
			logger.error("未找到相应的业务处理类{}",ACTION_PKG+ACTION_CLASS_PREFIX+code);
			e.printStackTrace();
		} 
		return null;
	}
}
