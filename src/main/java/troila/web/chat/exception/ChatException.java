/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  ChatException.java   
 * @Package troila.web.chat.exception   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 上午10:00:45   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.exception;

/**
 * @ClassName: ChatException
 * @Description:TODO(聊天室自定义异常)
 * @author: 卓朗科技 _limingliang
 * @date: 2018年6月22日 上午10:00:45
 * 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved.
 *             注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ChatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ErrorType errorType = ErrorType.UNKNOW;

	private String errorMessage = null;

	/**自定义异常构造器，传入错误类型和源异常*/  
    public ChatException(ErrorType errorType, Throwable t){  
        super(t);  
        this.errorType = errorType;  
        this.errorMessage = "["+errorType.getMessage()+ "]";  
    }

	/**自定义异常构造器，传入错误信息和源异常*/  
    public ChatException(String errorMessage, Throwable t){  
        super(t);  
        this.errorMessage = errorMessage;  
    }

	/**自定义异常构造器，传入异常类型、错误信息、源异常*/  
    public ChatException(ErrorType errorType,String errorMessage, Throwable t){  
        super(t);  
        this.errorType = errorType;  
        this.errorMessage = "[" +errorType.getMessage()+"]-" + errorMessage;  
    }

	/**自定义异常构造器，传入异常类型、错误信息*/  
    public ChatException(ErrorType errorType,String errorMessage){  
        this.errorType = errorType;  
        this.errorMessage = "[" +errorType.getMessage()+ "]-" + errorMessage;  
    }

	/**自定义异常构造器，传入错误信息*/  
    public ChatException(String errorMessage){  
        this.errorMessage = errorMessage;  
    }

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 返回异常信息
	 */
	public String getMessage() {
		if (this.errorMessage != null) {
			return this.errorMessage;
		} else {
			return "未知的错误";
		}
	}

	public String toString() {
		String s = getClass().getName();
		String message = getMessage();
		return (message != null) ? s + ":" + message : s;
	}
}
