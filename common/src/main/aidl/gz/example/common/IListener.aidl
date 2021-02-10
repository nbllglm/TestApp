// IListener.aidl
package gz.example.common;

// Declare any non-default types here with import statements

interface IListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
 void onResponse(String str);
}