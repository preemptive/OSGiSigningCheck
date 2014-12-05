package com.preEmptive.example.signedOSGi;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.cert.Certificate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SignedActivator implements BundleActivator {

	private static BundleContext context;

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
		System.out.format("SignedOSGiExample was loaded by %s\n.", getClass().getClassLoader().getClass().getName());
		System.out.println("Checking certificates...");
		CodeSource cs = getClass().getProtectionDomain().getCodeSource();
		Certificate[] certs = cs.getCertificates();
		boolean hasCertificates = printCerts(certs, cs.getLocation().toString());
		System.out.format("SignedOSGiExample is %s.\n", hasCertificates?"still signed":"no longer signed");
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
				System.out.println(certs[i].getEncoded());
			}
		} catch (Exception e) {
			System.out.println("Error with the certificates-----");
			e.printStackTrace(System.out);
			System.out.println("\n-----");
		}
		return size>0;
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