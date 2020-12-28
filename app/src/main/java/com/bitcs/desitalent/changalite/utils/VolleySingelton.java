package com.bitcs.desitalent.changalite.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bitcs.desitalent.changalite.ChangaLiteView;

public class VolleySingelton {
    private static VolleySingelton instance = null;
    private RequestQueue requestQueue;

    private VolleySingelton() {
        requestQueue = Volley.newRequestQueue(ChangaLiteView.context);
    }

    public static VolleySingelton getInstance() {
        if (instance == null) {
            instance = new VolleySingelton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
