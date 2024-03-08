/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eapweather;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author softa
 */
@Entity
@Table(name = "CITY")
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByAreaname", query = "SELECT c FROM City c WHERE c.areaname = :areaname"),
    @NamedQuery(name = "City.findByViews", query = "SELECT c FROM City c WHERE c.views = :views")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AREANAME")
    private String areaname;
    @Basic(optional = false)
    @Column(name = "VIEWS")
    private int views;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityname")
    private List<Citydata> citydataList;

    public City() {
    }

    public City(String areaname) {
        this.areaname = areaname;
    }

    public City(String areaname, int views) {
        this.areaname = areaname;
        this.views = views;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<Citydata> getCitydataList() {
        return citydataList;
    }

    public void setCitydataList(List<Citydata> citydataList) {
        this.citydataList = citydataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (areaname != null ? areaname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.areaname == null && other.areaname != null) || (this.areaname != null && !this.areaname.equals(other.areaname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapweather.City[ areaname=" + areaname + " ]";
    }
    
}
