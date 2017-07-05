package com.example.swornim.rachit;

import android.media.MediaPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swornim on 5/23/2017.
 */

public class UserDatabaseInformation implements Serializable{

    private String uploadingSongName;
    private String uploaderUserName;
    private String uploadingFilePath;
    private String firebaseSongsArtistName;
    private String firebaseSongsDurations;
    private String firebaseSongsUploaderName;
    private static MediaPlayer mediaPlayer;
    private List<UserDatabaseInformation> filteredData=new ArrayList<>();


    public List<UserDatabaseInformation> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<UserDatabaseInformation> filteredData) {
        this.filteredData = filteredData;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        UserDatabaseInformation.mediaPlayer = mediaPlayer;
    }

    public String getUploaderUserName() {
        return uploaderUserName;
    }

    public void setUploaderUserName(String uploaderUserName) {
        this.uploaderUserName = uploaderUserName;
    }

    public String getUploadingSongName() {
        return uploadingSongName;
    }

    public void setUploadingSongName(String uploadingSongName) {
        this.uploadingSongName = uploadingSongName;
    }

    public String getUploadingFilePath() {
        return uploadingFilePath;
    }

    public void setUploadingFilePath(String uploadingFilePath) {
        this.uploadingFilePath = uploadingFilePath;
    }

    public String getFirebaseSongsArtistName() {
        return firebaseSongsArtistName;
    }

    public void setFirebaseSongsArtistName(String firebaseSongsArtistName) {
        this.firebaseSongsArtistName = firebaseSongsArtistName;
    }

    public String getFirebaseSongsDurations() {
        return firebaseSongsDurations;
    }

    public void setFirebaseSongsDurations(String firebaseSongsDurations) {
        this.firebaseSongsDurations = firebaseSongsDurations;
    }

    public String getFirebaseSongsUploaderName() {
        return firebaseSongsUploaderName;
    }

    public void setFirebaseSongsUploaderName(String firebaseSongsUploaderName) {
        this.firebaseSongsUploaderName = firebaseSongsUploaderName;
    }
}
