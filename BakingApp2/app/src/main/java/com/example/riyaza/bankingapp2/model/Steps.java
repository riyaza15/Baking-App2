package com.example.riyaza.bankingapp2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    private String stepId;
    private String stepShortDescription;
    private String stepDescription;
    private String videoUrl;

    private String thumbnailUrl;

    public Steps(String stepId, String stepShortDescription, String stepDescription, String videoUrl, String thumbnailUrl) {
        this.stepId = stepId;
        this.stepShortDescription = stepShortDescription;
        this.stepDescription = stepDescription;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }


    protected Steps(Parcel in) {
        stepId = in.readString();
        stepShortDescription = in.readString();
        stepDescription = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getStepId() {
        return stepId;
    }

    public String getStepShortDescription() {
        return stepShortDescription;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stepId);
        parcel.writeString(stepShortDescription);
        parcel.writeString(stepDescription);
        parcel.writeString(videoUrl);
        parcel.writeString(thumbnailUrl);
    }
}
