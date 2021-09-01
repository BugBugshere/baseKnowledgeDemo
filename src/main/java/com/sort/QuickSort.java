package com.sort;

import cn.hutool.core.lang.Console;

/*快排算法-手写
 * 递归 + 分治
 * */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{1,8,43,44,2,5,21};
        quickSort(nums,0,nums.length-1);
        Console.log(nums);
    }

    public static void quickSort(int[] nums, int L, int R) {
        //递归结束条件
        if (L >= R) return;

        int left = L, right = R;
        //初始化定标
        int pivot = nums[left];
        //整理数组，并记录好left,R重合后的位置
        while (left < right) {
            //处理R尾
            while (left < right && nums[right] >= pivot) right--;
            if (left < right) {
                //发现值小于pivot尾部元素
                nums[left] = nums[right];
            }
            //处理L首
            while (left < right && nums[left] <= pivot) left++;
            if (left < right) {
                //发现值大于pivot的首部元素
                nums[right] = nums[left];
            }
            //当发现L,R重合后，说明此次整理结束
            if (left >= right) {
                nums[right] = pivot;
            }
        }
        //递归调用前半部分
        quickSort(nums, L, right - 1);
        //递归调用后半部分
        quickSort(nums, right + 1, R);
    }
}
