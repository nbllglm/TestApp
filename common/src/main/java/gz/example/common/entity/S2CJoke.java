package gz.example.common.entity;

import java.io.Serializable;
import java.util.List;

public class S2CJoke implements Serializable {
    private int error_code;
    private String reason;
    private List<Joke> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Joke> getResult() {
        return result;
    }

    public void setResult(List<Joke> result) {
        this.result = result;
    }
}
