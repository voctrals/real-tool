package ${package.servicePackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import cn.tradewin.reach.web.pagination.Pager;
import cn.tradewin.reach.web.util.BeanMapper;
import cn.tradewin.reach.web.exception.AppException;
import ${package.daoPackage}.${name}Dao;
import ${package.dtoPackage}.${name}Dto;
import ${package.modelPackage}.${name};

@Service
public class ${bussinessName}Service {

    @Autowired
    private ${name}Dao ${name?lower_case}Dao;

    @Transactional
    public int insert(${name}Dto ${name?lower_case}Dto, BindingResult bindingResult) {
        ${name} model = BeanMapper.map(${name?lower_case}Dto, ${name}.class);

        <#if hasIdentity != '1'>
        ${name} result = ${name?lower_case}Dao.selectByPrimaryKey(model);
            if (result != null) {
            throw new AppException("", "common.message.error.dataexist", bindingResult);
            }
        </#if>

        return ${name?lower_case}Dao.insert(model);
    }

    @Transactional
    public void update(${name}Dto ${name?lower_case}Dto, BindingResult bindingResult) {
        ${name} model = BeanMapper.map(${name?lower_case}Dto, ${name}.class);

        ${name?lower_case}Dao.updateByPrimaryKey(model);
    }

    @Transactional
    public void delete(${name}Dto ${name?lower_case}Dto) {
        ${name} model = BeanMapper.map(${name?lower_case}Dto, ${name}.class);
        ${name?lower_case}Dao.deleteByPrimaryKey(model);
    }

    public ${name}Dto selectByPrimaryKey(${name}Dto ${name?lower_case}Dto) {
        ${name} model = BeanMapper.map(${name?lower_case}Dto, ${name}.class);
        ${name} result = ${name?lower_case}Dao.selectByPrimaryKey(model);

        return BeanMapper.map(result, ${name}Dto.class);
    }

}
