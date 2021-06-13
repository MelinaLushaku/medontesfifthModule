package com.example.medontes5.Helper;

import java.io.Serializable;
import java.util.Date;

public class AppointmentHelper implements Serializable {
    private Date data;
    private boolean isFree;
    private int docPrNumber;

    public AppointmentHelper(){}
     public AppointmentHelper(Date data , boolean isFree , int docPrNumber){
        this.data= data;
        this.isFree= isFree;
        this.docPrNumber = docPrNumber;
     }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getDocPrNumber() {
        return docPrNumber;
    }

    public void setDocPrNumber(int docPrNumber) {
        this.docPrNumber = docPrNumber;
    }

}
