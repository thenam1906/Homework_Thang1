package com.example.thangpham.freemusic.network;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ThangPham on 12/27/2017.
 */
// strict: cho phép lấy location luôn, k phải phụ thuộc vào các thẻ khác
@Root(name="tracklist", strict = false)
public class TrackListXML {
    @Element(name ="track") // ten phai đúng giống như trog xml
    public TrackXML track;
}
