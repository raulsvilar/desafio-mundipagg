package raulsvilar.desafiomundipagg;

import dagger.Component;
import raulsvilar.desafiomundipagg.views.adapters.MerchantAdapter;
import raulsvilar.desafiomundipagg.views.fragments.ListMerchantsFragment;
import raulsvilar.desafiomundipagg.viewmodels.MerchantViewModel;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;

/**
 * Created by raulsvilar on 23/01/17.
 */

@Component(modules = {AppModule.class})
public interface AppComponet {
    public void inject(UserViewModel userViewModel);
    public void inject(MerchantViewModel merchantViewModel);
    public void inject(MerchantAdapter merchantAdapter);
    public void inject(ListMerchantsFragment listMerchantsFragment);
}
