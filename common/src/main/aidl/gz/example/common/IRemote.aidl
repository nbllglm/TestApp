// IRemote.aidl
package gz.example.common;

// Declare any non-default types here with import statements
import gz.example.common.IListener;
interface IRemote {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void registerListener(in IBinder listener);
       void unregisterListener(in IBinder listener);
       oneway void SerTestOneWay(in int[] pa);
       int SerTestIn(in int[] pa);
       int SerTestOut(out int[] pa);
       int SerTestInout(inout int[] pa);
}