#include<stdio.h>
#include"Node.h"

/** 中序遍历
*/
void inorderTraversal(node* root){
    if(root == NULL){
        return;
    }
    inorderTraversal(root->left);
    printf(root->value);
    inorderTraversal(root->right);
}