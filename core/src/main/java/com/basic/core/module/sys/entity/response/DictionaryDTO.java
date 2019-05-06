package com.basic.core.module.sys.entity.response;

import com.basic.core.module.sys.entity.SysDictionary;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  数据字典DTO
 *  data to object
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/5
 */
@Data
@ToString
@Accessors(chain = true)
public class DictionaryDTO {
    /**
     * 字典编码
     */
    private String dictType;
    /**
     * 中文名称
     */
    private String dictName;
    /**
     * 描述
     */
    private String remark;

    private List<SysDictionary> dictionaries = new ArrayList<>();

    public void addDictionary(SysDictionary dictionary){
        if(dictionary!=null){
            dictionaries.add(dictionary);
            this.dictType = dictionary.getDictType();
            this.dictName = dictionary.getDictName();
        }
    }
}
