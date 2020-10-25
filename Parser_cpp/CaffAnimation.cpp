//
//  CaffAnimation.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <cstdint>

class CaffAnimation {
  private:
    int64_t duration;
    
  public:
    CaffAnimation() {
    }
    
    void setDuration(int64_t dur) {
        duration = dur;
    }
    
    int64_t getDuration() {
        return duration;
    }
    
};
