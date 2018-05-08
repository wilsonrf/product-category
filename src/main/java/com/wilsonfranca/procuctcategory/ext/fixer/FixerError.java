package com.wilsonfranca.procuctcategory.ext.fixer;

/**
 * Created by wilson on 08/05/18.
 *
 * {
 "success": false,
 "error": {
 "code": 104,
 "info": "Your monthly API request volume has been reached. Please upgrade your plan."
 }
 }
 *
 */
public class FixerError {

    private boolean success;
    private Error error;

    public static class Error {

        private Long code;
        private String info;

        public Long getCode() {
            return code;
        }

        public void setCode(Long code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Error{");
            sb.append("code=").append(code);
            sb.append(", info='").append(info).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FixerError{");
        sb.append("success=").append(success);
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
