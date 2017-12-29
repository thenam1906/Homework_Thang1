package com.example.thangpham.freemusic.network;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ThangPham on 12/27/2017.
 */
@Root(name="track", strict = false)
public class TrackXML {
    @Element(name="location")
    public String location;
}
