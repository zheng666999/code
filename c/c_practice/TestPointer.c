#include <stdio.h>

/**
 * 一级指针 指向的是     变量 的地址
 * 二级指针 指向的是 一级指针变量 的地址
*/
void modifyPointer(int **pp);

void modifyPointer(int **pp) // *pp代表指向“int类型变量”的地址，那么**pp代表指向“存储int类型变量地址的变量”的地址
{
    static int b = 20;
    *pp = &b; // 解引用 *pp 操作的是“存储int类型变量地址的变量”，原本该变量指向的a地址，现在指向b的地址
    b=30;
}

void modifyPointer2(int *pp) // pp是一个指向 "int类型变量" 地址的 变量
{
    static int b = 20;
    pp = &b; // pp指向了b的地址，但是不会修改“原本指向变量的地址”的存储值
}
void modifyPointer3(int *pp) // pp是一个指向 "int类型变量" 地址的 变量
{
    static int b = 20;
    *pp = b; // 解引用，修改了pp指向的 "int类型变量"的值
}

int main()
{
    int a = 10;
    int *p = &a;
    printf("Before: %d\n", *p); 
    modifyPointer(&p);
    printf("After: %d\n", *p); //p被修改为指向30的地址
    printf("Aftera: %d\n", a); //a不变
    modifyPointer2(&a);
    printf("After2: %d\n", *p); //参数传递的是a的地址，不影响p，p不变
    printf("After2a: %d\n", a); //方法内部修改的是指针变量指向的地址，不影响a，a不变
    modifyPointer3(&a);
    printf("After3: %d\n", *p); //参数传递的是a的地址，不影响p，p不变
    printf("After3a: %d\n", a); //方法内部通过解引用，修改了地址存储的"实际值",a变化
    return 0;
}