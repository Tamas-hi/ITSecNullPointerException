//
//  main.cpp
//  caffFinalParser
//
//  Created by Norbert Gál on 2020. 10. 22..
//

#include <iostream>
#include "com_nullpointerexception_ParserJNI.h"

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
#include <bitset>

// Print all bytes
void dumpbytes(const vector<unsigned char>& v)
{
    for (int i=0; i<v.size(); ++i)
    {
        cout << bitset<8>(v[i]) << " ";
        if ((i + 1) % 8 == 0)
            printf("\n");
    }
    printf("\n");
}

// Convert char array to long
int64_t buffToLong(char* buffer) {
    int64_t l;
    memcpy(&l, buffer, sizeof(int64_t));
    return l;
}

// Convert char array to int
int buffToInt(char* buffer) {
    int i;
    memcpy(&i, buffer, sizeof(int));
    return i;
}

// Convert char array to short
short buffToShort(char* buffer) {
    short s;
    memcpy(&s, buffer, sizeof(short));
    return s;
}

// Convert vector to long
int64_t subvectorToLong(const std::vector<unsigned char>& subvector) {
    int64_t l;
    char chars[8];
    
    for (int i = 0; i < sizeof(int64_t); i++) {
        chars[i] = subvector.at(i);
    }
    
    l = buffToLong(chars);
    
    return l;
}

// Convert vector to short
short subvectorToShort(const std::vector<unsigned char>& subvector) {
    short s;
    char chars[2];
    
    for (int i = 0; i < sizeof(short); i++) {
        chars[i] = subvector.at(i);
    }
    
    s = buffToShort(chars);
    
    return s;
}

// Convert vector to CaffHeader
CaffHeader subvectorToHeader(const std::vector<unsigned char>& subvector) {
    CaffHeader caffHeader = CaffHeader();
    
    char magic[4];
    int64_t header_size;
    int64_t num_anim;
    
    for (int i = 0; i < 4; i++) {
        magic[i] = subvector.at(i);
    }
    
    std::vector<unsigned char> size_vector(&subvector[4], &subvector[4 + sizeof(int64_t)]);
    header_size = subvectorToLong(size_vector);
    
    std::vector<unsigned char> num_vector(&subvector[4 + sizeof(int64_t)], &subvector[subvector.size()]);
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
    int64_t creator_len;
    
    std::vector<unsigned char> year_vector(&subvector[0], &subvector[sizeof(short)]);
    year = subvectorToShort(year_vector);
    
    month = subvector.at(2);
    day = subvector.at(3);
    hour = subvector.at(4);
    minute = subvector.at(5);
    
    std::vector<unsigned char> len_vector(&subvector[6], &subvector[6 + sizeof(int64_t)]);
    creator_len = subvectorToLong(len_vector);
    
    CaffCredits caffCredits = CaffCredits(creator_len);
    
    std::vector<unsigned char> creator_vector(&subvector[6 + sizeof(int64_t)], &subvector[subvector.size()]);
    
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
    
    int64_t duration;
    
    std::vector<unsigned char> duration_vector(&subvector[0], &subvector[sizeof(int64_t)]);
    duration = subvectorToLong(duration_vector);
    
    caffAnimation.setDuration(duration);
    
    return caffAnimation;
}

// Convert vector to CiffHeader
CiffHeader subvectorToCiffHeader(const std::vector<unsigned char>& subvector) {
    char magic[4];
    int64_t header_size;
    int64_t content_size;
    int64_t width;
    int64_t height;
    
    int _current_location = 0;
    
    for (int i = 0; i < 4; i++) {
        magic[i] = subvector.at(i);
    }
    
    _current_location = 4;
    
    std::vector<unsigned char> header_size_vector(&subvector[_current_location], &subvector[_current_location + sizeof(int64_t)]);
    header_size = subvectorToLong(header_size_vector);
    
    _current_location = _current_location + sizeof(int64_t);
    
    std::vector<unsigned char> content_size_vector(&subvector[_current_location], &subvector[_current_location + sizeof(int64_t)]);
    content_size = subvectorToLong(content_size_vector);
    
    _current_location = _current_location + sizeof(int64_t);
    
    std::vector<unsigned char> width_vector(&subvector[_current_location], &subvector[_current_location + sizeof(int64_t)]);
    width = subvectorToLong(width_vector);
    
    _current_location = _current_location + sizeof(int64_t);
    
    std::vector<unsigned char> height_vector(&subvector[_current_location], &subvector[_current_location + sizeof(int64_t)]);
    height = subvectorToLong(height_vector);
    
    _current_location = _current_location + sizeof(int64_t);
    
    int q = _current_location;
    while (subvector.at(q) != '\n') {
        q++;
    }
    
    int caption_length = q - _current_location;
    
    char caption[caption_length];
    
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
    
    CiffHeader ciffHeader = CiffHeader(caption_length);
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
    int64_t block_length;
    int64_t block_beg = 0;
    bool creditsIsPresent = false;
    
    CaffReturnData caff_return = CaffReturnData();
    
    int64_t i = 0;
    while(i < v.size()) {
        block_id = v.at(block_beg);
        std::vector<unsigned char> sub(&v[block_beg + 1], &v[block_beg + sizeof(int64_t) + 1]);
        block_length = subvectorToLong(sub);
        i = block_beg + sizeof(char) + sizeof(int64_t);
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
            std::vector<unsigned char> ciff_data(&v[i + sizeof(int64_t)], &v[i + block_length]);
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
            
            std::vector<unsigned char> ciff_content_vector(&v[i + sizeof(int64_t) + ciff_header.getHeader_size()], &v[i + block_length]);
            CiffContent ciff_content = CiffContent();
            ciff_content.setRgb_pixels(ciff_content_vector);
            
            caff_return.setRgb_pixels(ciff_content.getRgb_pixels());
            
            //One CiffContent is enough for preview
            if(creditsIsPresent) {
                break;
            }
        }
        
        block_beg = sizeof(char) + sizeof(int64_t) + block_length + block_beg;
        i = block_beg;
    }
    
    return caff_return;
}

