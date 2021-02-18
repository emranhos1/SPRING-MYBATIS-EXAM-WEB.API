package com.eh.grocery.dev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Entity
@Table(name = "gender")
public class Gender implements Serializable {

    private static final long serialVersionUID = -5447498767683263429L;

    @Column(name="t_entry_user")
    private String entryUser;
    @Column(name="t_entry_date")
    private Date entryDate;
    @Column(name="t_upd_user")
    private String amendUser;
    @Column(name="t_upd_date")
    private Date amendDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_sex_code")
    private long code;
    @Column(name="t_lang1_name")
    private String nameNative;
    @Column(name="t_lang2_name")
    private String nameGlobal;
    @Column(name="t_short_gndr_name")
    private String shortName;

    public String getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getAmendUser() {
        return amendUser;
    }

    public void setAmendUser(String amendUser) {
        this.amendUser = amendUser;
    }

    public Date getAmendDate() {
        return amendDate;
    }

    public void setAmendDate(Date amendDate) {
        this.amendDate = amendDate;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public String getNameGlobal() {
        return nameGlobal;
    }

    public void setNameGlobal(String nameGlobal) {
        this.nameGlobal = nameGlobal;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
