package cn.zhaojishun.javaBase.数据结构与算法.leecode;

import java.util.HashMap;

/**
 * @program: javaBaseLearn
 * @description:
 * @author: Shunji Zhao
 * @create: 2020-02-07 10:15
 */
public class 两数之和 {

    public static void main(String[] args) {
        /*
        给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

        示例:

        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]

    }



    /** 
     * @Description: 暴力法
     * @Param:  
     * @return:  
     * @Author: 
     * @Date:   
     */
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] r = {0, 0};
        first:for (int i = 0; i < nums.length; i++) {
            for (int i1 = i+1; i1 < nums.length; i1++) {
                if(nums[i] + nums[i1] == target){
                    r = new int[]{i, i1};
                    break first;
                }
            }
        }
        return r;
    }

    /**
     * @Description: 哈希法
     * @Param:
     * @return:
     * @Author:
     * @Date:
     */
    public static int[] twoSum2(int[] nums, int target) {
        int r[] = new int[2];
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            integerIntegerHashMap.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer integer = integerIntegerHashMap.get(target - nums[i]);
            if (integer != null && integer != i){
               r[0] = i;
               r[1] = integer;
               return r;
            }
        }
        return r;
    }
}