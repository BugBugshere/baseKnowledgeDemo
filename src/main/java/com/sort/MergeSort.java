package com.sort;

import cn.hutool.core.lang.Console;

/*
归并排序 - 分治+递归
稳定性好
* */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[]{1,8,43,44,2,5,21};
        mergeSort(nums,0,nums.length-1);
        Console.log(nums);
    }

    public static void mergeSort(int[] nums, int low, int high) {
        //递归条件结束
        if (low >= high) return;
        //int mid = low + ((high - low)/2);
        int mid = low + (high - low >> 1);
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        //归并
        merge(nums, low, mid + 1, high);

    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int []tmp = new int[nums.length];
        //对待归并的两个子数组做合并
        //将值保存至临时数组
        int l = low,r = mid;
        int tmpl = l;
        //打擂，两个子数组比较
        while(l<mid&&r<=high){
            //左边子数组元素小
            if(nums[l]<nums[r]){
                tmp[tmpl++] = nums[l++];
            }else{
                tmp[tmpl++] = nums[r++];
            }
        }
        //右边数组数组结束，左边还剩余的部分
        while(l<mid){
            tmp[tmpl++] = nums[l++];
        }
        //左边数组数组结束，右边还剩余的部分
        while(r<=high){
            tmp[tmpl++] = nums[r++];
        }

        //将临时数组的数据复制到原数组
        int i =low,j=low;
        while(i<=high){
            nums[i++] = tmp[j++];
        }



    }
}
