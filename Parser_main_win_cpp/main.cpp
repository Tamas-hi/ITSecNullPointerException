//
//  main.cpp
//  caffFinalParser
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <iostream>

#include <fstream>
#include <stdio.h>
#include <vector>
#include <string>

#include "CaffHeader.cpp"
#include "CaffCredits.cpp"
#include "CaffAnimation.cpp"
#include "CiffHeader.cpp"
#include "CiffContent.cpp"
#include "CaffReturnData.cpp"

// Print all bytes
void dumpbytes(const vector<unsigned char>& v)
{
    for (int i=0; i<v.size(); ++i)
    {
        cout << v[i] << " ";
        if ((i + 1) % 8 == 0)
            printf("\n");
    }
    printf("\n");
}

// Convert char array to long
long buffToLong(char* buffer) {
    long l;
    memcpy(&l, buffer, 8);
    return l;
}

// Convert char array to int
int buffToInt(char* buffer) {
    int i;
    memcpy(&i, buffer, 4);
    return i;
}

// Convert char array to short
short buffToShort(char* buffer) {
    short s;
    memcpy(&s, buffer, 2);
    return s;
}

// Convert vector to long
long subvectorToLong(const std::vector<unsigned char>& subvector) {
    long l;
    char chars[8];
    
    for (int i = 0; i < 8; i++) {
        chars[i] = subvector.at(i);
    }
    
    l = buffToLong(chars);
    
    return l;
}

// Convert vector to short
short subvectorToShort(const std::vector<unsigned char>& subvector) {
    short s;
    char chars[2];
    
    for (int i = 0; i < 2; i++) {
        chars[i] = subvector.at(i);
    }
    
    s = buffToShort(chars);
    
    return s;
}

// Convert vector to CaffHeader
CaffHeader subvectorToHeader(const std::vector<unsigned char>& subvector) {
    CaffHeader caffHeader = CaffHeader();
    
    char magic[4];
    long header_size;
    long num_anim;
    
    for (int i = 0; i < 4; i++) {
        magic[i] = subvector.at(i);
    }
    
    std::vector<unsigned char> size_vector(&subvector[4], &subvector[4 + 8]);
    header_size = subvectorToLong(size_vector);
    
    std::vector<unsigned char> num_vector(&subvector[4 + 8], &subvector[subvector.size()]);
    num_anim = subvectorToLong(num_vector);
    
    caffHeader.setMagic(magic);
    caffHeader.setHeader_size(header_size);
    caffHeader.setNum_anim(num_anim);
    
    return caffHeader;
}

// Convert vector to CaffCredits
CaffCredits subvectorToCredits(const std::vector<unsigned char>& subvector) {
    short year;
    char month;
    char day;
    char hour;
    char minute;
    long creator_len;
    
    std::vector<unsigned char> year_vector(&subvector[0], &subvector[2]);
    year = subvectorToShort(year_vector);
    
    month = subvector.at(2);
    day = subvector.at(3);
    hour = subvector.at(4);
    minute = subvector.at(5);
    
    std::vector<unsigned char> len_vector(&subvector[6], &subvector[6 + 8]);
    creator_len = subvectorToLong(len_vector);
    
    CaffCredits caffCredits = CaffCredits(creator_len);
    
    std::vector<unsigned char> creator_vector(&subvector[6 + 8], &subvector[subvector.size()]);
    
    char creator[creator_len];
    
    for(int k = 0; k < creator_vector.size(); k++) {
        creator[k] = creator_vector.at(k);
    }
    
    caffCredits.setYY(year);
    caffCredits.setM(month);
    caffCredits.setD(day);
    caffCredits.setH(hour);
    caffCredits.setMin(minute);
    caffCredits.setCreator(creator);
    
    return caffCredits;
}

// Convert vector to CaffAnimation
CaffAnimation subvectorToAnimation(const std::vector<unsigned char>& subvector) {
    CaffAnimation caffAnimation = CaffAnimation();
    
    long duration;
    
    std::vector<unsigned char> duration_vector(&subvector[0], &subvector[8]);
    duration = subvectorToLong(duration_vector);
    
    caffAnimation.setDuration(duration);
    
    return caffAnimation;
}

