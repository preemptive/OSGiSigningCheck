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
Certificate Principal: CN=Example Alias, OU=Development, O=PreEmptive Solutions, L=Mayfield Village, ST=OH, C=US  
SignedOSGiExample is still signed.

### Unsigned Output
Starting SignedOSGiExample...  
SignedOSGiExample was loaded by {an OSGi implementation specific ClassLoader}.  
Checking certificates...  
There are 0 certificates in {the URL location of the bundle}.  
SignedOSGiExample is no longer signed.

##Equinox Settings
Out of the box, the [Equinox](http://eclipse.org/equinox/) implementation will not load the certificates. To run with [Equinox](http://eclipse.org/equinox/), pass _-Dosgi.signedcontent.support=certificate_ when launching.  If that does not work, make sure you are not passing _-Dosgi.support.class.certificate=false_.  See [http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fmisc%2Fruntime-options.html](http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fmisc%2Fruntime-options.html) for more details.

##Felix Settings
Out of the box, the [Felix](http://felix.apache.org) implementation will not load the certificates.  To run with [Felix](http://felix.apache.org), install the _org.apache.felix.framework.security_ bundle and pass _-Djava.security.policy={sample directory}/all.policy_, _-Dorg.osgi.framework.security="osgi"_, and  _-Dorg.osgi.framework.trust.repositories={sample directory}/certs.ks_ when launching.  See [http://felix.apache.org/site/apache-felix-framework-security.html](http://felix.apache.org/site/apache-felix-framework-security.html) for additional details.