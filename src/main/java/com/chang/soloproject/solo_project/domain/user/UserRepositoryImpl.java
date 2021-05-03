package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import com.chang.soloproject.solo_project.domain.common.CustomQuerydslRepositorySupport;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jdk.nashorn.internal.objects.annotations.Where;
import org.hibernate.annotations.Fetch;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.chang.soloproject.solo_project.domain.user.QUser.user;


public class UserRepositoryImpl extends CustomQuerydslRepositorySupport implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> findUsers(UserSearchReq userSearchReq) {
        final List<User> users = queryFactory
                .selectFrom(user)
                .where(eq(user.loginId, userSearchReq.getLoginId())).
                        fetch();
        return users;
    }


    @Override
    public Optional<User> findByLoginId(String loginId) {
        final User fetchUser = queryFactory
                .selectFrom(user)
                .where(eq(user.loginId, loginId))
                .fetchOne();

        return Optional.ofNullable(fetchUser);
    }

    @Override
    public boolean existsUserByLoginId(String loginId) {
        final Integer fetchFirstUser = queryFactory
                .selectOne()
                .from(user)
                .where(eq(user.loginId, loginId))
                .fetchFirst();

        return Optional.ofNullable(fetchFirstUser).orElse(0) > 0;
    }




    /**
     * WHERE 절 조건
     */
    // Long
    public static BooleanExpression eq(NumberPath<Long> domainValue, Long value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression in(NumberPath<Long> domainValue, List<Long> value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.in(value);
    }

    // String
    public static BooleanExpression eq(StringPath domainValue, String value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression like(StringPath domainValue, String value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.likeIgnoreCase("%" + value + "%");
    }

    // Boolean
    private static BooleanExpression eq(BooleanPath domainValue, Boolean value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    // Enum
    private static <T extends Enum<T>> BooleanExpression eq(EnumPath<T> domainValue, T value) {
        if (StringUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }


}