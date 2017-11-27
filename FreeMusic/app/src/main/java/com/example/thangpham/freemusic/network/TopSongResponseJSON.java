package com.example.thangpham.freemusic.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ThangPham on 11/25/2017.
 */

public class TopSongResponseJSON {
    public FeedJSON feed;

    public class FeedJSON {
        public List<Entry> entry;

        public  class Entry {
            @SerializedName("im:name")
            public NameJSON name;
            @SerializedName("im:image")
            public List<ImageJSON> image;
            @SerializedName("im:artist")
            public ArtistJSON artist;

            public class NameJSON {
                public String label;
            }

            public class ImageJSON {
                public String label;

            }

            public class ArtistJSON {
                public String label;
            }
        }
    }
}
