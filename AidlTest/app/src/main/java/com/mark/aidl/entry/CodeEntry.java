package com.mark.aidl.entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mark on 2017/12/25.
 */

public class CodeEntry implements Parcelable {

    public String tag;
    public long code;

    public CodeEntry(String tag, long code) {
        this.tag = tag;
        this.code = code;
    }

    protected CodeEntry(Parcel in) {
        tag = in.readString();
        code = in.readLong();
    }

    public static final Creator<CodeEntry> CREATOR = new Creator<CodeEntry>() {
        @Override
        public CodeEntry createFromParcel(Parcel in) {
            return new CodeEntry(in);
        }

        @Override
        public CodeEntry[] newArray(int size) {
            return new CodeEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tag);
        dest.writeLong(code);
    }
}
