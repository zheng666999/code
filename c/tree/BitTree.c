#include <BitNode.h>

int createBitTree(BitTree *T, int *arr, int size, int cur)
{
    if (arr == NULL)
    {
        return INTERROR;
    }
    if (arr[cur] != INTERROR)
    {
        *T = malloc(sizeof(BitNode));
        (*T)->data = arr[cur];
        if (cur * 2 + 1 < size)
        {
            createBitTree((*T)->lChild, arr, size, cur * 2 + 1);
        }
        if (cur * 2 + 2 < size)
        {
            createBitTree((*T)->lChild, arr, size, cur * 2 + 2);
        }
    }else{
        (*T) = NULL;
    }

    return 1;
}
int preOrderTraverse(BitTree T)
{
    if (T)
    {
        printf("%d \n", T->data);
        preOrderTraverse(T->lChild);
        preOrderTraverse(T->rChild);
    }

    return 1;
}
int inOrderTraverse(BitTree T)
{
    if (T)
    {
        inOrderTraverse(T->lChild);
        printf("%d \n", T->data);
        inOrderTraverse(T->rChild);
    }

    return 1;
}
int postOrderTraverse(BitTree T)
{
    if (T)
    {
        postOrderTraverse(T->lChild);
        postOrderTraverse(T->rChild);
        printf("%d \n", T->data);
    }

    return 1;
}
int levelOrderTraverse(BitTree T)
{
}