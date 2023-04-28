package osrsflipper;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ApiPriceDataResponse {
    @SerializedName("data")
    public HashMap<Integer, PriceData> data;
}

