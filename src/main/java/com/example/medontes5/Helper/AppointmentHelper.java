package com.example.medontes5.Helper;

import java.io.Serializable;
import java.util.Date;

public class AppointmentHelper implements Serializable {
    private Date data;
    private boolean isFree;
  //  private int doctorPersonalNumber;
  //  private double oraTerminit;

    public AppointmentHelper(){}
     public AppointmentHelper(Date data , boolean isFree ){
        this.data= data;
        this.isFree= isFree;
    //    this.doctorPersonalNumber = doctorPersonalNumber;

     }

    /*public double getTime() {
        return oraTerminit;
    }

    public void setTime(double oraTerminit) {
        this.oraTerminit = oraTerminit;
    }*/

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

  /*  public int getDocPrNumber() {
        return doctorPersonalNumber;
    }

    public void setDocPrNumber(int doctorPersonalNumber) {
        this.doctorPersonalNumber = doctorPersonalNumber;
    }
*/
}
