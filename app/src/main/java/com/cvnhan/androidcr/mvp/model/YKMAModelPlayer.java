package com.cvnhan.androidcr.mvp.model;

import android.app.Application;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.cvnhan.androidcr.NCMCCoreApp;
import com.cvnhan.androidcr.mvp.model.local.YKMADBSong;
import com.cvnhan.androidcr.utils.YKMAUtilStatic;

import java.io.File;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

import static com.cvnhan.androidcr.core.utils.NCMCUtilRxHelper.*;


/**
 * Created by NhanCao on 13-Sep-15.
 */
public class YKMAModelPlayer {
    private final String TAG = YKMAModelPlayer.class.getSimpleName();
    private final Application application;
    private final YKMAIModelApi apiModel;

    public YKMAModelPlayer(Application application, YKMAIModelApi apiModel) {
        this.application = application;
        this.apiModel = apiModel;
    }

    public Observable<ArrayList<YKMADBSong>> getAllAudioFiles() {
        return Observable.mergeDelayError(getFromLocal(), getFromSDCard());
    }

    public Observable<ArrayList<YKMADBSong>> getFromLocal() {
        return Observable.defer(() -> Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getInstance(((NCMCCoreApp) application).getConfig0());
                final RealmResults<YKMADBSong> dbSongs = realm.allObjects(YKMADBSong.class);
//                Log.e(TAG, dbSongs.size() + " getFromLocal");
                ArrayList<YKMADBSong> songList = new ArrayList<>();
                for (YKMADBSong dbSong : dbSongs) {
                    songList.add(YKMADataMapping.clone(dbSong));
                }
                onNext(subscriber, songList);
            } catch (Exception e) {
                onError(subscriber, e);
            } finally {
                if (realm != null) {
                    realm.close();
                }
                onCompleted(subscriber);
            }
        }));
    }

    private Observable<ArrayList<YKMADBSong>> getFromSDCard() {
        return Observable.defer(() -> Observable.create(subscriber -> {
            Realm realm = null;
            try {
                realm = Realm.getInstance(((NCMCCoreApp) application).getConfig0());
                ArrayList<YKMADBSong> songList = getPlayList(application.getApplicationContext());
//                Log.e(TAG, songList.size() + " getFromSDCard");
                realm.beginTransaction();
                for (YKMADBSong song : songList) {
                    realm.copyToRealmOrUpdate(song);
                }
                realm.commitTransaction();
                onNext(subscriber, songList);
            } catch (Exception e) {
                onError(subscriber, e);
            } finally {
                if (realm != null) {
                    realm.close();
                }
                onCompleted(subscriber);
            }
        }));
    }


    public ArrayList<YKMADBSong> getPlayList(Context context) {
        ArrayList<YKMADBSong> songsList = new ArrayList<>();
        if (YKMAUtilStatic.MEDIA_PATH != null) {
            File home = new File(YKMAUtilStatic.MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        scanDirectory(songsList, file, context);
                    } else {
                        addSongToList(songsList, file, context);
                    }
                }
            }
        }
        // return songs list array
        return songsList;
    }


    private void scanDirectory(ArrayList<YKMADBSong> songsList, File directory, Context context) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(songsList, file, context);
                    } else {
                        addSongToList(songsList, file, context);
                    }

                }
            }
        }
    }

    private void addSongToList(ArrayList<YKMADBSong> songsList, File song, Context context) {
        if (song.getName().endsWith(YKMAUtilStatic.MP3PATTERN)) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            Uri uri = Uri.fromFile(song);
            mediaMetadataRetriever.setDataSource(context, uri);
            YKMADBSong dbSong = YKMADBSong.newInstance(song.getPath(),
                    song.getName().substring(0, (song.getName().length() - 4)),
                    mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                    mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST),
                    mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR),
                    mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE),
                    Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
            songsList.add(dbSong);
        }
    }

    public String getInfoAudio(Context context, File mp3File, int CODE) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        Uri uri = Uri.fromFile(mp3File);
        mediaMetadataRetriever.setDataSource(context, uri);
//        String title = (String) mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        return mediaMetadataRetriever.extractMetadata(CODE);
    }
}
