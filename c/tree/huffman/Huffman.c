#include <stdio.h>
#include <Huffman.h>

/**
 * HT代表huffman树
 * HC代表huffman编码二维数组
 * n 代表叶子节点
 */
void HUffmanCoding(HuffmanTree HT, HuffmanCode HC, int *w, int n)
{
    if (n <= 1)
    {
        return;
    }
    int i;
    HuffmanTree p;
    int m = 2 * n - 1;
    HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNode)); // 分配huffman tree地址,HT是指向HT的指针，也可以理解为数组第一个元素的变量

    // todo 0位置为什么保留呢
    for (p = HT, i = 1; i <= n; ++i, ++p, ++w)
    { // 初始化
        HTNode temp = {*w, 0, 0, 0};
        *p = temp; // 翻译一下 HT[i] = {*w,0,0,0}
    }
    for (; i <= m; ++i, ++p)
    { // 初始化
        HTNode temp = {0, 0, 0, 0};
        *p = temp; // 翻译一下 HT[i] = {0,0,0,0}
    }
    for (i = n + 1; i <= m; ++i)
    {
        Select(HT, i - 1, s1, s2); // 从[1...i-1]选择weight最小的两个节点s1,s2
        HT[s1].parent = i;
        HT[s2].parent = i;
        HT[i].lchild = s1;
        HT[i].rchild = s2;
        HT[i].weight = HT[s1].weight + HT[s2].weight;
    }

    // 构建huffman编码
    HC = (HuffmanCode)malloc((n + 1) * sizeof(char *));
    char *cd = (char *)malloc(n * sizeof(char));  //这是一个一维数组，每次遍历都会被重新赋值，所以下面的代码看似奇怪，但是符合逻辑且满足需求
    cd[n - 1] = '\0';
    for (i = 1; i <= n; ++i)
    {
        int start = n - 1;
        int f;
        for (int c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent)
        {
            if (HT[f].lchild == c)
            {
                cd[--start] = "0"; //这里明明是从1开始遍历HT，为什么对应cd数组尾部？
            }
            else
            {
                cd[--start] = "1";
            }
        }
        HC[i] = (char *)malloc((n - start) * sizeof(char));
        strcpy(HC[i], &cd[start]);
    }
    free(cd);
}

void test(){
    HuffmanTree HT = (HuffmanTree)malloc(4*sizeof(HTNode));
    int i = 0;
    for(int i = 0 ;i<4 ; i++){
        HTNode temp = {1,1,1,1};
        HT[i] = temp;
    }

    for(int i = 0;i<4;i++){
        HT[i].parent = 4;
    }

}