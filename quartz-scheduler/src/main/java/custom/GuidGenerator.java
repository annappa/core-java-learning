package custom;

import java.rmi.dgc.VMID;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public final class GuidGenerator {

    public static String getGuid() {
        return new VMID().toString().replaceAll(":", "").replaceAll("-", "");
    }
}
