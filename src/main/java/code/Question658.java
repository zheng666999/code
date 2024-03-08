package code;

/**
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。

整数 a 比整数 b 更接近 x 需要满足：

    |a - x| < |b - x| 或者
    |a - x| == |b - x| 且 a < b

 

示例 1：

输入：arr = [1,2,3,4,5], k = 4, x = 3
输出：[1,2,3,4]

示例 2：

输入：arr = [1,2,3,4,5], k = 4, x = -1
输出：[1,2,3,4]

 */

/**
 * 往一个结果集里面添加（在没满的时候）， 记录最大差值、位置
 * 达到上限制，比较当前差值与最大差值，最后替换位置还是替换后最后排序？
 */
public class Question658 {
		
	
	public void getRes(int[] arr , int x , int k) {
		
		int[] res = new int[k];
		
		int[] diff = new int[k];
		
		//初始化
 		diff[0] = arr[0]-x > 0 ? arr[0]-x : x-arr[0];
 		res[0] = arr[0];
 		
 		
		for(int i = 1 ; i < arr.length ; i++) {
			int tempDiff = arr[i]-x > 0 ? arr[i]-x : x-arr[i];
			if(tempDiff<diff[i-1]) {
				
			}
 		}
		
		
	}
}
