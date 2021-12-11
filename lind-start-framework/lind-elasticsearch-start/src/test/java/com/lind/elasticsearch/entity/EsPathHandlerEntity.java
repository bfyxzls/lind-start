package com.lind.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 拦截路径表
 * </p>
 *
 * @author ssz
 * @since 2021-05-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setting(settingPath = "mapping/es-setting.json")
@Document(indexName = "operator_logger_svt")
public class EsPathHandlerEntity {
  public static final String dateTimeFormat =
      "yyyy||yyyyMM||yyyy.MM||yyyy/MM||yyyy-MM||yyyyMMdd||yyyy.MM.dd||yyyy/MM/dd||yyyy-MM-dd||yyyy-MM-dd HH:mm:ss||" +
          "yyyy.MM.dd HH:mm:ss||yyyy/MM/dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS||yyyy.MM.dd HH:mm:ss.SSS||yyyy/MM/dd HH:mm:ss.SSS";
  //主键
  @Id
  @Field(type = FieldType.Keyword)
  private String id = UUID.randomUUID().toString();

  //拦截路径
  @Field(type = FieldType.Keyword)
  private String project;

  //拦截路径
  @Field(type = FieldType.Keyword)
  private String path;

  //添加人
  @Field(type = FieldType.Keyword)
  private String createBy;

  //添加时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @Field(type = FieldType.Date, format = DateFormat.custom, pattern = dateTimeFormat)
  private Date createTime;

  //路径描述
  @Field(type = FieldType.Keyword)
  private String pathDescribes;

  //ip
  @Field(type = FieldType.Keyword)
  private String ip;

  //请求方式
  @Field(type = FieldType.Keyword)
  private String requestMethod;

  //请求体
  @Field(type = FieldType.Keyword)
  private String body;


  //kibana里索引字段
  @Field(type = FieldType.Date, format = DateFormat.custom, pattern ="yyyy-MM-ddTHH:mm:ss+0800")// 指定存储格式
  @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")// 数据格式转换，并加上8小时进行存储
  private String timestamp;
}
