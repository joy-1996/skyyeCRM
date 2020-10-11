package com.whvcse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePerm {
    public RolePerm(int r_id, int p_id) {
        this.r_id = r_id;
        this.p_id = p_id;
    }

    private int rpid;
    private int r_id;
    private int p_id;
    private Permission permission;
    private Role role;
    private HashMap<String,Integer> map;
}
