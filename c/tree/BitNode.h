#define INTERROR -1
#include<stdlib.h>
#include<stdio.h>

typedef struct BitNode{
    int data;
    struct BitNode *lChild,*rChild;
}BitNode;
typedef BitNode *BitTree;

int createBitTree(BitTree T,int *arr,int size,int cur);
int preOrderTraverse(BitTree T);
int inOrderTraverse(BitTree T);
int postOrderTraverse(BitTree T);
int levelOrderTraverse(BitTree T);

