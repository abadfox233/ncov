package cn.yogehaoren.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 * 填报信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitMessage {

    /**
     * 是否在校
     * 0 - 不在校
     * 1 - 在校
     *
     */
    private Integer sfzx;

    /**
     * 体温
     * 0 - 36℃ 以下
     * 1 - 36℃ -36.5℃
     * 2 - 36.5℃-36.9℃
     * 3 - 36.9℃-37.3℃
     * 4 - 37.3℃-38℃
     * 5 - 38℃-38.5
     * 6 - 38.5℃-39℃
     * 7 - 39℃-40℃
     * 8 - 40℃以上
     */
    private Integer tw;

    /**
     * 地区
     */
    private String area;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 全部地址
     */
    private String address;

    /**
     * 经纬度信息
     */
    private String geo_api_info;

    /**
     * 是否处于隔离期
     * 0 - 否
     * 1 - 是
     */
    private Integer sfcyglq;

    /**
     * 是否出现乏力、干咳、呼吸困难
     * 0 - 否
     * 1 - 是
     */
    private Integer sfyzz;

    /**
     * 其他情况
     */
    private String qtqk;

    /**
     * 一码通颜色
     * 0 - 绿色
     * 1 - 黄色
     * 2 - 红色
     */
    private Integer ymtys;

    public RequestBody getEncodingData(){

        RequestBody formBody = new FormBody.Builder()
                .add("sfzx", String.valueOf(sfzx))
                .add("tw", String.valueOf(tw))
                .add("area", area)
                .add("city", city)
                .add("province", province)
                .add("address", address)
                .add("geo_api_info", geo_api_info)
                .add("sfcyglq", String.valueOf(sfcyglq))
                .add("sfyzz", String.valueOf(sfyzz))
                .add("qtqk", qtqk)
                .add("ymtys", String.valueOf(ymtys))
                .build();

        return formBody;
    }

    public static final SubmitMessage DEFAULT_MESSAGE = SubmitMessage.builder()
            .tw(1)
            .sfzx(1)
            .area("陕西省 西安市 雁塔区")
            .city("西安市")
            .province("陕西省")
            .address("陕西省西安市雁塔区电子城街道西安电子科技大学北校区世纪广场")
            .geo_api_info("{\"type\":\"complete\",\"position\":{\"Q\":34.230770128039,\"R\":108.91731526692797,\"lng\":108.917315,\"lat\":34.23077},\"location_type\":\"html5\",\"message\":\"Get ipLocation failed.Get geolocation success.Convert Success.Get address success.\",\"accuracy\":57,\"isConverted\":true,\"status\":1,\"addressComponent\":{\"citycode\":\"029\",\"adcode\":\"610113\",\"businessAreas\":[],\"neighborhoodType\":\"\",\"neighborhood\":\"\",\"building\":\"\",\"buildingType\":\"\",\"street\":\"太白南路\",\"streetNumber\":\"2号\",\"country\":\"中国\",\"province\":\"陕西省\",\"city\":\"西安市\",\"district\":\"雁塔区\",\"township\":\"电子城街道\"},\"formattedAddress\":\"陕西省西安市雁塔区电子城街道西安电子科技大学北校区世纪广场\",\"roads\":[],\"crosses\":[],\"pois\":[],\"info\":\"SUCCESS\"}")
            .sfcyglq(0)
            .sfyzz(0)
            .qtqk("无")
            .ymtys(0)
            .build();

    public static SubmitMessage getInstanceFromMap(Map<String, Object> configMap) throws IllegalAccessException {
        SubmitMessage submitMessage = new SubmitMessage();
        Class<SubmitMessage> configClazz = SubmitMessage.class;

        Field[] declaredFields = configClazz.getDeclaredFields();
        for (Field field: declaredFields){

            String name = field.getName();
            if(configMap.containsKey(name)){
                field.setAccessible(true);
                Object value = configMap.get(name);
                field.set(submitMessage, value);
            }


        }

        return submitMessage;

    }



}

