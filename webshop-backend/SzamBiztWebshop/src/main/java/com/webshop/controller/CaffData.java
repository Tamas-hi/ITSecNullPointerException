package com.webshop.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "caffdata")
public class CaffData {
	
	@Column
    public String creator_name;
	@Column
    public long image_width;
	@Column
    public long image_height;
	@Column
    public String caption;
	@Column
    public String[] tags;
	@Column
    public int[] pixels;
}
