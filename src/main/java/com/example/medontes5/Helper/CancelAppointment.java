package com.example.medontes5.Helper;

import java.io.Serializable;
import java.util.Date;

public class CancelAppointment implements Serializable {

    private int docId;
    private int patId;
    private Date data;

    public CancelAppointment(){}
    public CancelAppointment(int docId, int patId, Date data) {
        this.docId = docId;
        this.patId = patId;
        this.data = data;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
