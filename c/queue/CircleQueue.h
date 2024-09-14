#define MAXSIZE sizeof(int)*100
typedef  struct 
{
    int* array;
    int front; //头指针
    int rear; //尾指针
}CircleQueue;