package navneet.com.openweather.addweather;


import navneet.com.openweather.BasePresenter;
import navneet.com.openweather.BaseView;

public class AddWeatherContract {
    interface View extends BaseView<Presenter> {
        void closeView();

        void displayErrorMessage(int stringRessource);

        void displayLoadingIndicator(Boolean isVisible);
    }

    interface Presenter extends BasePresenter {
        void addCity(String city);
    }
}
