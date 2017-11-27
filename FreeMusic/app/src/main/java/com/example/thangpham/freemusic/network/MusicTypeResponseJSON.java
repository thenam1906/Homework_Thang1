package com.example.thangpham.freemusic.network;

import java.util.List;

/**
 * Created by ThangPham on 11/15/2017.
 */

public class MusicTypeResponseJSON {
    public List<SubObjectJSON> subgenres;

    public class SubObjectJSON {
        public String id;
        public String translation_key;

        public SubObjectJSON(String id, String translation_key) {
            this.id = id;
            this.translation_key = translation_key;
        }
    }
}
