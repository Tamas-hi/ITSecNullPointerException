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
    long long header_size;
    long long content_size;
    long long width;
    long long height;
    char* caption;
    long long _caption_len;
    std::vector<string> tags;
    
    
  public:
    CiffHeader(long long caption_len) {
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
    
    void setHeader_size(long long l) {
        header_size = l;
    }
    
    long long getHeader_size() {
        return header_size;
    }
    
    void setContent_size(long long l) {
        content_size = l;
    }
    
    long long getContent_size() {
        return content_size;
    }
    
    void setWidth(long long l) {
        width = l;
    }
    
    long long getWidth() {
        return width;
    }
    
    void setHeight(long long l) {
        height = l;
    }
    
    long long getHeight() {
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