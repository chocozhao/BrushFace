package com.yzf.king.model;

import java.util.List;

/**
 * ClaseName：ServiceBean
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/1 16:28
 * Modified By：
 * Fixtime：2019/4/1 16:28
 * FixDescription：
 */
public class ServiceBean {
    private List<Bean> serviceList;

    public static class Bean {
        /**
         * id : 08
         * name : vip购买
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
