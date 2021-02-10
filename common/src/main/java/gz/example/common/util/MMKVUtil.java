package gz.example.common.util;

import com.tencent.mmkv.MMKV;

import org.apache.commons.lang3.StringEscapeUtils;

import gz.example.common.CommonApplication;

public class MMKVUtil {
    private MMKV mmkv;

    private MMKVUtil() {
        MMKV.initialize(CommonApplication.context);
        mmkv = MMKV.defaultMMKV();
    }

    private static class MMKVUtilHoler {
        private static final MMKVUtil INSTANCE = new MMKVUtil();
    }

    public static MMKVUtil getInstance() {
        return MMKVUtilHoler.INSTANCE;
    }

    public void saveJsonData(String key, String value) {
        String data = StringEscapeUtils.escapeJson(value);
        this.mmkv.encode(key, data);
    }

    public String getJsonData(String key) {
        String data = this.mmkv.decodeString(key);
        return StringEscapeUtils.unescapeJava(data);
    }

}
