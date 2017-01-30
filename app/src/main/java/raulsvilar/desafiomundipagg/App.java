package raulsvilar.desafiomundipagg;

import android.app.Application;

public class App extends Application {
    private static AppComponet componet;

    @Override
    public void onCreate() {
        super.onCreate();
        componet = DaggerAppComponet
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponet getComponent() {
        return componet;
    }
}
