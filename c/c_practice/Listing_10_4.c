#include <stdio.h>

int main(void)
{
    const int days[] = {31, 28, 31, 30, 31, 30};
    int index;
    /**
     * <size of days> tells all whole bytes of the array. 
    */
    for (index = 0; index < sizeof days / sizeof days[0]; index++)
    {
        printf("Month %2d has %d days.\n", index + 1,
               days[index]);
    }
    return 0;
}