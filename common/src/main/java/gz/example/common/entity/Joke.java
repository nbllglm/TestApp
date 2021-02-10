package gz.example.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.Bindable;

import gz.example.common.BR;

public class Joke extends BaseBean implements Parcelable {
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String content;

    transient private String hashId;
    transient private long unixtime;

    public Joke() {
    }


    public Joke(String content) {
        this.content = content;
    }

    protected Joke(Parcel in) {
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Joke> CREATOR = new Creator<Joke>() {
        @Override
        public Joke createFromParcel(Parcel in) {
            return new Joke(in);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
//        notifyChange();
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(long unixtime) {
        this.unixtime = unixtime;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "content='" + content + '\'' +
                ", hashId='" + hashId + '\'' +
                ", unixtime=" + unixtime +
                '}';
    }
}
