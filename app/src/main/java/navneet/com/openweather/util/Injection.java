package navneet.com.openweather.util;

import android.content.Context;
import android.support.annotation.NonNull;


import navneet.com.openweather.api.NetworkService;
import navneet.com.openweather.data.DataServiceImpl;

import static com.google.common.base.Preconditions.checkNotNull;


public class Injection {
    public static NetworkService provideNetworkService(@NonNull Context context) {
        checkNotNull(context);
        return NetworkService.getInstance(context);
    }

    public static DataServiceImpl provideDataService(@NonNull Context context) {
        checkNotNull(context);
        return DataServiceImpl.getInstance(context);
    }
}
