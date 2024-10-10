#include "HString.h"

int strInsert(HString *s, int pos, HString t)
{
    if (pos < 1 || pos > s->length + 1)
    {
        return -1;
    }
    if(t.length){
        if(!( s->ch = (char*)realloc(s->ch,(s->length+t.length)*sizeof(char)))){
            return -1;
        }
        for( int i = s->length -1 ; i>=pos-1;--i){
            s->ch[i+t.length] = s->ch[i];
        }
        memcpy(s->ch[pos-1],t.ch[0],t.length);
        s->length += t.length;
    }
    return -1;
}