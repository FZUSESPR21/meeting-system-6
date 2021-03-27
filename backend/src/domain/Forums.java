public class Forums {
    private String forumnama;
    private String theme;
    private String chairman;
    private String time;
    public Forums(String a,String b,String c,String d){
        forumnama = a;
        theme = b;
        chairman = c;
        time = d;
    }
    public Forums(){
        forumnama = "0";
        theme = "0";
        chairman = "0";
        time = "0";
    }
    public String getForumnama(){
        return forumnama;
    }
    public String getTheme(){
        return theme;
    }
    public String getChairman(){
        return chairman;
    }
    public String getTime(){
        return time;
    }
    public void setForumnama(String forumnama) {
        this.forumnama = forumnama;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
