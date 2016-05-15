package com.company.Controller;

import com.company.Models.GameObject;

/**
 * Created by Tran Tuan An on 5/11/2016.
 */
public interface Colliable {
    void onColliable(Colliable c);
    GameObject getGameObject();
}
