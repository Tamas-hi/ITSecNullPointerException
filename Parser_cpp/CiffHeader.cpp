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
#include <cstring>
#include <cstdint>

using namespace std;

class CiffHeader {
  private:
    char magic[5];
    int64_t header_size;
    int64_t content_size;
    int64_t width;
    int64_t height;
    char* caption;
    int64_t _caption_len;
    std::vector<string> tags;
    
    
  public:
    CiffHeader(int64_t caption_len) {
        _caption_len = caption_len;
        caption = new char[_caption_len + 1];
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
    
    void setHeader_size(int64_t l) {
        header_size = l;
    }
    
    int64_t getHeader_size() {
        return header_size;
    }
    
    void setContent_size(int64_t l) {
        content_size = l;
    }
    
    int64_t getContent_size() {
        return content_size;
    }
    
    void setWidth(int64_t l) {
        width = l;
    }
    
    int64_t getWidth() {
        return width;
    }
    
    void setHeight(int64_t l) {
        height = l;
    }
    
    int64_t getHeight() {
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
