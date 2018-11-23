package zonapsykhe.zonapsykhe.segment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EpisodeSegment {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }
}
