package com.shop.slice;

import com.shop.ResourceTable;
import com.shop.user.model.User;
import com.shop.util.DataBaseUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

public class LoginAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_login);


    }

    @Override
    public void onActive() {
        super.onActive();

        if(DataBaseUtil.getUser1("userId",this) == 0 ){
            DataBaseUtil.setUser("1","wy","123456","123",this);
        }

        TextField tf1 = (TextField) findComponentById(ResourceTable.Id_login_username);
        TextField tf2 = (TextField) findComponentById(ResourceTable.Id_login_pwd);
        Button submitBtn = (Button) findComponentById(ResourceTable.Id_login_btn);
        submitBtn.setClickedListener(component -> {


            User user1 = new User();
            user1.setUsername("wy");
            user1.setPassword("123456");
            //String username = DataBaseUtil.getUser("1",this);
            User user = DataBaseUtil.getUser(user1,this);


            if(tf1.getText().equals( user.getUsername()) && tf2.getText().equals(user.getPassword())){


                present(new TabListSlice(),new Intent());
            } else {
                    new ToastDialog(this).setText("账号或密码错误").show();
                }
            });

//            TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            //TODO 本地数据库访问。
//            globalTaskDispatcher.asyncDispatch(()->{
//                //点击登录按钮，将账号和密码提交到用户认证接口
//                String urlString = "dataability:///com.shop.MallDataAbility/user?username="+tf1.getText()+"&password="+tf2.getText();
//                String s = HttpRequestUtil.sendGetRequest(this, urlString);
//                ResultVO resultVO = new Gson().fromJson(s, ResultVO.class);
//                System.out.println("~~~~~~~~~~~~~~~");
//                System.out.println(resultVO);
//                if(resultVO.getCode() == 10000){
//                    //
//                    //1.将token存入到数据库mall_info
//                    int i = DataBaseUtil.setValue("token", resultVO.getMsg(), this);
//                    //将用户id和用户名也存入到数据库
//                    Map user = (Map) resultVO.getData();
//                    Double id = (Double) user.get("userId");
//                    double did = id;
//                    int userId = (int) did;


                    //2.跳转到TabListSlice
//                    present(new TabListSlice(),new Intent());
//                }else {
//                    new ToastDialog(this).setText("账号或密码错误").show();
//                }
//            });
//        });

    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
