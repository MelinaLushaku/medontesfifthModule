package com.example.medontes5.Helper;

import java.io.Serializable;
import java.util.Date;

public class CancelAppointment implements Serializable {

    private int docId;
    private float timeC;
    private Date data;

    public CancelAppointment(){}
    public CancelAppointment(int docId, Date data , float timeC) {
        this.docId = docId;
        this.timeC = timeC;
        this.data = data;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public float getTimeC() {
        return timeC;
    }

    public void setTimeC(float timeC) {
        this.timeC = timeC;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
