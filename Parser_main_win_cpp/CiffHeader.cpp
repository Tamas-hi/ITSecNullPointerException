//
//  CiffHeader.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <string>
#include <cstring>
#include <vector>

using namespace std;

class CiffHeader {
  private:
    char magic[5];
    double header_size;
    double content_size;
    double width;
    double height;
    char* caption;
    double _caption_len;
    std::vector<string> tags;
    
    
  public:
    CiffHeader(double caption_len) {
        _caption_len = caption_len;
        caption = new char[_caption_len];
    }
    
    ~CiffHeader() {
        delete[] caption;
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
    
    void setContent_size(double l) {
        content_size = l;
    }
    
    double getContent_size() {
        return content_size;
    }
    
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
    
    void setCaption(char* ch) {
        strncpy(caption, ch, _caption_len);
        caption[_caption_len] = '\0';
    }
    
    char* getCaption() {
        return caption;
    }
    
    void setTags(std::vector<string> tags_vector) {
        tags = tags_vector;
    }
    
    std::vector<string> getTags() {
        return tags;
    }
};
