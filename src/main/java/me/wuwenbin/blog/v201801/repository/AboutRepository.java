package me.wuwenbin.blog.v201801.repository;

import me.wuwenbin.blog.v201801.model.About;
import me.wuwenbin.modules.repository.annotation.type.Repository;
import me.wuwenbin.modules.repository.api.open.IBaseCrudRepository;
import me.wuwenbin.modules.repository.provider.update.annotation.Modify;
import org.springframework.transaction.annotation.Transactional;

import static me.wuwenbin.modules.sql.constant.Router.DEFAULT;

/**
 * created by Wuwenbin on 2018/1/16 at 17:00
 */
@Repository
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public interface AboutRepository extends IBaseCrudRepository<About, Long> {

    @Modify(DEFAULT)
    int updateByTab(About about);

    About findByTab(String tab);
}
