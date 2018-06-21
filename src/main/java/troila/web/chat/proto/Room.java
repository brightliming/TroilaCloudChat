package troila.web.chat.proto;

import java.util.List;

/**
 * 
 * @ClassName:  Room   
 * @Description:TODO(直播间信息实体类)   
 * @author: 卓朗科技_limingliang
 * @date:   2018年6月21日 下午5:06:34   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Room {
	/**
	 * 直播间ID
	 */
	private String id;

	/**
	 * 直播间名称
	 */
	private String name;
	
	/**
	 * 直播间观众数
	 */
	private int count;
	
	/**
	 * 直播间用户列表
	 */
	private List<Person> personList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

}
