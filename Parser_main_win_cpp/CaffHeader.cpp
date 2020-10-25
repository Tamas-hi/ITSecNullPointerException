//
//  caffHeader.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <cstring>

class CaffHeader {
  private:
    char magic[5];
    long long header_size;
    long long num_anim;
    
  public:
    CaffHeader() {
    }
    
    void setMagic(char* c) {
        strncpy(magic, c, 4);
        magic[4] = '\0';
    }
    
    char* getMagic() {
        return magic;
    }
    
    void setHeader_size(long long l) {
        header_size = l;
    }
    
    long long getHeader_size() {
        return header_size;
    }
    
    void setNum_anim(long long n) {
        num_anim = n;
    }
    
    long long getNum_anim() {
        return num_anim;
    }
    
};
