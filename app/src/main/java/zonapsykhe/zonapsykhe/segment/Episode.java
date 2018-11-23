
package zonapsykhe.zonapsykhe.segment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import zonapsykhe.zonapsykhe.segment.Segment;

public class Episode {

    @SerializedName("episode_id")
    @Expose
    private Integer episodeId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("explicit")
    @Expose
    private Boolean explicit;
    @SerializedName("show_id")
    @Expose
    private Integer showId;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("site_url")
    @Expose
    private String siteUrl;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("image_original_url")
    @Expose
    private String imageOriginalUrl;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("download_enabled")
    @Expose
    private Boolean downloadEnabled;
    @SerializedName("waveform_url")
    @Expose
    private String waveformUrl;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("plays_count")
    @Expose
    private Integer playsCount;
    @SerializedName("downloads_count")
    @Expose
    private Integer downloadsCount;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("messages_count")
    @Expose
    private Integer messagesCount;
    @SerializedName("chapters_count")
    @Expose
    private Integer chaptersCount;

    @SerializedName("segments")
    @Expose
    private List<Segment> segments = null;

    public Integer getEpisodeId() {
        return episodeId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public Integer getShowId() {
        return showId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageOriginalUrl() {
        return imageOriginalUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Boolean getDownloadEnabled() {
        return downloadEnabled;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public Object getDescription() {
        return description;
    }

    public String getPermalink() {
        return permalink;
    }

    public Integer getPlaysCount() {
        return playsCount;
    }

    public Integer getDownloadsCount() {
        return downloadsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public Integer getMessagesCount() {
        return messagesCount;
    }

    public Integer getChaptersCount() {
        return chaptersCount;
    }

    public List<Segment> getSegments() {
        return segments;
    }

}
