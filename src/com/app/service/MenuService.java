package com.app.service;
import com.app.Dao.TSysMenuMapper;
import com.pojo.TSysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
private TSysMenuMapper tSysUserMapper;

    public TSysMenu getById( Integer id){

        TSysMenu tSysMenu=tSysUserMapper.selectByPrimaryKey(id);

        List<TSysMenu>menus=getChildren(tSysMenu,tSysMenu.getId());
        tSysMenu.getChildren().addAll(menus);
        return tSysMenu;
    };

    public List<TSysMenu>getChildren(TSysMenu root,Integer id){
        List<TSysMenu> menus=this.tSysUserMapper.selectByParentId(id);
    for (TSysMenu menu:menus){
        menu.setParent(root);
        //每个子菜单递归调用本方法来获取所有子菜单
        List<TSysMenu> children=getChildren(menu,menu.getId());
        //将递归遍历的子菜单添加到当前菜单中
        menu.getChildren().addAll(children);
    }
    return menus;
    };
}
