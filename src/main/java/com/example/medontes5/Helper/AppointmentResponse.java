package com.example.medontes5.Helper;

import java.io.Serializable;

public class AppointmentResponse<T> implements Serializable {
    private T data;
    private String mesazhi;
    private int statusi;
    private String errori;

    public AppointmentResponse(AppointmentResponseBuilder conversationResponseBuilder){
        this.data=(T)conversationResponseBuilder.data;
        this.mesazhi=conversationResponseBuilder.mesazhi;
        this.statusi=conversationResponseBuilder.statusi;
        this.errori=conversationResponseBuilder.errori;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }

    public int getStatusi() {
        return statusi;
    }

    public void setStatusi(int statusi) {
        this.statusi = statusi;
    }

    public String getErrori() {
        return errori;
    }

    public void setErrori(String errori) {
        this.errori = errori;
    }

    public static class AppointmentResponseBuilder<T>{
        private T data;
        private String mesazhi;
        private int statusi;
        private String errori;
        public AppointmentResponseBuilder(int statusi){
            this.statusi = statusi;
        }
        public AppointmentResponseBuilder setData(T data){
            this.data = data;
            return this;
        }
        public AppointmentResponseBuilder setMesazhin(String mesazhi){
            this.mesazhi = mesazhi;
            return this;
        }
        public AppointmentResponseBuilder setErrorin(String errori){
            this.errori = errori;
            return this;
        }
        public AppointmentResponse build(){
            return new AppointmentResponse (this);
        }

    }
}
