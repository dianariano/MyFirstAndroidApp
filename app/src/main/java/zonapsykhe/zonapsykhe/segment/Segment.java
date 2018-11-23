
package zonapsykhe.zonapsykhe.segment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("duration_reliable")
    @Expose
    private Boolean durationReliable;
    @SerializedName("http_url")
    @Expose
    private String httpUrl;
    @SerializedName("http_url_expires_at")
    @Expose
    private String httpUrlExpiresAt;

    public String getType() {
        return type;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getDurationReliable() {
        return durationReliable;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public String getHttpUrlExpiresAt() {
        return httpUrlExpiresAt;
    }

}
