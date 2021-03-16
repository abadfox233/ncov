晨午检自动填报工具
-----

## Usage

1. 获取可执行程序
   
   1. 克隆本仓库自行打包
   
      ```shell
      mvn clean package
      ```
   
   2. 或者下载 Release
   
2. 填写上报信息

```yaml
studentId: "学号"
password: "密码"

message:
  tw: 1
  sfzx: 1
  area: "陕西省 西安市 雁塔区"
  city: "西安市"
  province: "陕西省"
  address: "陕西省西安市雁塔区电子城街道西安电子科技大学北校区世纪广场"
  geo_api_info: "{\"type\":\"complete\",\"position\":{\"Q\":34.230770128039,\"R\":108.91731526692797,\"lng\":108.917315,\"lat\":34.23077},\"location_type\":\"html5\",\"message\":\"Get ipLocation failed.Get geolocation success.Convert Success.Get address success.\",\"accuracy\":57,\"isConverted\":true,\"status\":1,\"addressComponent\":{\"citycode\":\"029\",\"adcode\":\"610113\",\"businessAreas\":[],\"neighborhoodType\":\"\",\"neighborhood\":\"\",\"building\":\"\",\"buildingType\":\"\",\"street\":\"太白南路\",\"streetNumber\":\"2号\",\"country\":\"中国\",\"province\":\"陕西省\",\"city\":\"西安市\",\"district\":\"雁塔区\",\"township\":\"电子城街道\"},\"formattedAddress\":\"陕西省西安市雁塔区电子城街道西安电子科技大学北校区世纪广场\",\"roads\":[],\"crosses\":[],\"pois\":[],\"info\":\"SUCCESS\"}"
  sfcyglq: 0
  sfyzz: 0
  qtqk: "无"
  ymtys: 0
```

> 默认北校区地址

message 中的数据为要上报的信息, 解释如下

| 名称           | 解释                                                         |
| -------------- | ------------------------------------------------------------ |
| `sfzx`         | 是否在校 0 - 不在校 1 - 在校                                 |
| `tw`           | 体温 0 - 36℃ 以下 <br>1 - 36℃ -36.5℃ <br/>2 - 36.5℃-36.9℃ <br/>3 -  36.9℃-37.3℃ <br/>4 -  37.3℃-38℃  <br/>5 -  38℃-38.5 <br/>6 - 38.5℃-39℃ <br/>7 -  39℃-40℃ <br/>8 -  40℃以上 |
| `area`         | 地区                                                         |
| `city`         | 城市                                                         |
| `province`     | 省份                                                         |
| `address`      | 详细地址                                                     |
| `geo_api_info` | 经纬度信息                                                   |
| `sfcyglq`      | 是否处于隔离期 0 - 否 1 - 是                                 |
| `sfyzz`        | 是否出现乏力、干咳、呼吸困难  0 - 否  1 - 是                 |
| `qtqk`         | 其他情况                                                     |
| `ymtys`        | 一码通颜色 0 - 绿色 1 - 黄色 2 - 红色                        |

3. 启动上报程序

```shell
java -jar ncov.jar 配置文件位置
```





## 更新日志

* 2021-1-4 增加延时参数

```shell
java -jar ncov.jar 配置文件位置 延时
# 示例
java -jar ncov.jar ./config.yaml 1
```

> 延时以分钟为单位，小于等于0时不延时。