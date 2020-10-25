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
    double duration;
    
  public:
    CaffAnimation() {
    }
    
    void setDuration(double dur) {
        duration = dur;
    }
    
    long getDuration() {
        return duration;
    }
    
};
