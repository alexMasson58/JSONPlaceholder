package com.masson.alex.jsonplaceholder.ui.photolist;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.Photo;
import com.masson.alex.jsonplaceholder.repository.photo.IPhotoRepository;
import com.masson.alex.jsonplaceholder.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class PhotoListPresenter implements IPhotoRepository.IPhotoRepositoryListener, Parcelable {

    private IPhotoRepository repository;
    private View view;

    private List<Photo> photos;

    public PhotoListPresenter(View view, IPhotoRepository repository) {
        this.view = view;
        this.repository = repository;
        this.photos = new ArrayList<>();
    }

    protected PhotoListPresenter(Parcel in) {
        photos = in.createTypedArrayList(Photo.CREATOR);
    }

    public static final Creator<PhotoListPresenter> CREATOR = new Creator<PhotoListPresenter>() {
        @Override
        public PhotoListPresenter createFromParcel(Parcel in) {
            return new PhotoListPresenter(in);
        }

        @Override
        public PhotoListPresenter[] newArray(int size) {
            return new PhotoListPresenter[size];
        }
    };

    public void bind(View view, IPhotoRepository repository) {
        this.view = view;
        this.repository = repository;
        if (photos != null) {
            photosForAlbum(photos);
        }
    }

    @Override
    public void photosForAlbum(List<Photo> photos) {
        this.photos = photos;
        ArrayList<PhotoViewModel> res = new ArrayList<>();
        if (photos != null) {
            for (Photo u : photos
                    ) {
                res.add(new PhotoViewModel(u));
            }
        }
        view.photoListUpdated(res);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(photos);
    }

    public void getPhotoForAlbum(int id) {
        repository.getPhotoListForAlbum(id, this);
    }

    interface View {
        void displayErrorMessage(String message);

        void photoListUpdated(List<PhotoViewModel> photos);

        void onRefresh();
    }
}
