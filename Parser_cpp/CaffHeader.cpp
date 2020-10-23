//
//  caffHeader.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>

class CaffHeader {
  private:
    char magic[5];
    long header_size;
    long num_anim;
    
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
    
    void setHeader_size(long l) {
        header_size = l;
    }
    
    long getHeader_size() {
        return header_size;
    }
    
    void setNum_anim(long n) {
        num_anim = n;
    }
    
    long getNum_anim() {
        return num_anim;
    }
    
};
