package raulsvilar.desafiomundipagg;

import dagger.Component;
import raulsvilar.desafiomundipagg.data.models.Transaction;
import raulsvilar.desafiomundipagg.viewmodels.TransactionViewModel;
import raulsvilar.desafiomundipagg.views.activities.MainActivity;
import raulsvilar.desafiomundipagg.views.adapters.MerchantAdapter;
import raulsvilar.desafiomundipagg.views.fragments.ListMerchantsFragment;
import raulsvilar.desafiomundipagg.viewmodels.MerchantViewModel;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;
import raulsvilar.desafiomundipagg.views.fragments.SignInFragment;

@Component(modules = {AppModule.class})
public interface AppComponet {
    void inject(UserViewModel userViewModel);
    void inject(MerchantViewModel merchantViewModel);
    void inject(MerchantAdapter merchantAdapter);
    void inject(TransactionViewModel transactionViewModel);
    void inject(ListMerchantsFragment listMerchantsFragment);
    void inject(Transaction transaction);
    void inject(SignInFragment signInFragment);
    void inject(MainActivity mainActivity);
}
