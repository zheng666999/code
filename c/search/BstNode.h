#define LH 1;
#define EH 0;
#define RH -1;

typedef struct BstNode{
    int data;
    int bf;
    struct BstNode * lchild,*rchild;
}BstNode, *BstTree;

void l_rotate(BstTree root);
