package gz.example.common.util;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import gz.example.common.entity.S2CJoke;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkUtil {
    private String Url;
    private volatile Map<String, String> param;
    private String getKey;
    private String getvalue;
    private boolean isPost = false;
    private GzCallBack callBack;
    private Class<?> aClass;

    public OkUtil setUrl(String url) {
        this.Url = url;
        return this;
    }

    public OkUtil setParam(String key, String value) {
        if (param == null) {
            param = new HashMap<>();
        }
        param.put(key, value);
        return this;
    }

    public OkUtil setGetkey(String key) {
        this.getKey = key;
        return this;
    }

    public OkUtil setGetvalue(String value) {
        this.getvalue = value;
        return this;
    }


    public OkUtil setCallBack(GzCallBack callBack) {
        this.callBack = callBack;
//        this.aClass = getInterfaceT(callBack);
        return this;
    }

    public static Type getInterfaceT(Object o) {
        Type[] types = o.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        return type;

    }

    private static Class<?> checkType(Type type, int index) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            Type t = pt.getActualTypeArguments()[index];
            return checkType(t, index);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType"
                    + ", but <" + type + "> is of type " + className);
        }
    }

    public OkUtil isPost() {
        this.isPost = true;
        return this;
    }

    private static final OkHttpClient mClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    public void build() {
        RequestBody body = null;
        Request request = null;
        if (isPost) {
            FormBody.Builder builder = new FormBody.Builder();
            Set<Map.Entry<String, String>> entrySet = param.entrySet();
            Iterator<Map.Entry<String, String>> iter = entrySet.iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                builder.add(entry.getKey(), entry.getValue());
//                    System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
            body = builder.build();
        } else {
            Url = new StringBuilder(Url).append("?").append(getKey).append("=").append(getvalue).toString();
        }

        Request.Builder builder = new Request.Builder().url(Url);
        if (body != null) {
            builder.post(body);
        }


        mClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String data = response.body().string();
                    Type type = getInterfaceT(callBack);
                    S2CJoke s2CJoke = new Gson().fromJson(data, type);
                    callBack.success(s2CJoke);
                    MMKVUtil.getInstance().saveJsonData("json", data);
                } catch (Exception e) {
                    //fail();
                }
            }
        });
    }


    public interface GzCallBack<T> {
        void success(T t);
    }
}
