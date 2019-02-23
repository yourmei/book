package caffrey.dao;

import caffrey.bean.LoginItem;
import caffrey.bean.LoginItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginItemMapper {
    long countByExample(LoginItemExample example);

    int deleteByExample(LoginItemExample example);

    int deleteByPrimaryKey(Integer loginId);

    int insert(LoginItem record);

    int insertSelective(LoginItem record);

    List<LoginItem> selectByExample(LoginItemExample example);

    LoginItem selectByPrimaryKey(Integer loginId);

    int updateByExampleSelective(@Param("record") LoginItem record, @Param("example") LoginItemExample example);

    int updateByExample(@Param("record") LoginItem record, @Param("example") LoginItemExample example);

    int updateByPrimaryKeySelective(LoginItem record);

    int updateByPrimaryKey(LoginItem record);
    
    Long seleteLastLoginSuccessTimeAfterTime(@Param("vipId") Integer vipId, @Param("time") Long time);
    
    Integer selectCntForLoginFailAfterTime(@Param("vipId") Integer vipId, @Param("time") Long time);
}