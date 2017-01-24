package md.utm.fi.proxy;

import md.utm.fi.sincronizeServices.SerializationServices;
import redis.clients.jedis.Jedis;

/**
 * Created by E7450 on 1/24/2017.
 */
public class CachingUtils {
    static Jedis jedis = new Jedis("localhost");
    public static  void setUsersInCache(String key, Object obj){
        String result= SerializationServices.serializeObjects(obj);
        jedis.set(key, result);
        jedis.expire(key,10);
    }
    public static void getUsersFrom(String key){
        jedis.get(key);
    }

}
