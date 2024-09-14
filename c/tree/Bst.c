#include<stdio.h>
#include"Node.h"
#include"InOrderTraversal.h"

void test(){
    int array[] = {2,3,1,6,5,4};
    node* root = createBst(array);
    inorderTraversal(root);
}

node* createBst(int* array){
    node* root = (node*)malloc(sizeof(node));
    root->value = array[0];

    int len = sizeof(array);
    for(int i= 1 ;i<len ; i++){
        node* parent = findPos(root,array[i]);
        if(parent == NULL){
            printf("error");
            return NULL;
        }
        node* oneNode = (node*)malloc(sizeof(node));
        if(parent->value > array[i]){
            parent->left = oneNode;
        }else{
            parent->right = oneNode;
        }
    }
    return root;
}

/*寻找#{toin}的父节点
*/
node* findPos(node* root,int toin){
    if(root == NULL){
        return NULL;
    }
    if(root->left == NULL && root->right == NULL){
        return root;
    }

    if(root->value > toin){
        if(root->left == NULL){
            return root;
        }else{
            return findPos(root->left,toin);
        }
    }else{
        if(root->right == NULL){
            return NULL;
        }else{
            return findPos(root->right,toin);
        }
    }
}