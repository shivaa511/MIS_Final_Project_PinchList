package com.example.misprojectpinchlist.viewholder;

import android.util.SparseArray;


public class AdapterItemUtil {
    private SparseArray<Object> typeSArr = new SparseArray<>();


    public int getIntType(Object type) {
        int index = typeSArr.indexOfValue(type);
        if (index == -1) {
            index = typeSArr.size();

            typeSArr.put(index, type);
        }
        return index;
    }

}

