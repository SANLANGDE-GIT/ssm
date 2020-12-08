package com.atguigu.pojo;

import java.util.List;

/**
 * @author ru
 * @create 2020-11-07-20:31
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    /**
     * 当前页面
     */
    private Integer pageNo;
    /**
     * 总页码
     */
    private Integer pageTotal;
    /**
     * 当前页显示的数量
     */
    private Integer pageSize = PAGE_SIZE;
    /**
     * 总记录数
     */
    private Integer pageTotalCount;
    /**
     * 当前页面数据
     */
    private List<T> items;
    /**
     * 分页条地址
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 一定要在pageTotal有值之后
     */
    public void setPageNo(Integer pageNo) {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal && pageTotal != 0) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
