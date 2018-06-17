package CRUD;

public interface CRUD {
    public int create(Object object);
    public void read();
    public void update(int id, String newData);
    public void delete (int id);

}
