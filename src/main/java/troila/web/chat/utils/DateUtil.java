/**
 * 
 */
package troila.web.chat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: DateUtil
 * @Description: TODO
 * @author liuquanrui
 * 2018年4月8日
 */
public class DateUtil {

	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 将日期转为毫秒数
	 * @param date
	 * @author: liuquanrui
	 * @date: 2018年4月8日 上午9:34:28
	 */
	public static Long DateToMsec(String date) {
		long time = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  
        try {
        	time = simpleDateFormat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return time;
	}
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 将Z时间转换为UTC
	 * @param date
	 * @author: liuquanrui
	 * @date: 2018年5月18日 下午3:08:42
	 */
	public static Date TDateToUTC(String date) {
		Date time = null;
		date = date.replace("Z", " UTC");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//24小时制  
        try {
        	time = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return time;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 当前日期加一天
	 * @param date
	 * @author: liuquanrui
	 * @date: 2018年5月18日 下午3:20:27
	 */
	public static String dateAddDay(Date date) {
		String timeStr = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,1);
        date =cal.getTime();
        timeStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
		return timeStr;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 将毫秒数转为时间
	 * @param msec
	 * @author: liuquanrui
	 * @date: 2018年5月14日 下午12:49:43
	 */
	public static String MsecToDate(Long msec) {
		String str = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制  
		Date date = new Date();  
        date.setTime(msec); 
        str = simpleDateFormat.format(date);
		return str;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 将毫秒数转化位分钟
	 * @param msec
	 * @author: liuquanrui
	 * @date: 2018年5月14日 下午3:15:10
	 */
	public static Long MsecToMintune(Long msec) {
	    Long minute = msec / (60 * 1000);  
		return minute;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 计算开始时间和结束时间
	 * @param day
	 * @author: liuquanrui
	 * @date: 2018年4月26日 上午10:56:00
	 */
	public static Map<String, String> getStartAndEndTime(int day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStart = "";
		String dateEnd = "";
		dateStart = format.format(new Date()); //当前日期为开始日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);  //结束日期
	    Date monday = calendar.getTime();
	    dateEnd = format.format(monday);
        Map<String, String> map = new HashMap<String, String>();
        map.put("dateStart", dateStart);
        map.put("dateEnd", dateEnd);
        return map;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 计算开始月份和结束月份
	 * @param month
	 * @author: liuquanrui
	 * @date: 2018年4月26日 下午4:12:19
	 */
	public static Map<String, String> getStartAndEndMonth(int month){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String monthStart = "";
		String monthEnd = "";
		monthStart = format.format(new Date()); //当前日期为开始日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);  //结束日期
	    Date monday = calendar.getTime();
	    monthEnd = format.format(monday);
        Map<String, String> map = new HashMap<String, String>();
        map.put("monthStart", monthStart);
        map.put("monthEnd", monthEnd);
		return map;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 收集起始时间到结束时间所有的时间并以字符串集合方式返回
	 * @param dateStart
	 * @param dateEnd
	 * @author: liuquanrui
	 * @date: 2018年4月26日 上午10:55:19
	 */
	public static List<String> getDateBetween(String dateStart, String dateEnd) {
		return getDateBetween(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 收集起始时间到结束时间所有的时间并以字符串集合方式返回
	 * @param start
	 * @param end
	 * @author: liuquanrui
	 * @date: 2018年4月26日 上午10:55:47
	 */
	public static List<String> getDateBetween(LocalDate start, LocalDate end){
		//用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
		return Stream.iterate(start, LocalDate -> LocalDate.plusDays(1))
				//截断无限流，长度为起始时间和结束时间的差+1个
				.limit(ChronoUnit.DAYS.between(start, end)+1)
				//最后要的是字符串，map转换一下
				.map(LocalDate::toString)
				//把流收集为List
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description: 获取两个日期之间的所有月份日期
	 * @param minDate
	 * @param maxDate
	 * @author: liuquanrui
	 * @date: 2018年4月26日 下午3:23:25
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate){
		ArrayList<String> result = new ArrayList<String>();
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		    Calendar min = Calendar.getInstance();
		    Calendar max = Calendar.getInstance();

		    min.setTime(sdf.parse(minDate));
		    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		    max.setTime(sdf.parse(maxDate));
		    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		    Calendar curr = min;
		    while (curr.before(max)) {
		     result.add(sdf.format(curr.getTime()));
		     curr.add(Calendar.MONTH, 1);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return result;
	}
	
	/**
	 * 
	 * @Function: DateUtil.java
	 * @Description:  获取两个日期之间的所有日期
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author: liuquanrui
	 * @date: 2018年4月26日 下午3:43:41
	 */
	/*public static List<String> getDateBetween(String startDate, String endDate){
		List<String> result = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
			Date start = sdf.parse(startDate);//定义起始日期
			Date end = sdf.parse(endDate);//定义结束日期
			
			
		    Calendar tempStart = Calendar.getInstance();
		    tempStart.setTime(start);
		    tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    
		    Calendar tempEnd = Calendar.getInstance();
		    tempEnd.setTime(end);
		    while (tempStart.before(tempEnd)) {
		        result.add(sdf.format(tempStart.getTime()));
		        tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	}*/
	
	public static void main(String[] args) {
		//String date = "2001-03-15 15-37-05";
		System.out.println(MsecToDate(1526659200000l));
		
		//Map<String, String> map = getStartAndEndMonth(-11);
		//getMonthBetween(map.get("monthEnd"), map.get("monthStart")).forEach(System.out::println);
		System.out.println(DateToMsec(dateAddDay(TDateToUTC("2018-05-17T16:00:00.000Z"))));
		System.out.println(MsecToMintune(1526282079742l-1526281882392l));
		
	}
}
