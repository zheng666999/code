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

