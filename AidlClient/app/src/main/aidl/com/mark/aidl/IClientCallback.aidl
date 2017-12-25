// IClientCallback.aidl
package com.mark.aidl;

// Declare any non-default types here with import statements
import com.mark.aidl.entry.CodeEntry;

interface IClientCallback {

    void callback(in CodeEntry entry);

}
