package com.basic.core.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 日期填入策略
 * @author wenlf
 * @since 18-9-25
 */
public class DateMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.autoFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.autoFill(metaObject);
    }

    private void autoFill(MetaObject metaObject){
        // createDate,updateDate创建日期、修改日期;创建人，修改人
        Object createDate = getFieldValByName("createDate", metaObject);
        if (createDate == null) {
            setFieldValByName("createDate", new Date(), metaObject);
        }
        setFieldValByName("updateDate", new Date(), metaObject);

        Object createTime = getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
