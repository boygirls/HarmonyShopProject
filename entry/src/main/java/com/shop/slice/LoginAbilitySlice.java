package com.shop.slice;

import com.google.gson.Gson;
import com.shop.ResourceTable;
import com.shop.home.model.ResultVO;
import com.shop.util.DataBaseUtil;
import com.shop.util.HttpRequestUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class LoginAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_login);

        TextField tf1 = (TextField) findComponentById(ResourceTable.Id_login_username);
        TextField tf2 = (TextField) findComponentById(ResourceTable.Id_login_pwd);
        Button submitBtn = (Button) findComponentById(ResourceTable.Id_login_btn);
        submitBtn.setClickedListener(component -> {
            TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            globalTaskDispatcher.asyncDispatch(()->{
                //点击登录按钮，将账号和密码提交到用户认证接口
                String urlString = "http://47.97.11.185:8080//user/login?username="+tf1.getText()+"&password="+tf2.getText();
                String s = HttpRequestUtil.sendGetRequest(this, urlString);
                ResultVO resultVO = new Gson().fromJson(s, ResultVO.class);
                System.out.println("~~~~~~~~~~~~~~~");
                System.out.println(resultVO);
                if(resultVO.getCode() == 10000){
                    //
                    //1.将token存入到数据库mall_info
                    int i = DataBaseUtil.setValue("token", resultVO.getMsg(), this);
                    //2.跳转到TabListSlice
                    present(new TabListSlice(),new Intent());
                }else {
                    new ToastDialog(this).setText("账号或密码错误").show();
                }
            });
        });

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
