//
//  CiffConent.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <vector>

class CiffContent {
  private:
    std::vector<unsigned char> rgb_pixels;
    
  public:
    CiffContent() {
    }
    
    void setRgb_pixels(std::vector<unsigned char>& uch) {
        rgb_pixels = uch;
    }
    
    std::vector<unsigned char> getRgb_pixels() {
        return rgb_pixels;
    }
   
};
