package navneet.com.openweather.weather;



import java.util.ArrayList;

import navneet.com.openweather.BasePresenter;
import navneet.com.openweather.BaseView;
import navneet.com.openweather.data.WeatherItem;


public class WeatherContract {
    interface View extends BaseView<Presenter> {
        void showAddWeatherItem();

        void showWeatherItems(ArrayList<WeatherItem> weatherItemsList);

        void addCityDialogDismiss();

        void displayLoadingIndicator(Boolean isVisible);

        void displayNoNetworkMessage();
    }

    interface Presenter extends BasePresenter {
        void loadWeatherItems();

        void addWeatherItem();

        void trashWeatherTouched(int position);

        void addCityDialogDismiss();

        void retryInternetAsked();

    }
}
