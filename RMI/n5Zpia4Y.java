package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class n5Zpia4Y {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");

        String nhan = sv.requestCharacter("B22DCCN470", "n5Zpia4Y");
        System.out.println("String:" + nhan);

        String key = "";
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < nhan.length(); i++){
            char d = nhan.charAt(i);
            if(map.containsKey(d)){
                map.put(d, map.get(d) + 1);
            } else map.put(d, 1);
        }
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            Character d = entry.getKey();
            int cnt = entry.getValue();

            key += (d + "") + (cnt + "");
        }
        sv.submitCharacter("B22DCCN470", "n5Zpia4Y", key);
        System.out.println("Key:" + key);

    }
}
