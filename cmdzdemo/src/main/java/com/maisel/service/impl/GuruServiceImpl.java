package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.AlbumDao;
import com.maisel.dao.ArticleDao;
import com.maisel.dao.GuruDao;
import com.maisel.entity.Album;
import com.maisel.entity.Article;
import com.maisel.entity.Guru;
import com.maisel.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.*;

/*
*
 * @aucthor:jjx
 * @Create:2019-01-05 13:42

*/

@Service
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private AlbumDao albumDao;
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Guru> wrapper = new QueryWrapper<Guru>();
        if (name != null && !"".equals(name)) {
            wrapper.like("guru_name", name);
        }
        Page<Guru> guruPage = new Page<Guru>(page, rows);
        IPage<Guru> iPage = guruDao.selectPage(guruPage, wrapper);
        List<Guru> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public List<Guru> getAll() {
        return guruDao.selectList(null);
    }

    @Override
    public void add(Guru guru) {
        guruDao.insert(guru);
    }

    @Override
    public void updateStatus(Guru guru) {
        // 改变上师的状态
        guruDao.updateById(guru);

        // ----------------- 修改该上师下的专辑的状态 ----------------
        // 1.获取到上师的名字
        String guruName = guruDao.selectById(guru.getGuruId()).getGuruName();
        // 2.将专辑的状态修改为跟上师的状态一样
        Album album = new Album();
        album.setAlbumStatus(guru.getGuruStatus());

        // 3.根据专辑中的作者名字修改专辑状态
        UpdateWrapper<Album> wrapper = new UpdateWrapper<Album>();
        wrapper.eq("album_author", guruName);
        albumDao.update(album, wrapper);

        // ------------------ 修改该上师下的文章的状态 ----------------

        // 1.将文章的状态修改为跟上师的状态一样
        Article article = new Article();
        article.setArticleStatus(guru.getGuruStatus());
        // 2.根据文章中上师id进行修改
        UpdateWrapper<Article> wrapper1 = new UpdateWrapper<Article>();
        wrapper1.eq("guru_id", guru.getGuruStatus());
        articleDao.update(article, wrapper1);

    }

    @Override
    public void update(Guru guru) {
        guruDao.updateById(guru);
    }

    @Override
    public void multiRemove(Integer[] ids) {

        // 修改上师的状态，实现假删除
        List<Integer> list = Arrays.asList(ids);
        Guru guru = new Guru();
        // 0代表删除、1代表冻结、2代表正常
        guru.setGuruStatus(0);
        UpdateWrapper<Guru> wrapper = new UpdateWrapper<Guru>();
        wrapper.in("guru_id", list);
        guruDao.update(guru, wrapper);
        // ----------------- 修改该上师下的专辑的状态 ----------------

        // 1.获取上师名字
        List<Guru> gurus = guruDao.selectBatchIds(list);
        List<String> guruNames = new ArrayList<String>();
        for (Guru gurus1 : gurus) {
            guruNames.add(gurus1.getGuruName());
        }
        // 2.修改专辑的状态跟上师的状态一致
        Album album = new Album();
        album.setAlbumStatus(0);
        // 3.根据专辑的作者名字进行修改
        UpdateWrapper<Album> wrapper1 = new UpdateWrapper<Album>();
        wrapper1.in("album_author", guruNames);
        albumDao.update(album, wrapper1);

        // ------------------ 修改该上师下的文章的状态 ----------------

        // 1.将文章的状态修改为跟上师的状态一样
        Article article = new Article();
        article.setArticleStatus(0);
        // 2.根据文章中上师id进行修改
        UpdateWrapper<Article> wrapper2 = new UpdateWrapper<Article>();
        wrapper2.in("guru_id", list);
        articleDao.update(article, wrapper2);
    }
}
