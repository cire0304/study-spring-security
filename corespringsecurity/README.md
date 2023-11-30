server.ssl.key-store: keystore.p12
server.ssl.key-store-password: 123456
server.ssl.keyStoreType: PKCS12
server.ssl.keyAlias: tomcat

===

keytool -genkey \
        -alias tomcat \
        -storetype PKCS12 \
        -keyalg RSA \
        -keysize 2048 \
        -keystore keystore.p12 \ 
        -validity 4000 
