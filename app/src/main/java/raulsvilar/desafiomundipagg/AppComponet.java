package raulsvilar.desafiomundipagg;

import dagger.Component;
import raulsvilar.desafiomundipagg.viewmodel.UserViewModel;

/**
 * Created by raulsvilar on 23/01/17.
 */

@Component(modules = {AppModule.class})
public interface AppComponet {
    public void inject(UserViewModel userViewModel);
}
