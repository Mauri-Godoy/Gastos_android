import com.mg.gastos.R;
import com.mg.gastos.gui.WelcomeActivity;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CaocConfig.Builder.create()
                .errorActivity(WelcomeActivity.class) //default: null (default error activity)
                .apply();

        //If you use Firebase Crashlytics or ACRA, please initialize them here as explained above.
    }
}
