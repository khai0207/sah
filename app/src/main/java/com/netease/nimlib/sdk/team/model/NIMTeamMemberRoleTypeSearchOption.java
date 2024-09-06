package com.netease.nimlib.sdk.team.model;

import com.netease.nimlib.sdk.msg.model.SearchOrderEnum;
import com.netease.nimlib.sdk.team.constant.TeamMemberType;
import java.util.List;

/* loaded from: classes.dex */
public class NIMTeamMemberRoleTypeSearchOption {
    private Integer limit;
    private Integer offset;
    private SearchOrderEnum order;
    private List<TeamMemberType> roleTypes;

    public NIMTeamMemberRoleTypeSearchOption() {
    }

    public NIMTeamMemberRoleTypeSearchOption(List<TeamMemberType> list, Integer num, SearchOrderEnum searchOrderEnum, Integer num2) {
        this.roleTypes = list;
        this.offset = num;
        this.order = searchOrderEnum;
        this.limit = num2;
    }

    public List<TeamMemberType> getRoleTypes() {
        return this.roleTypes;
    }

    public void setRoleTypes(List<TeamMemberType> list) {
        this.roleTypes = list;
    }

    public Integer getOffset() {
        Integer num = this.offset;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public void setOffset(Integer num) {
        this.offset = num;
    }

    public SearchOrderEnum getOrder() {
        SearchOrderEnum searchOrderEnum = this.order;
        return searchOrderEnum == null ? SearchOrderEnum.DESC : searchOrderEnum;
    }

    public void setOrder(SearchOrderEnum searchOrderEnum) {
        this.order = searchOrderEnum;
    }

    public Integer getLimit() {
        Integer num = this.limit;
        if (num == null) {
            return 10;
        }
        return num;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public boolean isValid() {
        Integer num = this.limit;
        return num == null || num.intValue() > 0;
    }
}
