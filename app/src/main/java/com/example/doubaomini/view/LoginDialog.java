package com.example.doubaomini.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.doubaomini.R;
import com.example.doubaomini.network.request.UserLogin;
import com.example.doubaomini.network.request.UserRegister;
import com.example.doubaomini.viewmodel.BaseHomeVM;

public class LoginDialog extends Dialog {
    public LoginDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (!(context instanceof Activity)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else {
                getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.windowAnimations = R.style.common_dialog_anim;
        getWindow().setAttributes(layoutParams);
    }

    public static class Builder{

        private LoginDialog loginDialog;
        private ConstraintLayout mView;
        private Context mContext;
        private Boolean isLogin;
        private EditText editText_1;
        private EditText editText_2;
        private EditText editText_3;
        private Button cancelButton;
        private Button sendButton;
        private final UserLogin userLogin;
        private final UserRegister userRegister;

        public Builder(@NonNull Context context, Boolean isLogin) {
            this.mContext = context;
            this.isLogin = isLogin;
            userLogin = new UserLogin();
            userRegister = new UserRegister();
            init();
        }

        private void init(){
            mView = (ConstraintLayout) View.inflate(mContext, R.layout.login_dialog, null);
            editText_1 = mView.findViewById(R.id.user_name);
            if (isLogin){
                editText_1.setHint("输入用户名或邮箱");
            }else {
                editText_1.setHint("输入用户名");
            }
            editText_2 = mView.findViewById(R.id.email);
            if (!isLogin){
                editText_2.setHint("输入邮箱");
                editText_2.setVisibility(View.VISIBLE);
            }
            editText_3 = mView.findViewById(R.id.pass);
            cancelButton = mView.findViewById(R.id.cancel);
            cancelButton.setOnClickListener(v -> {
                View root = v.getRootView();
                Dialog dialog = (Dialog) root.getParent();
                if (dialog != null) dialog.dismiss();
            });
            sendButton = mView.findViewById(R.id.send);
            sendButton.setOnClickListener(v -> {
                if (isLogin){
                    userLogin.nameOrEmail = editText_1.getText().toString();
                    userLogin.pass = editText_3.getText().toString();
                    if (mContext instanceof ViewModelStoreOwner) {
                        BaseHomeVM baseHomeVM = new ViewModelProvider((ViewModelStoreOwner) mContext)
                                .get(BaseHomeVM.class);
                        baseHomeVM.login(userLogin);
                    }
                }else {
                    userRegister.username = editText_1.getText().toString();
                    userRegister.email = editText_2.getText().toString();
                    userRegister.pass = editText_3.getText().toString();
                    if (mContext instanceof ViewModelStoreOwner) {
                        BaseHomeVM baseHomeVM = new ViewModelProvider((ViewModelStoreOwner) mContext)
                                .get(BaseHomeVM.class);
                        baseHomeVM.register(userRegister);
                    }
                }
            });
        }

        public LoginDialog create() {
            loginDialog = new LoginDialog(mContext);
            loginDialog.setContentView(mView);
            return loginDialog;
        }

    }

}
