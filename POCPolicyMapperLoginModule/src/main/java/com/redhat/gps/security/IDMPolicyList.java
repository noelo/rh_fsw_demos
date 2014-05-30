package com.redhat.gps.security;

import java.util.Set;

/**
 * Created by admin on 29/05/2014.
 */
public class IDMPolicyList {

    public enum IDM_ROLE_PREFIX {
        MANAGER_OF_,
        MEMBER_OF,
        OWNER_OF
    }

    private String param1;
    private String param2;
    private String param3;


    public IDMPolicyList(String p1, String p2, String p3){
        this.param1 = p1;
        this.param2 = p2;
        this.param3 = p3;
    }


    public String getParam3() {
        return param3;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String toString(){
        return param1 + ":" + param2 + ":"+param3;
    }
}
