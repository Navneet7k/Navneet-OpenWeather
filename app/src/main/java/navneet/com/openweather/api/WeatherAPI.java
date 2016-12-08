package navneet.com.openweather.api;



import navneet.com.openweather.api.pojo.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherAPI {
    @GET("weather")
    Call<WeatherData> getWeatherCity(@Query("q") String city);
}

