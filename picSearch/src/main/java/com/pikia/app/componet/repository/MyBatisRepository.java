package com.pikia.app.componet.repository;

import java.util.List;

public abstract interface MyBatisRepository {
	public abstract List queryForPagination(int paramInt1, int paramInt2,
			String paramString1, String paramString2,
			Object[] paramArrayOfObject);
}