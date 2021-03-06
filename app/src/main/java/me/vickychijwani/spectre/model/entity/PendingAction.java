package me.vickychijwani.spectre.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class PendingAction implements RealmModel, Parcelable {

    public static final String CREATE = "pendingaction:create";
    public static final String EDIT = "pendingaction:edit";
    // for published posts that are saved automatically
    public static final String EDIT_LOCAL = "pendingaction:edit_local";

    @StringDef({CREATE, EDIT, EDIT_LOCAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}

    @Type @Required
    private String type;

    @SuppressWarnings("unused")
    public PendingAction() {}

    public PendingAction(@Type String type) {
        this.type = type;
    }


    // parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
    }

    protected PendingAction(Parcel in) {
        //noinspection WrongConstant
        this.type = in.readString();
    }

    public static final Parcelable.Creator<PendingAction> CREATOR = new Parcelable.Creator<PendingAction>() {
        @Override
        public PendingAction createFromParcel(Parcel source) {
            return new PendingAction(source);
        }

        @Override
        public PendingAction[] newArray(int size) {
            return new PendingAction[size];
        }
    };


    // accessors
    @Type
    public String getType() {
        return type;
    }

    public void setType(@Type String type) {
        this.type = type;
    }

}
