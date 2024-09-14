#include<stdio.h>

struct Heap
{
    int* array;
    int count;
    int capacity;
    int heap_type;
    /* data */
};

struct Heap* createHeap(int capacity , int heap_type)
{
    struct Heap* h = (struct Heap*)malloc(sizeof(struct Heap));
    if (h == NULL){
        printf("Memory Error");
    }
    
    h->heap_type = heap_type;
    h->count = 0;
    h->array = (int*)malloc(sizeof(int)*h->capacity);
    if(h->array == NULL){
        printf("Memory Error");
        return;
    }
    return h;
}

int parent(struct Heap* h , int i )
{
    if(i<=0||i>=h->count)
        return -1;
    return (i-1)/2;
}


int leftChild(struct Heap* h , int i){
    if(i<0 || i>h->count)
        return -1;
    return 2*i+1;
}
int rightChild(struct Heap* h ,int i){
    if(i<0 || i>h->count)
        return -1;
    return 2*i + 2 ;
}


int getMaxEle(struct Heap* h ){
    if(h->count == 0){
        return -1;
    }
    if(1 == 1){ //小顶
        //遍历最后一层
    }else{
        return h->array[0];
    }
    
}



