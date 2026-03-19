
    //get请求
    public void sendGetHttpServer(String url, final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface) {
        sendGetHttpServer(url,netWorkCallbackInterface,null);
    }
    //get请求
    public void sendGetHttpServer(String url,
                                  final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface,
                                  final HttpServerUtil.NetWorkCallbackInterface failNetWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);
        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            cancelProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                   // showAlertView(result, 0);
                    if(failNetWorkCallbackInterface != null){
                        failNetWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface codeCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    operateCodeFail(result);
                    if(failNetWorkCallbackInterface != null){
                        failNetWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };
        HttpServerUtil.getInstance().sendDataGetService(url, dataCallBack, failCallBack, codeCallBack);
    }

    //post的json请求
    public void sendJsonPostServer(String url, String json, final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface){
        sendJsonPostServer(url,json,netWorkCallbackInterface,null);
    }

    //post的json请求
    public void sendJsonPostServer(String url, String json,
                                   final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface,
                                   final HttpServerUtil.NetWorkCallbackInterface failNetWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);
        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            cancelProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                   // showAlertView(result, 0);
                    if(failNetWorkCallbackInterface != null){
                        failNetWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface codeCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    operateCodeFail(result);
                    if (url.equals(HttpServerUtil.getTaskDetail)) {
                        finish();
                    }
                    if(failNetWorkCallbackInterface != null){
                        failNetWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.getInstance().sendJsonPostServer(url, json, dataCallBack, failCallBack, codeCallBack);
    }

    //delete请求
    public void sendDataDelServer(String url, final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);
        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            cancelProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };
        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                   // showAlertView(result, 0);
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface codeCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    operateCodeFail(result);
                }
            });
        };
        HttpServerUtil.getInstance().sendDataDelServer(url, dataCallBack, failCallBack, codeCallBack);
    }
    public void sendPostDownServer(String url, String json,  String fileName,final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);
        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            cancelProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };
        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    // showAlertView(result, 0);
                }
            });
        };
        HttpServerUtil.getInstance().sendPostDownServer(url,json, fileName,dataCallBack, failCallBack);
    }

    //上传图片
    public void uploadImageFile(String url, String json, final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);

        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cancelProgressDialog();
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                  //  showAlertView(result, 0);
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface codeCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    operateCodeFail(result);
                }
            });
        };

        try {
            JSONObject jsonObject = new JSONObject(json);
            String uploadUrl = jsonObject.getString("uploadUrl");
            String token = jsonObject.getString("token");
            String expireTime = jsonObject.getString("expireTime");
            String path = jsonObject.getString("path");
            String fileName = jsonObject.getString("fileName");
            String key = path + "/" + fileName + expireTime + ".jpg";
            HttpServerUtil.getInstance().uploadImageFile(dataCallBack, failCallBack, codeCallBack, uploadUrl, key, token, new File(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public void uploadFile(String url, String json, final HttpServerUtil.NetWorkCallbackInterface netWorkCallbackInterface) {
        String currentDate = "start:" + TimeFormatUtils.getCurrentDate(2);

        HttpServerUtil.NetWorkCallbackInterface dataCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cancelProgressDialog();
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    if (netWorkCallbackInterface != null) {
                        netWorkCallbackInterface.showCallback(result);
                    }
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface failCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                   // showAlertView(result, 0);
                }
            });
        };

        HttpServerUtil.NetWorkCallbackInterface codeCallBack = result -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DebugModel.getInstance().addTextInMap(url + "(" + json + ")" + "--" + currentDate + "--" + result);
                    cancelProgressDialog();
                    operateCodeFail(result);
                }
            });
        };

        try {
            JSONObject jsonObject = new JSONObject(json);
            String uploadUrl = jsonObject.getString("uploadUrl");
            String token = jsonObject.getString("token");
            String expireTime = jsonObject.getString("expireTime");
            String path = jsonObject.getString("path");
            String fileName = jsonObject.getString("fileName");
            String ext = FileNameUtil.extName(url);
            String key = path + "/" + fileName + expireTime + "." + ext;
            HttpServerUtil.getInstance().uploadFile(dataCallBack, failCallBack, codeCallBack, uploadUrl, key, token, new File(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
