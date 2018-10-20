
package DAO;

import java.util.List;

//fungsi interface hanya berperan sebagai pembuat kerangka dari 
//beberapa method yang akan digunakan oleh class lain
public interface Model_DAO <A>{
    public int autonumber(A object);    
    public void insert(A object);
    public void update(A object);
    public void delete(Integer id);
    public List<A> getAll();
    public List<A> getCari(String key);   

}

