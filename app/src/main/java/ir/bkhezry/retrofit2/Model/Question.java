package ir.bkhezry.retrofit2.Model;

/**
 * Created by bkhezry on 2/19/2016.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Question {
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private Integer quotaRemaining;

    /**
     *
     * @return
     * The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     *
     * @return
     * The hasMore
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     *
     * @param hasMore
     * The has_more
     */
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     *
     * @return
     * The quotaMax
     */
    public Integer getQuotaMax() {
        return quotaMax;
    }

    /**
     *
     * @param quotaMax
     * The quota_max
     */
    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    /**
     *
     * @return
     * The quotaRemaining
     */
    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    /**
     *
     * @param quotaRemaining
     * The quota_remaining
     */
    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
