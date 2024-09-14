#include<stdio.h>
#include"CircleQueue.h"

void initQueue(CircleQueue &queue) {
    queue.array = (int*)malloc(MAXSIZE); // 分配队列大小
    if (!queue.array) {
        printf("内存溢出");
        return;
    }
    queue.front = 0;
    queue.rear = 0;
}

int getQueueLen(CircleQueue *queue){
    return (queue->rear - queue->front + MAXSIZE)/MAXSIZE;
}