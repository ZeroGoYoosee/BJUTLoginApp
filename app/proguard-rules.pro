# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Softwares\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
-keep class * {
    public private *;
}

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class xin.z7workbench.bjutloginapp.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class xin.z7workbench.bjutloginapp.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class xin.z7workbench.bjutloginapp.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}