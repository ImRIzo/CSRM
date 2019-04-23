/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author R
 */
public class ReportList {
    String issueid, issue, customerid, customername, time, date, status, issuedetails;

    public ReportList(String issueid, String issue, String customerid, String customername, String time, String date, String status, String issuedetails) {
        this.issueid = issueid;
        this.issue = issue;
        this.customerid = customerid;
        this.customername = customername;
        this.time = time;
        this.date = date;
        this.status = status;
        this.issuedetails = issuedetails;
    }

    public String getIssueid() {
        return issueid;
    }

    public void setIssueid(String issueid) {
        this.issueid = issueid;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssuedetails() {
        return issuedetails;
    }

    public void setIssuedetails(String issuedetails) {
        this.issuedetails = issuedetails;
    }

    
    
}
