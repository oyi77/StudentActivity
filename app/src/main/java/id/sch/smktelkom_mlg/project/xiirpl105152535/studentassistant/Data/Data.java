package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant.Data;

/**
 * Created by Administrator on 14/11/2016.
 */

public class Data {
    private String pelajaran;
    private String isi;
    private String due;
    private String status;
    private String mapel;
    private String jam;
    private String hari;
    private String guru;


    public Data() {

    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getStatus(){return status;}

    public void setStatus(String status){this.status = status;}

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getGuru(){return guru;}

    public void setGuru(String guru){this.guru = guru;}
}
