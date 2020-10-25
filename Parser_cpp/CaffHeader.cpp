//
//  caffHeader.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <cstdint>

class CaffHeader {
  private:
    char magic[5];
    int64_t header_size;
    int64_t num_anim;
    
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
    
    void setHeader_size(int64_t l) {
        header_size = l;
    }
    
    int64_t getHeader_size() {
        return header_size;
    }
    
    void setNum_anim(int64_t n) {
        num_anim = n;
    }
    
    int64_t getNum_anim() {
        return num_anim;
    }
    
};
