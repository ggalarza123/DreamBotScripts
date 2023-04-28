package osrsflipper;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ApiMarketDataResponse {
    @SerializedName("data")
    public HashMap<Integer, MarketData> data;
}
