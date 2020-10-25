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
    double header_size;
    double num_anim;
    
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
    
    void setHeader_size(double l) {
        header_size = l;
    }
    
    double getHeader_size() {
        return header_size;
    }
    
    void setNum_anim(double n) {
        num_anim = n;
    }
    
    double getNum_anim() {
        return num_anim;
    }
    
};
