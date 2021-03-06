package com.unicloud.repository;

import com.unicloud.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dzkan on 2016/3/18.
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

    // 修改博文操作
    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.title=:qTitle,blog.titleId=:titleId, " +
            " blog.content=:qContent, blog.pubDate=:qPubDate,blog.costtime=:costtime,blog.type=:type where blog.id=:qId")
    void updateBlog(@Param("qTitle") String title,@Param("titleId") String titleId, @Param("qContent") String content,
                    @Param("qPubDate") String pubDate, @Param("qId") int id,@Param("costtime") float costtime,@Param("type") String type);
    @Modifying
    @Transactional
    @Query("update BlogEntity blog set blog.deleted=:deleted where blog.id=:qId")
    void updateBlog(@Param("qId") int id,@Param("deleted") int i);
}
