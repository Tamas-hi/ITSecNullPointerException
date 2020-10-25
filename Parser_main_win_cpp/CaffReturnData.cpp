//
//  CaffReturnData.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 23..
//

#include <stdio.h>
#include <iostream>
#include <vector>
#include <string>

class CaffReturnData {
  private:
    double width;
    double height;
    std::string _caption;
    std::string _creator;
    std::vector<std::string> tags;
    std::vector<unsigned char> rgb_pixels;
    
  public:    
    void setWidth(double l) {
        width = l;
    }
    
    double getWidth() {
        return width;
    }
    
    void setHeight(double l) {
        height = l;
    }
    
    double getHeight() {
        return height;
    }
    
    void setTags(std::vector<std::string> tags_vector) {
        tags = tags_vector;
    }
    
    std::vector<std::string> getTags() {
        return tags;
    }
    
    void setCaption(std::string caption) {
        _caption = caption;
    }
    
    std::string getCaption() {
        return _caption;
    }
    
    void setCreator(std::string creator) {
        _creator = creator;
    }
    
    std::string getCreator() {
        return _creator;
    }
    
    void setRgb_pixels(const std::vector<unsigned char>& uch) {
        rgb_pixels = uch;
    }
    
    std::vector<unsigned char> getRgb_pixels() {
        return rgb_pixels;
    }
    
};
