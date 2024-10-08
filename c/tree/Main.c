#include<BitNode.h>

int main(void){
    int arr[] = {1,2,3,INTERROR,4,5,INTERROR};
    int size = sizeof(arr)/sizeof(int);
    BitTree tree = malloc(sizeof(BitNode));
    if(tree == NULL){
        printf("malloc memory error");
        return INTERROR;
    }
    int start = 0;
    int status = createBitTree(&tree,arr,size,start);
    if(status == INTERROR){
        printf("create bit tree error");
        return INTERROR;
    }
}