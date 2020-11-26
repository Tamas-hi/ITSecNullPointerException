package com.webshop.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class CaffData {
	
    public String creator_name;
    public long image_width;
    public long image_height;
    public String caption;
    public String[] tags;
    public int[] pixels;
}
