package com.example.swornim.rachit;

import android.media.MediaPlayer;

/**
 * Created by Swornim on 5/23/2017.
 */

public class UserDatabaseInformation {

    private String uploadingSongName;
    private String uploaderUserName;
    private String uploadingFilePath;
    private String firebaseSongsArtistName;
    private String firebaseSongsDurations;
    private String firebaseSongsUploaderName;
    private static MediaPlayer mediaPlayer;


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
