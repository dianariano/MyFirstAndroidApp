
package zonapsykhe.zonapsykhe.episodes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyDataEpisodes {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

}
