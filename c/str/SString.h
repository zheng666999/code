#define MAXSTRLEN 255

//typedef 为已有类型定义新的别名
//下标为0的数组分量存储串的长度，c默认的String是以'\0'
//下面这种设计有点小问题，就是长度受限
typedef unsigned char SString[MAXSTRLEN + 1]; 

int Concat(SString t, const SString s1, const SString s2);// 字符串拼接
int subString(SString sub, const SString s, int pos, int len);
int index(SString s, SString t, int pos); // 返回子串t在主串s中第pos个字符的位置
int index_KMP(SString s, SString t, int pos,int *next);//需要一个next数组

