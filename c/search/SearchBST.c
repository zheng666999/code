#include "SearchBst.h"

BitTree search(BitTree T,int key){
   if(!T || T->data == key){
    return T;
   }else if(T->data > key){
    search(T->lChild,key);
   }else{
    search(T->rChild,key);
   }
}

int search2(BitTree T,int key,BitTree *pre , BitTree *res){
    if(!T){
        *res = *pre;
        return -1;
    }
    if(T->data == key){
        *res = *pre;
        return 1;
    }else if(T->data < key){
        *pre = T;
        search2(T->rChild , key , pre , res);
    }else{
        *pre = T;
        search2(T->lChild , key , pre , res);
    }
}

int  delete(BitTree T,BitTree pre){ 
    if(!T->rChild){ //右子树不存在，直接清除掉
        if(pre->lChild->data == T->data){
            pre->lChild = T->lChild;
        }else{
            pre->rChild = T->lChild;
        }
    }else if(!T->lChild){  //左子树不存在，直接清楚掉
        if(pre->lChild->data == T->data){
            pre->lChild = T->rChild;
        }else{
            pre->rChild = T->rChild;
        }
    }else{ // 左右子树都存在，直接把T的左子树接上，然后把T的右子树拼接到左子树的最右边。
        if(pre->lChild->data == T->data){
            pre->lChild = T->lChild;
        }else{
            pre->rChild = T->lChild;
        }
        BitTree cur = T->lChild;
        while(cur->rChild){
            cur = cur->rChild;
        }
        cur->rChild = T->rChild;
    }
    free(T);
    return 1;
}

/**
 * 这种删除很罕见，因为，搜索函数被提取出来了，导致删除操作和遍历分离，
 * 这样在递归搜索过程中可以获取的隐藏父节点信息就无法使用，当然，
 * 可以讲递归转换成非递归方式
*/
int deleteNode(BitTree T , int key){
    BitNode *res = NULL;
    BitNode *pre = NULL;
    int flag = search2(T,key,&pre,&res);
    if(flag != 1){
        return flag;  //没有找到
    }else if(flag == 1){   
        return delete(res,pre);  //找到删除节点
    }
}

int createBst(BitTree *T){
    int array[] = {1,2,3,4};
    int num = sizeof(array)/sizeof(int);
    for(int i = 0;i<num;i++){
        BitTree p = NULL;
        BitTree pre = NULL;
        int flag = search2(*T,array[i],&pre,&p);
        if(!p){
            *T = (BitTree)malloc(sizeof(BitNode));
            BitTree tP = *T;
            tP->data = array[i];
            tP->lChild = NULL;
            tP->rChild = NULL;
            continue;
        }
        if(flag!=1){ //没找到
            BitTree tempMode = malloc(sizeof(BitNode));
            tempMode->data = array[i];
            tempMode->lChild = NULL;
            tempMode->rChild = NULL;
            if(p->data > array[i]){
                p->lChild = tempMode;
            }else{
                p->rChild = tempMode;
            }
        }
    }
}

int printBitNode(BitTree T){
    if(!T){
        return -1;
    }
    printBitNode(T->lChild);
    printf("%d",T->data);
    printBitNode(T->rChild);
}

int main(){
    BitTree T = NULL;
    createBst(&T);
    BitTree p = T;
    p = T->rChild;
    free(T);
    printBitNode(T);
    return 1;
}

