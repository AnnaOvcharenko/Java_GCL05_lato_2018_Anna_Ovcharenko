package model;

public class Reader {
    private int reader_id;
    private String first_name;
    private String second_name;

    public Reader( String first_name, String second_name) {
       // this.reader_id = reader_id;
        this.first_name = first_name;
        this.second_name = second_name;
    }

    public Reader() {
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }
}
