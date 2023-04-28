package osrsflipper;

import com.google.gson.annotations.SerializedName;

public class MarketData {
    @SerializedName("highPriceVolume")
    public int highPriceVolume;
    @SerializedName("lowPriceVolume")
    public int lowPriceVolume;
}
