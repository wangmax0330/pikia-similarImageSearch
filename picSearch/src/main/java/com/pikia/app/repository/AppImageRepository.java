package com.pikia.app.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.pikia.app.componet.repository.ModelRepository;
import com.pikia.app.domain.AppImageDomain;

public interface AppImageRepository extends ModelRepository {

	@Select({ "SELECT * FROM APP_IMAGE WHERE ID=#{id}" })
	@Results({
			@Result(property = "picUrl", column = "PIC_URL", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "fingerPrint", column = "FINGER_PRINT", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createTime", column = "CREATE_TIME", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "editdistance", column = "EDITDISTANCE", javaType = Integer.class, jdbcType = JdbcType.INTEGER)})
	public AppImageDomain get(@Param("id") Long id);

	@SelectProvider(type = AppImageProvider.class, method = "getPagedModelIds")
	public List<Long> getPagedModelIds(@Param("startIndex") int startIndex,
			@Param("pageSize") int pageSize,
			@Param("sortField") String sortField,
			@Param("sortType") String sortType, @Param("name") Object name);

	@SelectProvider(type = AppImageProvider.class, method = "getTotalCount")
	public int getTotalCount(@Param("name") String name);

	
	
	@SelectProvider(type = AppImageProvider.class, method = "getModelIdList")
	public List<Long> getModelIdList(@Param("startIndex") int startIndex,
			@Param("pageSize") int pageSize,
			@Param("sortField") String sortField,
			@Param("sortType") String sortType, @Param("name") Object name);

	@SelectProvider(type = AppImageProvider.class, method = "getTotalList")
	public int getTotalList(@Param("name") String name);
	
	@Insert("INSERT INTO APP_IMAGE(PIC_URL,FINGER_PRINT,CREATE_TIME) VALUES(#{picUrl},#{fingerPrint},#{createTime})")
	@Options(keyProperty = "id", useGeneratedKeys = true)
	public Object save(@Param("domain") AppImageDomain domain);

	@Update("UPDATE APP_IMAGE SET FINGER_PRINT=#{domain.fingerPrint},PIC_URL=#{domain.picUrl},CREATE_TIME=#{domain.createTime},EDITDISTANSE=#{domain.editDistance} where ID=#{domain.id}")
	public Object update(@Param("domain") AppImageDomain domain);

}
