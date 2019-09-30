package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：OpenTransResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/25 19:48
 * Modified By：
 * Fixtime：2019/2/25 19:48
 * FixDescription：
 */
public class OpenTransResult extends BaseModel {

    /**
     * code : 200
     * data : {"payInfo":"PGZvcm0gbmFtZT0icHVuY2hvdXRfZm9ybSIgbWV0aG9kPSJwb3N0IiBhY3Rpb249Imh0dHBzOi8vb3BlbmFwaS5hbGlwYXkuY29tL2dhdGV3YXkuZG8/Y2hhcnNldD11dGYtOCZtZXRob2Q9YWxpcGF5LnRyYWRlLndhcC5wYXkmc2lnbj1VZ0FkJTJGSTNyYWdLSHIxWlNWUnZiMHdTNVNRbkNKdzIlMkJnN3haaVQ2dmlzOG1zdkpCWiUyQnI4aDl1TU8xMU5pM1NqamJQaUNJcktKSHljc28lMkZicTllOHVaREtEeVJOYWdLUWNMQXNHRWlRekhHcERwR0ZsT1d3V3pGNVRSaWRIJTJGOEIlMkJsYW4lMkZBUkgxZDJ3Uk8ySW9LdU5zenVCQ1BuclZMV2RwNWNPMjdhQnFOQ2tBcU9RMXlyNmx6TnlVR0c5NnBBQXFxaU85UGZNNVk0cWV0YWVIdnlsemRiRnNnQ25vSW8lMkZ5MFRFc0pTdko5ckM0S09ZbEJwS3ZMJTJCaEMyN0pncE5seGZqYW03bEglMkJYZ0tDMDVRYmdVb0p1T1pxcE1NbTEyN0tiTzZ5aW5RMnhhNDk0VjVzViUyQjVVcFN2dG52UzFoeEtVU2k4d0ZkbVdIdlByYk0wQiUyRnZ6MHclM0QlM0QmcmV0dXJuX3VybD13d3cuYmFpZHUuY29tJm5vdGlmeV91cmw9aHR0cCUzQSUyRiUyRm9lbS55aXlvdXBheS5uZXQlMkZhbGlwYXklMkZub3RpZnkmdmVyc2lvbj0xLjAmYXBwX2lkPTIwMTgwNTAzMDI2MjgzODEmc2lnbl90eXBlPVJTQTImdGltZXN0YW1wPTIwMTktMDItMjUrMTklM0E0NyUzQTEwJmFsaXBheV9zZGs9YWxpcGF5LXNkay1qYXZhLTMuNC4yNy5BTEwmZm9ybWF0PWpzb24iPgo8aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJiaXpfY29udGVudCIgdmFsdWU9InsgJnF1b3Q7b3V0X3RyYWRlX25vJnF1b3Q7OiZxdW90O0FuZHJvaWQyMDE5MDIyNTE5NDcxMDk1NDkmcXVvdDssICZxdW90O3RvdGFsX2Ftb3VudCZxdW90OzoxNi4wLCAmcXVvdDtzdWJqZWN0JnF1b3Q7OiZxdW90O2NwczIwMTkwMjI1NTczODIyMCZxdW90OywgJnF1b3Q7cHJvZHVjdF9jb2RlJnF1b3Q7OiZxdW90O1FVSUNLX1dBUF9QQVkmcXVvdDsgfSI+CjxpbnB1dCB0eXBlPSJzdWJtaXQiIHZhbHVlPSLnq4vljbPmlK/ku5giIHN0eWxlPSJkaXNwbGF5Om5vbmUiID4KPC9mb3JtPgo8c2NyaXB0PmRvY3VtZW50LmZvcm1zWzBdLnN1Ym1pdCgpOzwvc2NyaXB0Pg==","respCode":"0100","respDesc":"等待支付"}
     * message : 等待支付
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * payInfo : PGZvcm0gbmFtZT0icHVuY2hvdXRfZm9ybSIgbWV0aG9kPSJwb3N0IiBhY3Rpb249Imh0dHBzOi8vb3BlbmFwaS5hbGlwYXkuY29tL2dhdGV3YXkuZG8/Y2hhcnNldD11dGYtOCZtZXRob2Q9YWxpcGF5LnRyYWRlLndhcC5wYXkmc2lnbj1VZ0FkJTJGSTNyYWdLSHIxWlNWUnZiMHdTNVNRbkNKdzIlMkJnN3haaVQ2dmlzOG1zdkpCWiUyQnI4aDl1TU8xMU5pM1NqamJQaUNJcktKSHljc28lMkZicTllOHVaREtEeVJOYWdLUWNMQXNHRWlRekhHcERwR0ZsT1d3V3pGNVRSaWRIJTJGOEIlMkJsYW4lMkZBUkgxZDJ3Uk8ySW9LdU5zenVCQ1BuclZMV2RwNWNPMjdhQnFOQ2tBcU9RMXlyNmx6TnlVR0c5NnBBQXFxaU85UGZNNVk0cWV0YWVIdnlsemRiRnNnQ25vSW8lMkZ5MFRFc0pTdko5ckM0S09ZbEJwS3ZMJTJCaEMyN0pncE5seGZqYW03bEglMkJYZ0tDMDVRYmdVb0p1T1pxcE1NbTEyN0tiTzZ5aW5RMnhhNDk0VjVzViUyQjVVcFN2dG52UzFoeEtVU2k4d0ZkbVdIdlByYk0wQiUyRnZ6MHclM0QlM0QmcmV0dXJuX3VybD13d3cuYmFpZHUuY29tJm5vdGlmeV91cmw9aHR0cCUzQSUyRiUyRm9lbS55aXlvdXBheS5uZXQlMkZhbGlwYXklMkZub3RpZnkmdmVyc2lvbj0xLjAmYXBwX2lkPTIwMTgwNTAzMDI2MjgzODEmc2lnbl90eXBlPVJTQTImdGltZXN0YW1wPTIwMTktMDItMjUrMTklM0E0NyUzQTEwJmFsaXBheV9zZGs9YWxpcGF5LXNkay1qYXZhLTMuNC4yNy5BTEwmZm9ybWF0PWpzb24iPgo8aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJiaXpfY29udGVudCIgdmFsdWU9InsgJnF1b3Q7b3V0X3RyYWRlX25vJnF1b3Q7OiZxdW90O0FuZHJvaWQyMDE5MDIyNTE5NDcxMDk1NDkmcXVvdDssICZxdW90O3RvdGFsX2Ftb3VudCZxdW90OzoxNi4wLCAmcXVvdDtzdWJqZWN0JnF1b3Q7OiZxdW90O2NwczIwMTkwMjI1NTczODIyMCZxdW90OywgJnF1b3Q7cHJvZHVjdF9jb2RlJnF1b3Q7OiZxdW90O1FVSUNLX1dBUF9QQVkmcXVvdDsgfSI+CjxpbnB1dCB0eXBlPSJzdWJtaXQiIHZhbHVlPSLnq4vljbPmlK/ku5giIHN0eWxlPSJkaXNwbGF5Om5vbmUiID4KPC9mb3JtPgo8c2NyaXB0PmRvY3VtZW50LmZvcm1zWzBdLnN1Ym1pdCgpOzwvc2NyaXB0Pg==
         * respCode : 0100
         * respDesc : 等待支付
         */

        private String payInfo;
        private String respCode;
        private String respDesc;

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }
    }
}
