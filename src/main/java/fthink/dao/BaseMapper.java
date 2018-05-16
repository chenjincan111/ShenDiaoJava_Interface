package fthink.dao;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author CAIJIE
 *
 */
public interface BaseMapper {

	public int deleteByPrimaryKey(String id);

    public <T> int insert(T record) ;

    public <T> int insertSelective(T record);

    public <T> T selectByPrimaryKey(String id);

    public <T> int updateByPrimaryKeySelective(T record);

    public <T> int updateByPrimaryKey(T record);
    
    public <T>  List<T> list(Map<String,Object> map);
    
    public <T> int  audit(T record);
    
    public <T> int  publish(T record);

}
