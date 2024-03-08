/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eapweather;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author softa
 */
@Entity
@Table(name = "CITYDATA")
@NamedQueries({
    @NamedQuery(name = "Citydata.findAll", query = "SELECT c FROM Citydata c"),
    @NamedQuery(name = "Citydata.findById", query = "SELECT c FROM Citydata c WHERE c.id = :id"),
    @NamedQuery(name = "Citydata.findByDate", query = "SELECT c FROM Citydata c WHERE c.date = :date"),
    @NamedQuery(name = "Citydata.findByCTemp", query = "SELECT c FROM Citydata c WHERE c.cTemp = :cTemp"),
    @NamedQuery(name = "Citydata.findByHumidity", query = "SELECT c FROM Citydata c WHERE c.humidity = :humidity"),
    @NamedQuery(name = "Citydata.findByWspeedinkmph", query = "SELECT c FROM Citydata c WHERE c.wspeedinkmph = :wspeedinkmph"),
    @NamedQuery(name = "Citydata.findByUvindex", query = "SELECT c FROM Citydata c WHERE c.uvindex = :uvindex"),
    @NamedQuery(name = "Citydata.findByWeatherdesc", query = "SELECT c FROM Citydata c WHERE c.weatherdesc = :weatherdesc")})
public class Citydata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "C_TEMP")
    private int cTemp;
    @Basic(optional = false)
    @Column(name = "HUMIDITY")
    private int humidity;
    @Basic(optional = false)
    @Column(name = "WSPEEDINKMPH")
    private int wspeedinkmph;
    @Basic(optional = false)
    @Column(name = "UVINDEX")
    private int uvindex;
    @Basic(optional = false)
    @Column(name = "WEATHERDESC")
    private String weatherdesc;
    @JoinColumn(name = "CITYNAME", referencedColumnName = "AREANAME")
    @ManyToOne(optional = false)
    private City cityname;

    public Citydata() {
    }

    public Citydata(Integer id) {
        this.id = id;
    }

    public Citydata(Integer id, Date date, int cTemp, int humidity, int wspeedinkmph, int uvindex, String weatherdesc) {
        this.id = id;
        this.date = date;
        this.cTemp = cTemp;
        this.humidity = humidity;
        this.wspeedinkmph = wspeedinkmph;
        this.uvindex = uvindex;
        this.weatherdesc = weatherdesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCTemp() {
        return cTemp;
    }

    public void setCTemp(int cTemp) {
        this.cTemp = cTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWspeedinkmph() {
        return wspeedinkmph;
    }

    public void setWspeedinkmph(int wspeedinkmph) {
        this.wspeedinkmph = wspeedinkmph;
    }

    public int getUvindex() {
        return uvindex;
    }

    public void setUvindex(int uvindex) {
        this.uvindex = uvindex;
    }

    public String getWeatherdesc() {
        return weatherdesc;
    }

    public void setWeatherdesc(String weatherdesc) {
        this.weatherdesc = weatherdesc;
    }

    public City getCityname() {
        return cityname;
    }

    public void setCityname(City cityname) {
        this.cityname = cityname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citydata)) {
            return false;
        }
        Citydata other = (Citydata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapweather.Citydata[ id=" + id + " ]";
    }
    
}
