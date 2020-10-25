//
//  CaffCredits.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <cstdint>

class CaffCredits {
  private:
    short YY;
    char M;
    char D;
    char h;
    char min;
    int64_t creator_len;
    char* creator;
    
  public:
    CaffCredits(int64_t len) {
        creator_len = len;
        creator = new char[creator_len + 1];
    }
    
    ~CaffCredits() {
        delete[] creator;
    }
    
    void setM(char m) {
        M = m;
    }
    
    char getM() {
        return M;
    }
    
    void setD(char d) {
        D = d;
    }
    
    char getD() {
        return D;
    }
    
    void setH(char H) {
        h = H;
    }
    
    char getH() {
        return h;
    }
    
    void setMin(char minute) {
        min = minute;
    }
    
    char getMin() {
        return min;
    }
    
    void setYY(short yy) {
        YY = yy;
    }
    
    short getYY() {
        return YY;
    }
    
    void setCreatorLen(int64_t len) {
        creator_len = len;
    }
    
    int64_t getCreatorLen() {
        return creator_len;
    }
    
    void setCreator(char* ch) {
        memcpy(creator, ch, creator_len);
        creator[creator_len] = '\0';
    }
    
    char* getCreator() {
        return creator;
    }
    
};
