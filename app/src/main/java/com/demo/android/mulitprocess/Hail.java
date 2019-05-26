package com.demo.android.mulitprocess;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by herr.wang on 2017/8/28.
 */

public class Hail implements Parcelable {
    public String from;
    public String content;
    public int id;
    public Hail(String from, String content, int id){
        this.from = from;
        this.content = content;
        this.id = id;
    }

    protected Hail(Parcel in) {
        from = in.readString();
        content = in.readString();
        id = in.readInt();
    }

    public static final Creator<Hail> CREATOR = new Creator<Hail>() {
        @Override
        public Hail createFromParcel(Parcel in) {
            return new Hail(in);
        }

        @Override
        public Hail[] newArray(int size) {
            return new Hail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(content);
        dest.writeInt(id);
    }

    public String toString(){
        return "from " + from + ",content " + content + ",id " + id;
    }
}