// Convert vector to CiffHeader
CiffHeader subvectorToCiffHeader(const std::vector<unsigned char>& subvector) {
    char magic[4];
    long header_size;
    long content_size;
    long width;
    long height;
    
    int _current_location = 0;
    
    for (int i = 0; i < 4; i++) {
        magic[i] = subvector.at(i);
    }
    
    _current_location = 4;
    
    std::vector<unsigned char> header_size_vector(&subvector[_current_location], &subvector[_current_location + 8]);
    header_size = subvectorToLong(header_size_vector);
    
    _current_location = _current_location + 8;
    
    std::vector<unsigned char> content_size_vector(&subvector[_current_location], &subvector[_current_location + 8]);
    content_size = subvectorToLong(content_size_vector);
    
    _current_location = _current_location + 8;
    
    std::vector<unsigned char> width_vector(&subvector[_current_location], &subvector[_current_location + 8]);
    width = subvectorToLong(width_vector);
    
    _current_location = _current_location + 8;
    
    std::vector<unsigned char> height_vector(&subvector[_current_location], &subvector[_current_location + 8]);
    height = subvectorToLong(height_vector);
    
    _current_location = _current_location + 8;
    
    int q = _current_location;
    while (subvector.at(q) != '\n') {
        q++;
    }
    
    int caption_length = q - _current_location;
    
    char caption[caption_length + 1];
    
    for(int k = 0; k < caption_length; k++) {
        caption[k] = subvector.at(k + _current_location);
    }
    _current_location = _current_location + caption_length + 1;
    
    std::vector<unsigned char> tags_vector(&subvector[_current_location], &subvector[header_size]);
    
    std::vector<string> tags_string_vector;
    
    int tags_location = 0;
    
    while(tags_location < tags_vector.size()) {
        
        int i = tags_location;
        string new_string;
        while(tags_vector.at(i) != '\0') {
            new_string+=tags_vector.at(i);
            i++;
        }
        tags_string_vector.push_back(new_string);
        tags_location = ++i;
    }
    
    CiffHeader ciffHeader = CiffHeader(caption_length + 1);
    ciffHeader.setMagic(magic);
    ciffHeader.setHeader_size(header_size);
    ciffHeader.setContent_size(content_size);
    ciffHeader.setWidth(width);
    ciffHeader.setHeight(height);
    ciffHeader.setCaption(caption);
    ciffHeader.setTags(tags_string_vector);
    
    return ciffHeader;
}

