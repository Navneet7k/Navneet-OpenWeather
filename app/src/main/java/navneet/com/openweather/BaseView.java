package navneet.com.openweather;


public interface BaseView<T> {

    void setPresenter(T presenter);

    boolean isActive();
}
