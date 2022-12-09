package com.boolaw.myapplication;

public class LeetCode26 {
    public int removeDuplicates(int[] nums) {
        int j=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[j]){
                nums[j+1]=nums[i];
                j++;
            }
        }
        return j+1;
    }

    public static void main(String[] args) {
        new LeetCode26().removeDuplicates(new int[]{1,2,3});
//        new LeetCode26().removeDuplicates(new int[]{1,2,2,3});
    }
}
