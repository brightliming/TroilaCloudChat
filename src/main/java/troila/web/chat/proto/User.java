package troila.web.chat.proto;

import java.io.Serializable;

/**
 * 
 * @ClassName: User 
 * @Description: 用户表user对应的bean
 * @author: troila_cyw
 * @date: 2018年3月28日 下午3:03:08
 */
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;			//用户主键ID
	private Integer enterpriseId; 	//用户所在企业ID
	private Integer departmentId;	//用户所在部门ID
	private String departmentName; 	//部门名称
	private String userName; 		//用户名
	private String enterpriseName; 	//用户所在企业名称
	private String userPassword; 	//密码
	private String userPhone; 		//用户手机号
	private Integer userRole; 		//用户角色：1：主播 2：观众  企业管理员默认主播/普通员工默认观众
	private Long createTime; 		//创建时间
	private String userEmail; 		//用户邮箱
	private Integer userType;		//用户类型：0：超级管理员 1：企业管理员 2：普通员工
	//数据库不存在但是封装成bean需要
	private String phoneVerifcode;	//手机验证码
	private String clientIp;//用户ip地址
	public User() {}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", enterpriseId=" + enterpriseId + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", userName=" + userName + ", enterpriseName=" + enterpriseName
				+ ", userPassword=" + userPassword + ", userPhone=" + userPhone + ", userRole=" + userRole
				+ ", createTime=" + createTime + ", userEmail=" + userEmail + ", userType=" + userType
				+ ", phoneVerifcode=" + phoneVerifcode + ", clientIp=" + clientIp + "]";
	}
	
	
	public User(Integer enterpriseId, String departmentName, String userName, String userPhone, String userEmail, Integer userRole, String userPassword) {
		super();
		this.enterpriseId = enterpriseId;
		this.departmentName = departmentName;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}


	public User(String userName, String enterpriseName, String userPassword, String userPhone,String phoneVerifcode) {
		super();
		this.userName = userName;
		this.enterpriseName = enterpriseName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.phoneVerifcode = phoneVerifcode;
	}
	public User(Integer userId, Integer departmentId, String departmentName, String userName, String userPassword, String userPhone, Integer userRole,
			String userEmail) {
		super();
		this.userId = userId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userRole = userRole;
		this.userEmail = userEmail;
	}
	
	public User(Integer departmentId, String departmentName, String userName,  String userPassword, String userPhone, Integer userRole,
			String userEmail, Integer enterpriseId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.enterpriseId = enterpriseId;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getPhoneVerifcode() {
		return phoneVerifcode;
	}
	public void setPhoneVerifcode(String phoneVerifcode) {
		this.phoneVerifcode = phoneVerifcode;
	}
	
	
}
