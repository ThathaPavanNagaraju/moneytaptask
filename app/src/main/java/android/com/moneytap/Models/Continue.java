/**
 * Created by Pavan Nagaraju on 01-Sep-18.
 */

package android.com.moneytap.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Continue {

    @SerializedName("gpsoffset")
    @Expose
    private Integer gpsoffset;
    @SerializedName("continue")
    @Expose
    private String _continue;

    public Integer getGpsoffset() {
        return gpsoffset;
    }

    public void setGpsoffset(Integer gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public String getContinue() {
        return _continue;
    }

    public void setContinue(String _continue) {
        this._continue = _continue;
    }

}