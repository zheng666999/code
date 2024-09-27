#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define invalidFlag -1
#define validFlag    1

/**
 * 这是一个四皇后问题，由于四个皇后没有先后顺序，并且，根据问题本事，这四个皇后一定出现在不同列不同行
 * 问题就简化为上面的幂集求解问题，例如
 * 皇后一在第一行的**列
 * 皇后二在第二行的**列
 * 皇后三在第三行的**列
 * 皇后四在第四行的**列
*/
int main(void){

    int queues[] = {0,0,0,0,0}; //0位置空出来
    int size = sizeof(queues)/sizeof(int);
    int num = size*size*size*size;
    int** result = malloc((num)*sizeof(int *)); //0位置空出来
    if(result == NULL){
        return -1;
    }

    int cur = 1; // 遍历的第几个皇后
    int sign = 1; //遍历的位置
    traverse(queues,result,cur,sign,size);

    //释放
    for(int i = 0 ; i<num;i++){
        if(result[i]!=NULL){
            free(result[i]);
        }
    }
    free(result);
    return 0;
}

void traverse(int *queue , int **result ,int cur , int sign,int queueNum){
    if(cur >= queueNum){
        //加入result
        return;  //数组越界
    }
    int flag = isValid(queue,cur,sign);
    if(flag == invalidFlag){
        printf("位置非法");
        return;
    }
    //继续遍历
    for(int i = (sign+1)%queueNum ; i<queueNum;i++){
        traverse(queue,result,cur+1,i,queueNum);
    }
}

//检查合法性
int isValid(int *queue,int cur , int sign){
    for(int i = 1 ; i<= cur ; i++){
        if(cur == i){
            return invalidFlag;
        }
        if(queue[cur] == queue[i]){
            return invalidFlag;
        }
        if(queue[cur]/queue[i] == cur/i){
            return invalidFlag;
        }
    }
    return validFlag;
}