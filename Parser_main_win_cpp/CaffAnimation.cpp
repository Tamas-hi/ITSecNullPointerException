//
//  CaffAnimation.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>

class CaffAnimation {
  private:
    long duration;
    
  public:
    CaffAnimation() {
    }
    
    void setDuration(long dur) {
        duration = dur;
    }
    
    long getDuration() {
        return duration;
    }
    
};