JNIEXPORT jobject JNICALL Java_com_nullpointerexception_ParserJNI_readData(JNIEnv * env, jobject, jbyteArray array) {
    
    // Create the object of the class CaffData
    jclass caffDataClass = env->FindClass("com/nullpointerexception/CaffData");
    jobject newCaffData = env->AllocObject(caffDataClass);
    
    // Get the CaffData fields to be set
    jfieldID captionField = env->GetFieldID(caffDataClass, "caption", "Ljava/lang/String;");
    jfieldID creatorField = env->GetFieldID(caffDataClass, "creator_name", "Ljava/lang/String;");
    jfieldID widthField = env->GetFieldID(caffDataClass, "image_width", "J");
    jfieldID heightField = env->GetFieldID(caffDataClass, "image_height", "J");
    jfieldID tagsField = env->GetFieldID(caffDataClass, "tags", "[Ljava/lang/String;");
    jfieldID pixelsField = env->GetFieldID(caffDataClass, "pixels", "[I");
    
    // Read CAFF file
    int array_length = env -> GetArrayLength(array);
    std::vector<unsigned char> data(array_length);
    env -> GetByteArrayRegion(array, 0, array_length, reinterpret_cast<jbyte*>(data.data()));
    CaffReturnData return_data = readBlocks(data);
    
    // caption
    int64_t string_length = return_data.getCaption().length();
    char caption[string_length + 1];
    strcpy(caption, return_data.getCaption().c_str());
    jstring jstrCaption = env -> NewStringUTF(caption);
    env -> SetObjectField(newCaffData, captionField, jstrCaption);
    
    // width
    env->SetLongField(newCaffData, widthField, (jlong)return_data.getWidth());
    
    // height
    env->SetLongField(newCaffData, heightField, (jlong)return_data.getHeight());
    
    // creator_name
    string_length = return_data.getCreator().length();
    char creator[string_length + 1];
    strcpy(creator, return_data.getCreator().c_str());
    jstring jstrCreator = env -> NewStringUTF(creator);
    env -> SetObjectField(newCaffData, creatorField, jstrCreator);
    
    // tags
    std::vector<string> tags_vector = return_data.getTags();
    jobjectArray tags_jobjectArray = env->NewObjectArray((jsize)tags_vector.size(), env->FindClass("java/lang/String"), nullptr);
    for (int i = 0; i < tags_vector.size(); i++) {
        env->SetObjectArrayElement(tags_jobjectArray, i, env->NewStringUTF(tags_vector.at(i).c_str()));
    }
    env->SetObjectField(newCaffData, tagsField, tags_jobjectArray);
    
    //pixels
    std::vector<unsigned char> v = return_data.getRgb_pixels();
    std::vector<int> rgb(v.size() / 3);
    
    int j = 0;
    for(int i = 0; i < v.size(); i+= 3) {
        int c = v.at(i);
        c = (c << 8) + v.at(i + 1);
        c = (c << 8) + v.at(i + 2);
        rgb[j] = c;
        j++;
    }
    
    int* pixels = &rgb[0];
    jintArray pixels_jintArray = env->NewIntArray((jsize)v.size() / 3);
    env->SetIntArrayRegion(pixels_jintArray, 0, (jsize)v.size() / 3, (jint*)pixels);
    env->SetObjectField(newCaffData, pixelsField, pixels_jintArray);
    
    return newCaffData;
}
