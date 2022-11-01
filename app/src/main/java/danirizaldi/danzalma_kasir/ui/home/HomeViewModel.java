package danirizaldi.danzalma_kasir.ui.home;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseUser mUser;
    EditText text;

    public HomeViewModel() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}