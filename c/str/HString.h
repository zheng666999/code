//定义一种动态分配的串
struct HString{
    char *ch;
    int length;
};
typedef struct HString HString;
typedef HString * HSPointer;