package com.example.mynotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    private final int imageIndex;
    private final String noteName;
    private final String noteText;
    private final String noteData;


    public Notes(int imageIndex, String noteName, String noteText, String noteData) {
        this.imageIndex = imageIndex;
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteData = noteData;
    }


    protected Notes(Parcel in) {
        imageIndex = in.readInt();
        noteName = in.readString();
        noteText = in.readString();
        noteData = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageIndex);
        dest.writeString(noteName);
        dest.writeString(noteText);
        dest.writeString(noteData);
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public String getNoteData() {
        return noteData;
    }
}
