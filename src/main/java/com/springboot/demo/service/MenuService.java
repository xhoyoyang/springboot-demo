package com.springboot.demo.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.springboot.demo.controller.request.MenuRequest;
import com.springboot.demo.dao.MenuMapper;
import com.springboot.demo.entity.Menu;
import com.springboot.demo.exception.DataNotExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 新增菜单
     * @param request
     */
    public void createMenu(MenuRequest request){

        Menu menu = new Menu();
        BeanUtils.copyProperties(request,menu);
        menu.buildForCreate();
        this.menuMapper.insert(menu);

    }

    /**
     * 修改菜单
     * @param request
     */
    public void updateMenu(MenuRequest request){

        //验证菜单是否存在
        Menu menu = this.menuMapper.selectById(request.getId());
        if (null == menu) throw new DataNotExistException("数据不存在");
        BeanUtils.copyProperties(request,menu);
        menu.buildForUpdatee();
        this.menuMapper.updateById(menu);
    }

    /**
     * 删除菜单
     * @param id
     */
    public void deleteMenu(Integer id){
        //验证菜单是否存在
        //Menu menu = this.menuMapper.selectById(id);
        this.menuMapper.deleteById(id);
    }

    /**
     * 查询所有菜单树
     * @return
     */
    public List<Tree<Integer>> listTree(){

        //所有菜单
        List<Menu> menus = this.menuMapper.selectList(null);

        //List<TreeNode<Integer>> treeNodes = new ArrayList<>();
        //menus.forEach(menu -> {
        //    TreeNode<Integer> treeNode = new TreeNode<>();
        //    treeNode.setId(menu.getId());
        //    treeNode.setParentId(menu.getParentId());
        //    treeNode.setName(menu.getMenuName());
        //    Map<String,Object> extra = new HashSet<>()
        //    treeNode.setExtra()
        //    treeNodes.add(treeNode);
        //});

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        List<Tree<Integer>> treeNodes = TreeUtil.build(menus, 0, treeNodeConfig,
                (menu, tree) -> {
                    tree.setId(menu.getId());
                    tree.setParentId(menu.getParentId());
                    tree.setName(menu.getMenuName());
                    // 扩展属性 ...
                    tree.putExtra("menuType", menu.getMenuType());
                    tree.putExtra("menuUrl", menu.getMenuUrl());
        });

        //List<Tree<Integer>> trees = TreeUtil.build(treeNodes);

        return treeNodes;
    }


}
