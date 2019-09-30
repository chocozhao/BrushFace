package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetShopAroundResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/29 11:44
 * Modified By：
 * Fixtime：2019/7/29 11:44
 * FixDescription：
 **/

public class GetShopAroundResult extends BaseModel {


    /**
     * code : 200
     * data : [{"address":"广东省广州市番禺区兴南大道1号","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"17","detail_url":"http://api.map.baidu.com/place/detail?uid=2ad32cc3ac73c0969d95de1d&output=html&source=placeapi_v2","distance":1362,"navi_location":{"lat":23.018960290094,"lng":113.34586368765},"overall_rating":"3.5","price":"307.5","tag":"酒店;其他","type":"hotel"},"flag":false,"location":{"lat":23.019194,"lng":113.345854},"name":"永成大酒店","province":"广东省","street_id":"2ad32cc3ac73c0969d95de1d","telephone":"(020)39955888","uid":"2ad32cc3ac73c0969d95de1d"},{"address":"广州市番禺区南村镇塘东中路中华美食城二期B区第一栋","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"29","detail_url":"http://api.map.baidu.com/place/detail?uid=4fb6e4771e2d131b593ac810&output=html&source=placeapi_v2","distance":831,"navi_location":{"lat":23.017714562504,"lng":113.35104582619},"overall_rating":"4.5","price":"300.0","tag":"酒店;快捷酒店","type":"hotel"},"flag":false,"location":{"lat":23.018205,"lng":113.350947},"name":"丽枫酒店(广州汉溪长隆站万达广场店)","province":"广东省","street_id":"4fb6e4771e2d131b593ac810","telephone":"(020)39958808","uid":"4fb6e4771e2d131b593ac810"},{"address":"广东省广州市番禺区南村镇万达广场B2栋1705室","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"19","detail_url":"http://api.map.baidu.com/place/detail?uid=8d99c12b963b9d741df74d46&output=html&source=placeapi_v2","distance":350,"overall_rating":"4.9","price":"600.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.014113,"lng":113.355954},"name":"广州微香屋亲子主题公寓(番禺长隆万达广场店)","province":"广东省","street_id":"8d99c12b963b9d741df74d46","telephone":"18027221878","uid":"8d99c12b963b9d741df74d46"},{"address":"广东省广州市番禺区汉溪大道东393号万达广场B3栋28楼04房","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"14","detail_url":"http://api.map.baidu.com/place/detail?uid=a830e1d5181b0beb4f6d46d6&output=html&source=placeapi_v2","distance":552,"navi_location":{"lat":23.0136734213,"lng":113.35717909689},"overall_rating":"4.6","price":"144.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.011886,"lng":113.355658},"name":"乐途酒店公寓(广州长隆番禹万达店)","province":"广东省","street_id":"a830e1d5181b0beb4f6d46d6","telephone":"(020)39980026","uid":"a830e1d5181b0beb4f6d46d6"},{"address":"广东省广州市番禺区汉溪大道东387号万达广场B4栋2805室","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"17","detail_url":"http://api.map.baidu.com/place/detail?uid=7e40147e4f3d1bb86d20b944&output=html&source=placeapi_v2","distance":414,"overall_rating":"4.6","price":"126.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.012852,"lng":113.356517},"name":"快乐宝贝主题度假公寓(广州万达店)","province":"广东省","street_id":"7e40147e4f3d1bb86d20b944","telephone":"13392484878","uid":"7e40147e4f3d1bb86d20b944"},{"address":"广东省广州市番禺区汉溪大道东383号番禺万达广场B3栋1519室","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"54","detail_url":"http://api.map.baidu.com/place/detail?uid=336a1f81e2071ed8740269b6&output=html&source=placeapi_v2","distance":549,"navi_location":{"lat":23.01363376155,"lng":113.3572150327},"overall_rating":"4.6","price":"83.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.011976,"lng":113.355573},"name":"广州长隆钧玺主题式酒店公寓(万达广场店)","province":"广东省","street_id":"336a1f81e2071ed8740269b6","telephone":"(020)39191088","uid":"336a1f81e2071ed8740269b6"},{"address":"广东省广州市番禺区南村镇兴南大道368号番禺万达广场10楼2009房","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"25","detail_url":"http://api.map.baidu.com/place/detail?uid=bb448cad0352be58f4eafc7b&output=html&source=placeapi_v2","distance":393,"navi_location":{"lat":23.013694392603,"lng":113.35717610014},"overall_rating":"4.4","price":"284.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.012777,"lng":113.357086},"name":"广州小时代酒店公寓","province":"广东省","street_id":"bb448cad0352be58f4eafc7b","telephone":"18565202178","uid":"bb448cad0352be58f4eafc7b"},{"address":"广东省广州市番禺区汉溪大道368号番禺万达广场  B-4座36-37楼","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"11","detail_url":"http://api.map.baidu.com/place/detail?uid=7d292fe04002d4db853304aa&output=html&source=placeapi_v2","distance":410,"navi_location":{"lat":23.012453457928,"lng":113.3568568747},"overall_rating":"4.8","price":"206.0","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.012849,"lng":113.356587},"name":"广州北海稻主题公寓(番禺万达广场店)","province":"广东省","street_id":"7d292fe04002d4db853304aa","telephone":"(020)39956222","uid":"7d292fe04002d4db853304aa"},{"address":"广东省广州市番禺区汉溪大道东381号番禺万达广场金街006号","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"0","detail_url":"http://api.map.baidu.com/place/detail?uid=fdb0492aa5181baf128824c8&output=html&source=placeapi_v2","distance":397,"overall_rating":"4.5","tag":"酒店;公寓式酒店","type":"hotel"},"flag":false,"location":{"lat":23.012938,"lng":113.356672},"name":"广州长隆星伦国际主题式度假公寓(番禺万达广场店)","province":"广东省","street_id":"90a576f00fa1245fc03644cf","uid":"fdb0492aa5181baf128824c8"},{"address":"广东省广州市番禺区番禺大道北342号","area":"番禺区","city":"广州市","detail":1,"detail_info":{"children":[],"comment_num":"21","detail_url":"http://api.map.baidu.com/place/detail?uid=c734e0d4ec05ad89223bf20f&output=html&source=placeapi_v2","distance":1584,"navi_location":{"lat":23.002264247252,"lng":113.35480868065},"overall_rating":"4.3","price":"272.0","tag":"酒店;其他","type":"hotel"},"flag":false,"location":{"lat":23.0022,"lng":113.354798},"name":"茂丰酒店","province":"广东省","street_id":"c734e0d4ec05ad89223bf20f","telephone":"(020)39952066","uid":"c734e0d4ec05ad89223bf20f"}]
     * message : 获取成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 广东省广州市番禺区兴南大道1号
         * area : 番禺区
         * city : 广州市
         * detail : 1
         * detail_info : {"children":[],"comment_num":"17","detail_url":"http://api.map.baidu.com/place/detail?uid=2ad32cc3ac73c0969d95de1d&output=html&source=placeapi_v2","distance":1362,"navi_location":{"lat":23.018960290094,"lng":113.34586368765},"overall_rating":"3.5","price":"307.5","tag":"酒店;其他","type":"hotel"}
         * flag : false
         * location : {"lat":23.019194,"lng":113.345854}
         * name : 永成大酒店
         * province : 广东省
         * street_id : 2ad32cc3ac73c0969d95de1d
         * telephone : (020)39955888
         * uid : 2ad32cc3ac73c0969d95de1d
         */

