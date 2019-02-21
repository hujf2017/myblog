package com.myblog.mapper;

import com.myblog.pojo.BgUser;
import com.myblog.pojo.BgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BgUserMapper {
    int countByExample(BgUserExample example);

    int deleteByExample(BgUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BgUser record);

    int insertSelective(BgUser record);

    List<BgUser> selectByExample(BgUserExample example);

    BgUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BgUser record, @Param("example") BgUserExample example);

    int updateByExample(@Param("record") BgUser record, @Param("example") BgUserExample example);

    int updateByPrimaryKeySelective(BgUser record);

    int updateByPrimaryKey(BgUser record);
}