//
//  CiffHeader.cpp
//  caffOpen
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <stdio.h>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class CiffHeader {
  private:
    char magic[5];
    long header_size;
    long content_size;
    long width;
    long height;
    char* caption;
    long _caption_len;
    std::vector<string> tags;
    
    
  public:
    CiffHeader(long caption_len) {
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
    
    void setHeader_size(long l) {
        header_size = l;
    }
    
    long getHeader_size() {
        return header_size;
    }
    
    void setContent_size(long l) {
        content_size = l;
    }
    
    long getContent_size() {
        return content_size;
    }
    
    void setWidth(long l) {
        width = l;
    }
    
    long getWidth() {
        return width;
    }
    
    void setHeight(long l) {
        height = l;
    }
    
    long getHeight() {
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