        private String address;
        private String area;
        private String city;
        private int detail;
        private DetailInfoBean detail_info;
        private boolean flag;
        private LocationBean location;
        private String name;
        private String province;
        private String street_id;
        private String telephone;
        private String uid;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getDetail() {
            return detail;
        }

        public void setDetail(int detail) {
            this.detail = detail;
        }

        public DetailInfoBean getDetail_info() {
            return detail_info;
        }

        public void setDetail_info(DetailInfoBean detail_info) {
            this.detail_info = detail_info;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getStreet_id() {
            return street_id;
        }

        public void setStreet_id(String street_id) {
            this.street_id = street_id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public static class DetailInfoBean {
            /**
             * children : []
             * comment_num : 17
             * detail_url : http://api.map.baidu.com/place/detail?uid=2ad32cc3ac73c0969d95de1d&output=html&source=placeapi_v2
             * distance : 1362
             * navi_location : {"lat":23.018960290094,"lng":113.34586368765}
             * overall_rating : 3.5
             * price : 307.5
             * tag : 酒店;其他
             * type : hotel
             */

            private String comment_num;
            private String detail_url;
            private int distance;
            private NaviLocationBean navi_location;
            private String overall_rating;
            private String price;
            private String tag;
            private String type;
            private List<?> children;

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getDetail_url() {
                return detail_url;
            }

            public void setDetail_url(String detail_url) {
                this.detail_url = detail_url;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public NaviLocationBean getNavi_location() {
                return navi_location;
            }

            public void setNavi_location(NaviLocationBean navi_location) {
                this.navi_location = navi_location;
            }

            public String getOverall_rating() {
                return overall_rating;
            }

            public void setOverall_rating(String overall_rating) {
                this.overall_rating = overall_rating;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }

            public static class NaviLocationBean {
                /**
                 * lat : 23.018960290094
                 * lng : 113.34586368765
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class LocationBean {
            /**
             * lat : 23.019194
             * lng : 113.345854
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
