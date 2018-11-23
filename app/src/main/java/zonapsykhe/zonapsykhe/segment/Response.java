
package zonapsykhe.zonapsykhe.segment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("episode")
    @Expose
    private Episode episode;

    public Episode getEpisode() {
        return episode;
    }

}
