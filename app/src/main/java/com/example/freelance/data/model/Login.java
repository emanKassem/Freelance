package com.example.freelance.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("meta")
    @Expose
    private Token meta;

    public class Token{
        @SerializedName("token")
        @Expose
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public Token getMeta() {
        return meta;
    }

    public void setMeta(Token meta) {
        this.meta = meta;
    }
}
