# dist Folder Structure
- [dist](/dist)
  - **Description:** This is the folder which we put all the build files for distribution of application
  - [assets](/dist/assets)
    - **Description:** just copy the assets folder from the project here with all related files
    - [db](/dist/assets/db)
    - **Description:** database files
  - [installer](/dist/installer)
  - **Description:** this folder include the files of application installer build using **InstallAnywhere** app
  - [javafx](/dist/javafx)
    - **Description:** This folder contains all the javafx files which you need to download the related version from your project and add it here.
    - [javafx/bin](/dist/javafx/bin)
    - [javafx/lib](/dist/javafx/lib)
  - [jdk](/dist/jdk)
  - **Description:** This folder contains the JDK files which is the same version as the application.
    - [jdk/bin](/dist/jdk/bin)
    - [jdk/lib](/dist/jdk/bin)
  - [libs](/dist/libs)
  - **Description:** When you package your application using maven a folder by name of libs is created inside target folder just copy it here
  - [app_icon.ico](/dist/app_icon.ico)
  - [app_icon.png](/dist/app_icon.png)
  - [NahjolFasahe.exe](/dist/NahjolFasahe.exe)
  - **Description:** This file is generated using Launch4j app
  - [launch4j.xml](/dist/launch4j.xml)
  - **Description:** Launch4j configuration file for app
  - [setup.jar](/dist/setup.jar)
  - **Description:** When you package your application using maven a file by name of setup.jar is created inside target folder just copy it here

