#include "SString.h"
#include <stdio.h>
#include <stdlib.h>



int Concat(SString t, const SString s1, const SString s2)
{

    if (s1[0] + s2[0] <= MAXSTRLEN)
    {
        memcpy(t + 1, s1 + 1, s1[0]);
        memcpy(t + 1 + s1[0], s2 + 1, s2[0]);
        t[0] = s1[0] + s2[0];
    }
    else if (s1[0] < MAXSTRLEN)
    {
        memcpy(t + 1, s1 + 1, s1[0]);
        memcpy(t + 1 + s1[0], s2 + 1, s2[MAXSTRLEN - s1[0]]);
        t[0] = MAXSTRLEN;
    }
    else
    {
        memcpy(t + 1, s1 + 1, MAXSTRLEN);
        t[0] = MAXSTRLEN;
    }
    return 0;
}

int subString(SString sub, const SString s, int pos, int len)
{
    if (pos < 1 || pos > s[0] || len < 0 || len > s[0] - pos + 1)
    {
        return -1;
    }
    memcy(sub + 1, s[pos], s[pos + len - 1]);
    sub[0] = len;
    return -1;
}

//最简单的字符串模式匹配算法，最差情况 O(m*n)
int index(SString s, SString t, int pos)
{
    int i = pos;
    int j = 1;
    while (i <= s[0] && j <= t[0])
    {
        if (s[i] == t[j])
        {
            ++i;
            ++j;
        }
        else
        {
            i = i - j + 2;
            j = 1;
        }
    }
    if (j > t[0])
    {
        return i - t[0]; //不是i-t[0]+1的原因是 在上面循环中++j导致循环退出时，++i导致多出来一位
    }
    else
    {
        return 0;
    }
}

//回溯的不是i指针，而是j指针
int index_KMP(SString s, SString t, int pos,int *next){
    int i = pos; 
    int j = 1;
    while(i<=s[0]&&j<=t[0]){
        if(j==0||s[i]==t[j]){
            ++i;
            ++j;
        }else{
            j = next[j];
        }
    }
    if(j>t[0]) return i-t[0];
    return 0;
}

//返回的下标是否为0，
/**
 * 第一个问题，假设第一个返回下标是1 next[1] = 1  
 *
 *       abdefg 与 ac 比较
 *     next[2] = {1,1}
 *         当i=1,j=1时，s[i] = t[j] i = i+1 = 2 ,j=j+1=2
 *         当i=2,j=2时，s[i]!=t[j]  j = next[j] = next[2] = 1  i = i = 2
 *         当i=2,j=1时，s[i]!=t[j]  j = next[j] = next[1] =1   i =i =2
 *         ... 陷入无限循环，所以next[1]=0其实是结束这一次部分匹配的标识     //注意对于C标准库，这里应该next[0] = -1
 *  
 * 第二个问题，就是如何求解next[j] , 
 *      我们常理解next[j]代表j前面(不包括j)子串最大匹配前缀后缀。正常思维到了j，求解该位置next[j]
 *      这个函数采用反正常思维理解 由于next[j]是一个与前面相关的数组，所以可以已知next[1...j],求解next[j+1]
 *          
 *          以abcab模式串为例
 *          next[1] = 0;双指针i=1,j=0
 *          当j=0,i=1时，此时代表子串没有最大匹配前缀，i=i+1=1+1=2 ; j=j+1=1 ; next[i]=next[2]=j=1  
 *                                   //这句话的含义就是2以前的子串没有最大部分前缀，那么这种情况是否对其他场景适用呢
 *          当j=1,i=2时 ， t[j]=a  t[i]=b t[i]!=t[j]  j = next[j] = next[1] = 0  i = i          next[5] = {0,1,null,null,null}
 *          当j=0,i=2时 ， 回到第一步j=0的特殊情况，i=i+1=3;j=j+1=1;next[i]=next[3]=j=1           next[5] = {0,1,1,null,null}
 *          当j=1,i=3时 ， 重复第二步t[i]!=t[j]     j = next[j] = next[1] = 0  i = i
 *          当j=0,i=3时 ， 回到第一步j=0的特殊情况  i=i+1=4;j=j+1=1;next[i]=next[4]=j=1            next[5] = {0,1,1,1,null}
 *          当j=1,i=4时 ， t[i] = t[j] = a   i = i+1 = 5  j = j+1 = 2  next[5] = 2
 *
 * 
 * 下面的代码是不是很眼熟，与上面kmp匹配部分很相似，注意其实就是差不多的含义
 * 但是注意虽然给next赋值，其实每次循环时给next[i+1]赋值，因为next[1...i]已知了
 * j可以理解为严**书里面的k的角色，当然有解释说这是前面匹配的长度，但是由于这里SString的特殊定义，其实如果使用c标准库，这里应该是就是对的上常规解释了
 *   */      
void getNext(SString t , int *next){
    int j = 0;
    int i = 1; //i就是
    next[1] = 0;

    while(i<t[0]){
        if(j==0 || t[i] == t[j]){
            ++i;
            ++j;
            next[i] = j;
        }else{
            j = next[j];
        }
    }
}

