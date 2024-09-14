#include<stdio.h>

int main(){
    int array[] = {1,2,3,4};
    sort(array,0,3);
}

void sort(int array[],int low,int high){
    int sign;
    if(low<high){
        sign = partition(array,low,high);
        sort(array,low,sign-1);
        sort(array,sign+1,high);
    }
}

int partition(int* array , int low , int high){

    int left = low;
    int right = high;
    int sign = array[left];
    while(left<right){
        while( array[left] <= sign ){
            left++;
        }
        while( array[right] >= sign){
            right--;
        }
        if(left < right){
            swap(array,left,right);
        }
        array[low] = array[right];
        array[right] = sign;
        return right;
    }
}