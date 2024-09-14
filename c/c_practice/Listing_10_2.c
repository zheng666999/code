#include<stdio.h>
#define size 10

/**
 * 未初始化的数组，每个元素会被随机赋值，Yours results vary from the last.
 * 
*/
int main(void){
    int array[size];
    int index;
    for(index = 0;index<size;index++){
        printf("%2d%14d\n", index, array[index]);
    }

    return 0;
}
/**
 * 部分初始化的数组，其余元素会被初始化为0
*/
/**
 * 也可以不指定初始大小，交给编译器决定
*/