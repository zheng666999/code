#include<stdarg.h>

#define MAX_ARRAY_DIM 8
typedef struct 
{
    int *base;      //数组元素基址
    int dim;        //数组维数
    int *bounds;    //数组维界基址
    int *constants; //数组映像函数常量基址
}Array;

int initArray(Array *a , int dim,...);
int destroyArray(Array *a,...);
int value(Array *a,int e,...);
int assign(Array *a,int e,...);
