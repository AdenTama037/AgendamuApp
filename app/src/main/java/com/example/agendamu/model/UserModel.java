package com.example.agendamu.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private String uid, email, name;
    private int role;

    public UserModel(){}

    public UserModel(String uid, String email, String name, int role) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    protected UserModel(Parcel in) {
        uid = in.readString();
        email = in.readString();
        name = in.readString();
        role = in.readInt();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRoleText(){
        if (this.role == 0){
            return "Kepala Program Studi";
        } else {
            return "Koordinator";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeInt(role);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
