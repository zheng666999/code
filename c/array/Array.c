#include<Array.h>

/**
 * typedef struct 
{
    int *base;      //数组元素基址
    int dim;        //数组维数
    int *bounds;    //每一维的大小
    int *constants; //映射数组下标到线性地址的常量
}Array;
*/
int InitArray(Array *a,int dim,...){

    if(dim < 1 || dim >MAX_ARRAY_DIM){
        return -1;     // 异常抛出
    }

    a->dim = dim; //设置数组的维数

    a->bounds = (int *)malloc(dim*sizeof(int)); //为每一维的边界分配内存

    if(!(*a).bounds){
        return -1;
    }

    int elemtotal = 1;  //a中元素总数

    va_list ap; //定义变长参数列表
    va_start(ap,dim); // ap为va_list类型，是存放变长参数表信息的数组

    for(int i = 0 ; i<dim ; ++i){
        a->bounds[i] = va_arg(ap,int); //获取当前维度的大小
        if(a->bounds[i]<0){
            return -1;
        }
        elemtotal *=a->bounds[i]; //计算元素总数
    }

    va_end(ap);

    a->base = (int *)malloc(elemtotal * sizeof(int)); // 初始化所有元素
    if(!a->base){
        return -1;
    }

    a->constants = (int *)malloc(dim * sizeof(int)); //初始化每一维度的基址
    if(!a->constants){
        return -1;
    }

    a->constants[dim-1] = 1;
    for(int i = dim-2 ; i>=0 ;--i){
        a->constants[i] = a->bounds[i+1] * a->constants[i+1];
    }

    return 1;
}