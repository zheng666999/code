package code;

import java.util.HashMap;

import org.apache.regexp.recompile;

/**
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
换句话说，s1 的排列之一是 s2 的 子串 。
 
示例 1：
输入：s1 = "ab" s2 = "eidbaooo"
输出：true
解释：s2 包含 s1 的排列之一 ("ba").

示例 2：
输入：s1= "ab" s2 = "eidboaoo"
输出：false

 */
public class Question567 {


}


/**
 * 笨方法，不合适使用
 * 需要记住，最终答案出现的时候，右指针与左指针的区间一定是等于短字符串的长度的，这就是必要的点
 * 如何判断新的字符是否在这里？
 * 如何在发现这个区间出现异常字符，发生移动的时候，重置？
 */
class 笨方法 {
	String shortString = "";
	String longString = "";
	HashMap<String, Integer> sourceHashMap = new HashMap<String, Integer>();
	
	/**
	 * -1非法
	 */
	public boolean findCur() { 
		int start = 0 , end = 0;
		int max = longString.length();
		boolean flag = false;
		//第一层循环start一点点左移
		while(start<max) {
			HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
			//第二层循环end左移
			while(end<max) {
				//持续遍历
				String curString = String.valueOf(longString.charAt(end));
				if(sourceHashMap.containsKey(curString))  {
					//存在
					if(tempHashMap.containsKey(curString)) {
						Integer curNumInteger = tempHashMap.get(curString);
						Integer sourceNumInteger = sourceHashMap.get(curString);
						//存在但是计数超过了，应该定位到出现的第一个位置后面，这里无法找到就是笨方法
						if(curNumInteger >= sourceNumInteger) {
							start = start +1;
							end = start;
							break;
						}else {
							//存在但是计数没超过了正常
							tempHashMap.put(curString, curNumInteger+1);
						}
					}else {
						tempHashMap.put(curString, 1);
					}
					end++;
				}else{
					//不存在跳出
					//下一次重置
					start = end+1;
					end = start;
					break;
				}
			}
			//完成结束
			if() {
				flag = true;
				start = max;
				end = max;
			}
		}
		
		return flag;
	}
	
	public void init() {
		for(int i = 0 ; i < shortString.length();i++) {
			String curStr = String.valueOf(shortString.charAt(i));
			if(sourceHashMap.containsKey(curStr)) {
				Integer oldInteger = sourceHashMap.get(curStr);
				sourceHashMap.put(curStr, oldInteger);
			}else {
				sourceHashMap.put(curStr, 1);
			}
			
		}
	}
}