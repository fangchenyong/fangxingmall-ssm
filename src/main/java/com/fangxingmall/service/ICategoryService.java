package com.fangxingmall.service;


import com.fangxingmall.common.ServerResponse;
import com.fangxingmall.pojo.Category;

import java.util.List;


/**
 * @author 10136
 */
public interface ICategoryService {

    /**
     * 增加分类
     * @param categoryName
     * @param parentId
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新分类名称
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);

    /**
     * 获取子节点和平级节点
     * @param categoryId
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 查询子节点
     * @param categoryId
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
