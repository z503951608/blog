package springcloud.club.blog.utils;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //
    private int offset;
    // 每页条数
    private int limit;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        if (params.containsKey("limit")) {
            this.limit = Integer.parseInt(params.get("limit").toString());
        }
        if (params.containsKey("offset")) {
            this.offset = Integer.parseInt(params.get("offset").toString());
        }
        if (params.containsKey("page")) {
            this.offset = Integer.parseInt(params.get("page").toString()) * 10 -10;
        }
        this.put("offset", offset);
        this.put("limit", limit);
    }
}