```cmd
dist> tree
```
result:
```
dist folder abstract structure :
E:.
├───assets
├───installer
│   └───Nahjol Fasahe-cache
│       └───part1
├───javafx
│   ├───bin
│   └───lib
├───jdk
│   ├───bin
│   │   └───server
│   └───lib
│       ├───jfr
│       └───security
└───libs
```
```cmd
dist> tree /f
```
```
dist folder detail structure :
│   app_icon.ico
│   create_exe.exe
│   create_exe.py
│   dist_folder_structure.md
│   launch4j.xml
│   NahjolFasahe.exe
│   setup.jar
│
├───assets
│       hadis_db.db
│
├───installer
│   │   Nahjol Fasahe.aip
│   │   nahjol-fasahe-setup.exe
│   │
│   └───Nahjol Fasahe-cache
│       │   cacheIndex.txt
│       │
│       └───part1
│               FILES.7z
│               output-info.ini
│
├───javafx
│   ├───bin
│   │       api-ms-win-core-console-l1-1-0.dll
│   │       api-ms-win-core-console-l1-2-0.dll
│   │       api-ms-win-core-datetime-l1-1-0.dll
│   │       api-ms-win-core-debug-l1-1-0.dll
│   │       api-ms-win-core-errorhandling-l1-1-0.dll
│   │       api-ms-win-core-file-l1-1-0.dll
│   │       api-ms-win-core-file-l1-2-0.dll
│   │       api-ms-win-core-file-l2-1-0.dll
│   │       api-ms-win-core-handle-l1-1-0.dll
│   │       api-ms-win-core-heap-l1-1-0.dll
│   │       api-ms-win-core-interlocked-l1-1-0.dll
│   │       api-ms-win-core-libraryloader-l1-1-0.dll
│   │       api-ms-win-core-localization-l1-2-0.dll
│   │       api-ms-win-core-memory-l1-1-0.dll
│   │       api-ms-win-core-namedpipe-l1-1-0.dll
│   │       api-ms-win-core-processenvironment-l1-1-0.dll
│   │       api-ms-win-core-processthreads-l1-1-0.dll
│   │       api-ms-win-core-processthreads-l1-1-1.dll
│   │       api-ms-win-core-profile-l1-1-0.dll
│   │       api-ms-win-core-rtlsupport-l1-1-0.dll
│   │       api-ms-win-core-string-l1-1-0.dll
│   │       api-ms-win-core-synch-l1-1-0.dll
│   │       api-ms-win-core-synch-l1-2-0.dll
│   │       api-ms-win-core-sysinfo-l1-1-0.dll
│   │       api-ms-win-core-timezone-l1-1-0.dll
│   │       api-ms-win-core-util-l1-1-0.dll
│   │       api-ms-win-crt-conio-l1-1-0.dll
│   │       api-ms-win-crt-convert-l1-1-0.dll
│   │       api-ms-win-crt-environment-l1-1-0.dll
│   │       api-ms-win-crt-filesystem-l1-1-0.dll
│   │       api-ms-win-crt-heap-l1-1-0.dll
│   │       api-ms-win-crt-locale-l1-1-0.dll
│   │       api-ms-win-crt-math-l1-1-0.dll
│   │       api-ms-win-crt-multibyte-l1-1-0.dll
│   │       api-ms-win-crt-private-l1-1-0.dll
│   │       api-ms-win-crt-process-l1-1-0.dll
│   │       api-ms-win-crt-runtime-l1-1-0.dll
│   │       api-ms-win-crt-stdio-l1-1-0.dll
│   │       api-ms-win-crt-string-l1-1-0.dll
│   │       api-ms-win-crt-time-l1-1-0.dll
│   │       api-ms-win-crt-utility-l1-1-0.dll
│   │       decora_sse.dll
│   │       fxplugins.dll
│   │       glass.dll
│   │       glib-lite.dll
│   │       gstreamer-lite.dll
│   │       javafx_font.dll
│   │       javafx_iio.dll
│   │       jfxmedia.dll
│   │       jfxwebkit.dll
│   │       msvcp140.dll
│   │       msvcp140_1.dll
│   │       msvcp140_2.dll
│   │       prism_common.dll
│   │       prism_d3d.dll
│   │       prism_sw.dll
│   │       ucrtbase.dll
│   │       vcruntime140.dll
│   │       vcruntime140_1.dll
│   │
│   └───lib
│           javafx-swt.jar
│           javafx.base.jar
│           javafx.controls.jar
│           javafx.fxml.jar
│           javafx.graphics.jar
│           javafx.media.jar
│           javafx.properties
│           javafx.swing.jar
│           javafx.web.jar
│
├───jdk
│   ├───bin
│   │   │   api-ms-win-core-console-l1-1-0.dll
│   │   │   api-ms-win-core-console-l1-2-0.dll
│   │   │   api-ms-win-core-datetime-l1-1-0.dll
│   │   │   api-ms-win-core-debug-l1-1-0.dll
│   │   │   api-ms-win-core-errorhandling-l1-1-0.dll
│   │   │   api-ms-win-core-file-l1-1-0.dll
│   │   │   api-ms-win-core-file-l1-2-0.dll
│   │   │   api-ms-win-core-file-l2-1-0.dll
│   │   │   api-ms-win-core-handle-l1-1-0.dll
│   │   │   api-ms-win-core-heap-l1-1-0.dll
│   │   │   api-ms-win-core-interlocked-l1-1-0.dll
│   │   │   api-ms-win-core-libraryloader-l1-1-0.dll
│   │   │   api-ms-win-core-localization-l1-2-0.dll
│   │   │   api-ms-win-core-memory-l1-1-0.dll
│   │   │   api-ms-win-core-namedpipe-l1-1-0.dll
│   │   │   api-ms-win-core-processenvironment-l1-1-0.dll
│   │   │   api-ms-win-core-processthreads-l1-1-0.dll
│   │   │   api-ms-win-core-processthreads-l1-1-1.dll
│   │   │   api-ms-win-core-profile-l1-1-0.dll
│   │   │   api-ms-win-core-rtlsupport-l1-1-0.dll
│   │   │   api-ms-win-core-string-l1-1-0.dll
│   │   │   api-ms-win-core-synch-l1-1-0.dll
│   │   │   api-ms-win-core-synch-l1-2-0.dll
│   │   │   api-ms-win-core-sysinfo-l1-1-0.dll
│   │   │   api-ms-win-core-timezone-l1-1-0.dll
│   │   │   api-ms-win-core-util-l1-1-0.dll
│   │   │   api-ms-win-crt-conio-l1-1-0.dll
│   │   │   api-ms-win-crt-convert-l1-1-0.dll
│   │   │   api-ms-win-crt-environment-l1-1-0.dll
│   │   │   api-ms-win-crt-filesystem-l1-1-0.dll
│   │   │   api-ms-win-crt-heap-l1-1-0.dll
│   │   │   api-ms-win-crt-locale-l1-1-0.dll
│   │   │   api-ms-win-crt-math-l1-1-0.dll
│   │   │   api-ms-win-crt-multibyte-l1-1-0.dll
│   │   │   api-ms-win-crt-private-l1-1-0.dll
│   │   │   api-ms-win-crt-process-l1-1-0.dll
│   │   │   api-ms-win-crt-runtime-l1-1-0.dll
│   │   │   api-ms-win-crt-stdio-l1-1-0.dll
│   │   │   api-ms-win-crt-string-l1-1-0.dll
│   │   │   api-ms-win-crt-time-l1-1-0.dll
│   │   │   api-ms-win-crt-utility-l1-1-0.dll
│   │   │   attach.dll
│   │   │   awt.dll
│   │   │   dt_shmem.dll
│   │   │   dt_socket.dll
│   │   │   fontmanager.dll
│   │   │   freetype.dll
│   │   │   instrument.dll
│   │   │   j2gss.dll
│   │   │   j2pcsc.dll
│   │   │   j2pkcs11.dll
│   │   │   jaas.dll
│   │   │   jabswitch.exe
│   │   │   jaccessinspector.exe
│   │   │   jaccesswalker.exe
│   │   │   jar.exe
│   │   │   jarsigner.exe
│   │   │   java.dll
│   │   │   java.exe
│   │   │   javaaccessbridge.dll
│   │   │   javac.exe
│   │   │   javadoc.exe
│   │   │   javajpeg.dll
│   │   │   javap.exe
│   │   │   javaw.exe
│   │   │   jawt.dll
│   │   │   jcmd.exe
│   │   │   jconsole.exe
│   │   │   jdb.exe
│   │   │   jdeprscan.exe
│   │   │   jdeps.exe
│   │   │   jdwp.dll
│   │   │   jfr.exe
│   │   │   jhsdb.exe
│   │   │   jimage.dll
│   │   │   jimage.exe
│   │   │   jinfo.exe
│   │   │   jli.dll
│   │   │   jlink.exe
│   │   │   jmap.exe
│   │   │   jmod.exe
│   │   │   jpackage.dll
│   │   │   jpackage.exe
│   │   │   jps.exe
│   │   │   jrunscript.exe
│   │   │   jshell.exe
│   │   │   jsound.dll
│   │   │   jstack.exe
│   │   │   jstat.exe
│   │   │   jstatd.exe
│   │   │   jsvml.dll
│   │   │   jwebserver.exe
│   │   │   keytool.exe
│   │   │   kinit.exe
│   │   │   klist.exe
│   │   │   ktab.exe
│   │   │   lcms.dll
│   │   │   le.dll
│   │   │   management.dll
│   │   │   management_agent.dll
│   │   │   management_ext.dll
│   │   │   mlib_image.dll
│   │   │   msvcp140.dll
│   │   │   net.dll
│   │   │   nio.dll
│   │   │   prefs.dll
│   │   │   rmi.dll
│   │   │   rmiregistry.exe
│   │   │   saproc.dll
│   │   │   serialver.exe
│   │   │   splashscreen.dll
│   │   │   sspi_bridge.dll
│   │   │   sunmscapi.dll
│   │   │   ucrtbase.dll
│   │   │   vcruntime140.dll
│   │   │   vcruntime140_1.dll
│   │   │   verify.dll
│   │   │   w2k_lsa_auth.dll
│   │   │   windowsaccessbridge-64.dll
│   │   │   WinFallbackLookup.dll
│   │   │   zip.dll
│   │   │
│   │   └───server
│   │           classes.jsa
│   │           classes_nocoops.jsa
│   │           jvm.dll
│   │
│   └───lib
│       │   classlist
│       │   ct.sym
│       │   fontconfig.bfc
│       │   fontconfig.properties.src
│       │   jawt.lib
│       │   jrt-fs.jar
│       │   jvm.cfg
│       │   jvm.lib
│       │   modules
│       │   psfont.properties.ja
│       │   psfontj2d.properties
│       │   src.zip
│       │   tzdb.dat
│       │   tzmappings
│       │
│       ├───jfr
│       │       default.jfc
│       │       profile.jfc
│       │
│       └───security
│               blocked.certs
│               cacerts
│               default.policy
│               public_suffix_list.dat
│
└───libs
        apiguardian-api-1.1.2.jar
        bootstrapfx-core-0.4.0.jar
        controlsfx-11.1.1.jar
        ikonli-bootstrapicons-pack-12.3.1.jar
        ikonli-core-12.3.1.jar
        ikonli-javafx-12.3.1.jar
        jai-imageio-core-1.4.0.jar
        javafx-base-18.0.1-win.jar
        javafx-base-18.0.1.jar
        javafx-controls-18.0.1-win.jar
        javafx-controls-18.0.1.jar
        javafx-fxml-18.0.1-win.jar
        javafx-fxml-18.0.1.jar
        javafx-graphics-18.0.1-win.jar
        javafx-graphics-18.0.1.jar
        javafx-swing-18.0.1-win.jar
        javafx-swing-18.0.1.jar
        junit-jupiter-api-5.8.2.jar
        junit-jupiter-engine-5.8.2.jar
        junit-platform-commons-1.8.2.jar
        junit-platform-engine-1.8.2.jar
        opentest4j-1.2.0.jar
        sqlite-jdbc-3.39.4.1.jar
```