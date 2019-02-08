package caffrey.dao;

import caffrey.bean.ShoppingCarItem;
import caffrey.bean.ShoppingCarItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShoppingCarItemMapper {
    long countByExample(ShoppingCarItemExample example);

    int deleteByExample(ShoppingCarItemExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(ShoppingCarItem record);

    int insertSelective(ShoppingCarItem record);

    List<ShoppingCarItem> selectByExample(ShoppingCarItemExample example);

    ShoppingCarItem selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") ShoppingCarItem record, @Param("example") ShoppingCarItemExample example);

    int updateByExample(@Param("record") ShoppingCarItem record, @Param("example") ShoppingCarItemExample example);

    int updateByPrimaryKeySelective(ShoppingCarItem record);

    int updateByPrimaryKey(ShoppingCarItem record);
    
    List<ShoppingCarItem> selectByVipId(@Param("vipId") Integer vipId);
}