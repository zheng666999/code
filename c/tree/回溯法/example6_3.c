#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define charEnd '\0'
void traverse(char *sourceArr, char **result, char *onceArr, int *cur, int onceSize, int traversalSize);

/**
 * 求 n 个元素的集合的幂集
 */
int main(void)
{
    printf("function start\n");

    char elements[] = {'1', '2', '3', '4', '\0'}; // 初始数组
    int size = sizeof(elements) / sizeof(char);
    int num = (1 << (size - 1)); // 数组子集数量

    char **result = (char **)malloc(num * sizeof(char *));

    if (NULL == result)
    {
        // 异常情况捕获
        return -1;
    }
    char *oneArr = malloc(size * sizeof(char));
    if(oneArr == NULL){
        return -1;
    }
    for (int i = 0; i < size; i++)
    {
        oneArr[i] = charEnd; // 初始化全是非法值
    }
    // 回溯法或者说递归遍历
    printf("traverse start\n");
    int cur = 0;
    traverse(elements, result, oneArr, &cur, 0, 0);
    printf("traverse end\n");

    // todo打印二维char数组
    for (int i = 0; i < num; i++)
    {
        printf("%d ele: %s\n", i + 1, result[i]);
        free(result[i]); // 释放内存
    }
    free(result);
    free(oneArr);
    return 0;
}
// cur使用指针的原因是，cur其实是最终结果的遍历，在每次记录结果的时候，就可以将cur指针后移，同时这个指针也得全局看到，由此使用指针修改这个地址的数据
void traverse(char *sourceArr, char **result, char *onceArr, int *cur, int onceSize, int traversalSize)
{
    if (sourceArr[traversalSize] == charEnd)
    {
        // 记录结果
        result[*cur] = malloc((strlen(onceArr)+1) * sizeof(char));
        if (result[*cur] == NULL)
        {
            printf("malloc error");
            return;
        }
        strcpy(result[*cur], onceArr);
        *cur = *cur + 1;
        printf("once traverse end ,arrive leaf node");

        return;
    }

    // 加入一次
    onceArr[onceSize] = sourceArr[traversalSize];
    traverse(sourceArr, result, onceArr, cur, onceSize + 1, traversalSize + 1);

    // 恢复数据
    onceArr[onceSize] = charEnd;
    // 不加入
    traverse(sourceArr, result, onceArr, cur, onceSize, traversalSize + 1);
}

/**
 * break point
 *
 * break traverse
 * break 57
 * break 67
 * break 72
 * break 77
 */

                        //              []
                        //             /  \
                        //            /    \
                        //           /      \
                        //          /        \
                        //        [1]         []
                        //        / \        / \
                        //       /   \      /   \
                        //    [1,2]  [1]   [2]  []