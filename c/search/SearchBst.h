#include "BitNode.h"

//这是一个单纯的遍历二叉树
BitTree search(BitTree T,int key);

/**
 * 这跟第一个遍历没有太大区别，但是该函数可以返回
 * 1.查找成功的节点
 * 2.失败的时候父节点
 * 这只是一种写法，第一种方式也不是不可。
*/
int search2(BitTree T,int key,BitTree *pre , BitTree *res);

/**
 * 删除某个节点，但是首先也得遍历找到该节点的位置，
*/
int deleteNode(BitTree T , int key);

int createBst(BitTree *T);