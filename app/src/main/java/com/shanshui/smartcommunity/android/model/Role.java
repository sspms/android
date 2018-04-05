package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

/**
 * Part of the server-side Role information, all information should be public.
 */

public class Role {
    public Role(){}
    @ColumnInfo(name = "role_name")
    private String roleName;
    @Ignore
    private boolean valid;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
