# OSGiSigningCheck
An OSGi bundle which reports on the status of the bundle's jar signing information upon launch.
Compiling and installing this bundle will inform you if your OSGi configuration ignores or supports signed bundles.

## Building
Simply run _gradlew_.  It will download any files necessary, compile the bundle, and sign it.  You can also use the prebuilt jar.

##Deploying
Install the compiled jar _(build/libs/SignedOSGiExample-1.0.jar)_, or the prebuilt one, to your OSGi system and start the bundle.  The console will report if the bundle is still signed.

### Signed Output
Starting SignedOSGiExample...  
SignedOSGiExample was loaded by {an OSGi implementation specific ClassLoader}.  
Checking certificates...  
There is 1 certificate in {the URL location of the bundle}.  
[B@71cc81b0  
SignedOSGiExample is still signed.

### Unsigned Output
Starting SignedOSGiExample...  
SignedOSGiExample was loaded by {an OSGi implementation specific ClassLoader}.  
Checking certificates...  
There are 0 certificates in {the URL location of the bundle}.  
SignedOSGiExample is no longer signed.
