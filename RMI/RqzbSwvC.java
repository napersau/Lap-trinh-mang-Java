package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;

public class RqzbSwvC {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");

        String student = "B22DCCN290";
        String code = "RqzbSwvC"; 
        String s = sv.requestCharacter(student, code);
        System.out.println("S: " + s);

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < s.length(); i++){
            char d = s.charAt(i);
            if(map.containsKey(d)){
                map.put(d, map.get(d) + 1);
            } else map.put(d, 1);
        }

        String keyL[] = new String[map.size()];

        int t = 0;
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            // Thêm dấu ngoặc kép quanh key
            keyL[t++] = "\"" + entry.getKey() + "\": " + entry.getValue();
        }

        String key = "{" + String.join(", ", keyL) + "}";
        System.out.println("Key gửi: " + key);

        sv.submitCharacter(student, code, key);
    }
}
