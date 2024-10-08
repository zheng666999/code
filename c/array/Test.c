#define MAX_ARRAY_DIM 8
typedef struct 
{
    int *base;
    int dim;
    int *bounds;
    int *constants;
}Array;

int main(){
    
    Array *a = malloc(sizeof(Array));
    a->dim = 1;
    (*a).dim = 2;
    return 1;

}