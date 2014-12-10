package com.preEmptive.example.signedOSGi;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SignedActivator implements BundleActivator {

	private static BundleContext context;
	private static boolean printEquinoxProperties=false;
	private static boolean printFelixProperties=false;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		SignedActivator.context = bundleContext;
		System.out.println("\nStarting SignedOSGiExample...\n");
		System.out.format("SignedOSGiExample was loaded by %s.\n", getClass().getClassLoader().getClass().getName());
		System.out.println("Checking certificates...");
		CodeSource cs = getClass().getProtectionDomain().getCodeSource();
		Certificate[] certs = cs.getCertificates();
		boolean hasCertificates = printCerts(certs, cs.getLocation().toString());
		System.out.format("SignedOSGiExample is %s.\n", hasCertificates?"still signed":"no longer signed");
		if (printEquinoxProperties) {
			System.out.println("\nEquinox Related Properties...");
			printProperty("osgi.signedcontent.support");
			printProperty("osgi.support.class.certificate");
		}
		if (printFelixProperties) {
			System.out.println("\nFelix Related Properties...");
			printProperty("org.osgi.framework.security");
			printProperty("org.osgi.framework.trust.repositories");
			printProperty("java.security.policy");
		}
	}

	/**
	* Prints the certificates
	* @param certs The array of certificates
	* @param name The name
	* @returns True if there are any certificates
	*/
	private boolean printCerts(Certificate[] certs, String name) {
		int size = certs!=null?certs.length:0;
		if (size == 1) {
			System.out.format("There is 1 certificate in %s.\n", name);
		} else {
			System.out.format("There are %d certificates in %s.\n", size, name);
		}
		try {
			for (int i = 0; i < size; i++) {
				if (certs[i] instanceof X509Certificate) {
					System.out.println("Certificate Principal: "+ ((X509Certificate)certs[i]).getSubjectX500Principal());
				} else {
					System.out.println("Certificate Type: "+certs[i].getType());
				}
			}
		} catch (Exception e) {
			System.out.println("Error with the certificates-----");
			e.printStackTrace(System.out);
			System.out.println("\n-----");
		}
		return size>0;
	}
	
	/**
	* Prints out the setting of a system property.
	* @param propName The property name.
	*/
	private void printProperty(String propName) {
		String val = System.getProperty(propName);
		if (val == null) {
			System.out.format("%s is not set.\n", propName);
		} else {
			System.out.format("%s = '%s'.\n", propName, val);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("\nStopping SignedOSGiExample...\n");
		SignedActivator.context = null;
	}
}
