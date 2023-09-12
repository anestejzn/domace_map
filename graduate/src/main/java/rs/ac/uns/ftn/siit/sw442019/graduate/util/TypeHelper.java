package rs.ac.uns.ftn.siit.sw442019.graduate.util;

public class TypeHelper {

    public static String getImageForType (String type) {
        switch (type){
            case "Jabuka":
                return "./src/main/resources/images/types/apple.png";
            default:
                return "";
        }
    }
}
