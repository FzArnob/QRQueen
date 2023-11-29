package com.google.appinventor.components.runtime.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class PackageInstaller {
    private PackageInstaller() {
    }

    public static void doPackageInstall(final Form form, final String str) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                FileOutputStream fileOutputStream;
                BufferedInputStream bufferedInputStream;
                Exception e;
                File file;
                BufferedInputStream bufferedInputStream2 = null;
                try {
                    bufferedInputStream = new BufferedInputStream(new URL(str).openConnection().getInputStream());
                    try {
                        file = new File(QUtil.getReplAssetPath(form), "/package.apk");
                        fileOutputStream = new FileOutputStream(file);
                    } catch (ActivityNotFoundException e2) {
                        e = e2;
                        fileOutputStream = null;
                        Log.e("PackageInstaller (Kodular)", "Unable to install package", e);
                        Form form = form;
                        form.dispatchErrorOccurredEvent(form, "PackageInstaller", ErrorMessages.ERROR_UNABLE_TO_INSTALL_PACKAGE, null);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                    } catch (Exception e3) {
                        e = e3;
                        fileOutputStream = null;
                        e = e;
                        Log.e("PackageInstaller (Kodular)", "ERROR_UNABLE_TO_GET", e);
                        Form form2 = form;
                        form2.dispatchErrorOccurredEvent(form2, "PackageInstaller", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, str);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        bufferedInputStream2 = bufferedInputStream;
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream2);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[32768];
                        while (true) {
                            int read = bufferedInputStream.read(bArr, 0, 32768);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        bufferedInputStream.close();
                        fileOutputStream.close();
                        Log.d("PackageInstaller (Kodular)", "About to Install package from " + str);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(NougatUtil.getPackageUri(form, file), "application/vnd.android.package-archive");
                        intent.setFlags(1);
                        form.startActivity(intent);
                    } catch (ActivityNotFoundException e4) {
                        e = e4;
                        Log.e("PackageInstaller (Kodular)", "Unable to install package", e);
                        Form form3 = form;
                        form3.dispatchErrorOccurredEvent(form3, "PackageInstaller", ErrorMessages.ERROR_UNABLE_TO_INSTALL_PACKAGE, null);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                    } catch (Exception e5) {
                        e = e5;
                        Log.e("PackageInstaller (Kodular)", "ERROR_UNABLE_TO_GET", e);
                        Form form22 = form;
                        form22.dispatchErrorOccurredEvent(form22, "PackageInstaller", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, str);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                        IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                    }
                } catch (ActivityNotFoundException e6) {
                    e = e6;
                    bufferedInputStream = null;
                    fileOutputStream = null;
                    Log.e("PackageInstaller (Kodular)", "Unable to install package", e);
                    Form form32 = form;
                    form32.dispatchErrorOccurredEvent(form32, "PackageInstaller", ErrorMessages.ERROR_UNABLE_TO_INSTALL_PACKAGE, null);
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                } catch (Exception e7) {
                    e = e7;
                    bufferedInputStream = null;
                    fileOutputStream = null;
                    e = e;
                    Log.e("PackageInstaller (Kodular)", "ERROR_UNABLE_TO_GET", e);
                    Form form222 = form;
                    form222.dispatchErrorOccurredEvent(form222, "PackageInstaller", ErrorMessages.ERROR_WEB_UNABLE_TO_GET, str);
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream2 = bufferedInputStream;
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream2);
                    IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
                    throw th;
                }
                IOUtils.closeQuietly("PackageInstaller (Kodular)", bufferedInputStream);
                IOUtils.closeQuietly("PackageInstaller (Kodular)", fileOutputStream);
            }
        });
    }
}