// Parse image
CaffReturnData readBlocks(const vector<unsigned char>& v)
{
    char block_id = 0;
    long block_length;
    long block_beg = 0;
    bool creditsIsPresent = false;
    
    CaffReturnData caff_return = CaffReturnData();
    
    if(v.at(block_beg) != '\x01') {
        return caff_return;
    }
    
    long i = 0;
    while(i < v.size()) {
        block_id = v.at(block_beg);
        std::vector<unsigned char> sub(&v[block_beg + 1], &v[block_beg + 8 + 1]);
        block_length = subvectorToLong(sub);
        i = block_beg + 1 + 8;
        if(block_id == '\x01') {
            std::vector<unsigned char> header_data(&v[i], &v[i + block_length]);
            CaffHeader caff_header = subvectorToHeader(header_data);
            cout << "--- CAFF HEADER --- \n";
            cout << "Magic: " << caff_header.getMagic() << "\n";
            cout << "Header size: "<< caff_header.getHeader_size() << "\n";
            cout << "Number of animated CIFFs: "<< caff_header.getNum_anim() << "\n";
            cout << "---\n\n";
        } else if (block_id == '\x02') {
            creditsIsPresent = true;
            std::vector<unsigned char> credits_data(&v[i], &v[i + block_length]);
            CaffCredits caff_credits = subvectorToCredits(credits_data);
            cout << "--- CAFF CREDITS --- \n";
            cout << "Year: " << caff_credits.getYY() << "\n";
            cout << "Month: " << (int)caff_credits.getM() << "\n";
            cout << "Day: " << (int)caff_credits.getD() << "\n";
            cout << "Hour: " << (int)caff_credits.getH() << "\n";
            cout << "Minute: " << (int)caff_credits.getMin() << "\n";
            cout << "Length of Creator: " << caff_credits.getCreatorLen() << "\n";
            cout << "Creator: " << caff_credits.getCreator() << "\n";
            caff_return.setCreator(string(caff_credits.getCreator()));
            cout << "---\n\n";
        } else if (block_id == '\x03') {
            std::vector<unsigned char> anim_data(&v[i], &v[i + block_length]);
            CaffAnimation caff_animation = subvectorToAnimation(anim_data);
            cout << "--- CAFF ANIMATION --- \n";
            cout << "Block length: " << block_length << "\n";
            cout << "Duration: " << caff_animation.getDuration() << "\n";
            
            cout << "   *** CIFF HEADER *** \n";
            std::vector<unsigned char> ciff_data(&v[i + 8], &v[i + block_length]);
            CiffHeader ciff_header = subvectorToCiffHeader(ciff_data);
            cout << "   Magic: " << ciff_header.getMagic() << "\n";
            cout << "   Header size: " << ciff_header.getHeader_size() << "\n";
            cout << "   Content size: " << ciff_header.getContent_size() << "\n";
            cout << "   Width: " << ciff_header.getWidth() << "\n";
            cout << "   Height: " << ciff_header.getHeight() << "\n";
            cout << "   Caption: " << ciff_header.getCaption() << "\n";
            cout << "   Tags:" << "\n";
            
            for(int tags_count = 0; tags_count < ciff_header.getTags().size(); tags_count++) {
                cout << "      Tag " << tags_count <<": " << ciff_header.getTags().at(tags_count) <<"\n";
            }
            cout << "---\n\n";
            
            caff_return.setWidth(ciff_header.getWidth());
            caff_return.setHeight(ciff_header.getHeight());
            caff_return.setCaption(string(ciff_header.getCaption()));
            caff_return.setTags(ciff_header.getTags());
            
            std::vector<unsigned char> ciff_content_vector(&v[i + 8 + ciff_header.getHeader_size()], &v[i + block_length]);
            CiffContent ciff_content = CiffContent();
            ciff_content.setRgb_pixels(ciff_content_vector);
            
            caff_return.setRgb_pixels(ciff_content.getRgb_pixels());
            
            //One CiffContent is enough for preview
            if(creditsIsPresent) {
                break;
            }
        }
        
        block_beg = 1 + 8 + block_length + block_beg;
        i = block_beg;
    }
    
    return caff_return;
}

int main(int argc, const char * argv[]) {
    if(argc > 1) {
        std::streampos fileSize;
        ifstream caffFile (argv[1], ios::in | ios::binary);
        
        caffFile.seekg(0, std::ios::end);
        fileSize = caffFile.tellg();
        caffFile.seekg(0, std::ios::beg);
        
        std::vector<unsigned char> fileData(fileSize);
        caffFile.read((char*) &fileData[0], fileSize);
        
        char* number = new char[8];
        for (int i=0; i<8; ++i) {
            number[i] = fileData.at(i+1);
        }
        
        CaffReturnData returndata = readBlocks(fileData);
        
        ofstream img("image.ppm");
        img << "P3" << endl;
        img << returndata.getWidth() << " " << returndata.getHeight() << endl;
        img << "255" << endl;
        
        std::vector<unsigned char> ciff_content_pixels = returndata.getRgb_pixels();
        for(int k = 0; k < returndata.getHeight() * returndata.getWidth() * 3; k+=3) {
            img << (int)ciff_content_pixels.at(k) << " " << (int)ciff_content_pixels.at(k + 1) << " " << (int)ciff_content_pixels.at(k + 2) << " " << endl;
        }
        
        img.close();
    } else {
        cout << "Pass one argument" << endl;
    }
    
    return 0;
}
