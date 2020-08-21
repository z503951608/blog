package springcloud.club.blog.snowflake.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.*;
import springcloud.club.blog.snowflake.annotation.PrimaryId;

import java.util.Date;

/**
 * 
 * @ClassName : DqBaseDO
 * @Description : 所有持久化实体的基础类 --阿里规范手册建议实用DO为后缀--但是若包已do后缀结尾将报错--因此使用entity作为后缀
 * @date 2017年12月4日 下午12:47:39
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity<T> {
	@Id
	@PrimaryId
	private Long id;
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;
	@Version
	private Integer version;
	@CreatedBy
	private String createBy;
	@LastModifiedBy
	private String lastUpdateBy;
	/** 删除标志 */
	private Integer delFlag;
	/** 可用标志 */
	private Integer available;
	/** 备注 */
	private String remark;

	public BaseEntity id(Long id){
		this.id = id;
		return this;
	}

	public BaseEntity createTime(Date createTime){
		this.createTime = createTime;
		return this;
	}

	public BaseEntity lastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
		return this;
	}
	public BaseEntity version(Integer version){
		this.version = version;
		return this;
	}

	public BaseEntity createBy(String createBy){
		this.createBy = createBy;
		return this;
	}
	public BaseEntity lastUpdateBy(String lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
		return this;
	}
	public BaseEntity delFlag(Integer delFlag){
		this.delFlag = delFlag;
		return this;
	}

	public BaseEntity available(Integer available){
		this.available = available;
		return this;
	}
	public BaseEntity remark(String remark){
		this.remark = remark;
		return this;
	}

}
