package md.utm.fi.proxy;

import md.utm.fi.model.User;
import md.utm.fi.sincronizeServices.SerializationServices;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by E7450 on 1/24/2017.
 */
public class CachingUtils {
    static Jedis jedis = new Jedis("localhost");
    public static  void setUsersInCache(String key, Object obj){
        String result= SerializationServices.serializeObjects(obj);
        jedis.set(key, result);
        jedis.expire(key,120);
    }
    public static User getUserFromChache(String key){
        if(jedis.get(key)!=null) {
            User u = SerializationServices.deserializeUser(jedis.get(key));
            return  u;
        }
        return null;
    }
    public static List<User> getUsersListFromChache(String key){
        if(jedis.get(key)!=null)
        return SerializationServices.deserializeUsersList(jedis.get(key));
        return null;
    }

}
